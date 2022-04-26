package com.future.observermonitorpublic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.databind.JsonNode;
import com.future.observercommon.dto.DeviceDTO;
import com.future.observercommon.dto.ImgBasePath;
import com.future.observercommon.util.BeanUtil;
import com.future.observercommon.util.DateUtil;
import com.future.observercommon.util.FileUtil;
import com.future.observercommon.util.JacksonUtil;
import com.future.observermonitorpublic.dto.PublicStatisDTO;
import com.future.observermonitorpublic.mapper.PublicImgMapper;
import com.future.observermonitorpublic.mapper.PublicPeopleMapper;
import com.future.observermonitorpublic.mapper.PublicStandardMapper;
import com.future.observermonitorpublic.po.*;
import com.future.observermonitorpublic.service.BaiDuAIService;
import com.future.observermonitorpublic.service.PublicMonitorService;
import com.future.observermonitorpublic.service.PublicStatisService;
import com.future.observercommon.vo.PublicIllegalInfoVO;
import com.future.observercommon.vo.PublicPeopleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class PublicMonitorServiceImpl implements PublicMonitorService {

    @Autowired
    private BaiDuAIService baiDuAIService;

    @Autowired
    private PublicStatisService publicStatisService;

    @Autowired
    @SuppressWarnings("all")
    private PublicStandardMapper publicStandardMapper;

    @Autowired
    @SuppressWarnings("all")
    private PublicImgMapper publicImgMapper;

    @Autowired
    @SuppressWarnings("all")
    private PublicPeopleMapper publicPeopleMapper;

    @Autowired
    private ImgBasePath imgBasePath;

    @Override
    public PublicIllegalInfoVO check(DeviceDTO deviceDTO) throws Exception {
        // 获取监控图片的字节流
        byte[] monitorImg = FileUtil.receiveFile(deviceDTO.getPicUrl());
        // 检测结果
        String detectionResult = (String) baiDuAIService.check(deviceDTO).getResult();

        // 获取监控设备的非法信息标准
        PublicStandard standard = publicStandardMapper.selectOneByDeviceId(deviceDTO.getDeviceId());

        // 当前图片的非法类型
        Set<String> illegalType = new HashSet<>();

        // 保存当前图片的非法检测信息
        LinkedList<PublicPeople> publicPeopleList = new LinkedList<>();

        /*
         * 获取非法信息中的每个属性
         * 根据非法信息标准，对属性进行判断
         * 若出现了非法信息，则将当前图片及其非法信息保存
         */
        JsonNode personInfo = JacksonUtil.jsonNodeOf(detectionResult, "person_info");
        for (int i = 0; i < personInfo.size(); i++) {
            JsonNode attributes = JacksonUtil.jsonNodeOf(personInfo.get(i), "attributes");

            PublicPeople people = new PublicPeople();

            /*
             * 人体框的信息
             */
            Integer locHeight = JacksonUtil.jsonNodeOf(personInfo.get(i), "location", "height").asInt();
            Integer locWidth = JacksonUtil.jsonNodeOf(personInfo.get(i), "location", "width").asInt();
            Integer locLeft = JacksonUtil.jsonNodeOf(personInfo.get(i), "location", "left").asInt();
            Integer locTop = JacksonUtil.jsonNodeOf(personInfo.get(i), "location", "top").asInt();
            people.setLocHeight(locHeight);
            people.setLocWidth(locWidth);
            people.setLocLeft(locLeft);
            people.setLocTop(locTop);
            /*
             * 性别
             */
            String gender = JacksonUtil.jsonNodeOf(attributes, "gender", "name").asText();
            double genderScore = JacksonUtil.jsonNodeOf(attributes, "gender", "score").asDouble();
            people.setGender(gender);
            /*
             * 年龄阶段
             */
            String age = JacksonUtil.jsonNodeOf(attributes, "age", "name").asText();
            double ageScore = JacksonUtil.jsonNodeOf(attributes, "age", "score").asDouble();
            people.setAge(age);
            /*
             * 上身服饰
             */
            String upperWear = JacksonUtil.jsonNodeOf(attributes, "upper_wear", "name").asText();
            double upperWearScore = JacksonUtil.jsonNodeOf(attributes, "upper_wear", "score").asDouble();
            people.setUpperWear(upperWear);
            /*
             * 上身服饰颜色
             */
            String upperColor = JacksonUtil.jsonNodeOf(attributes, "upper_color", "name").asText();
            double upperColorScore = JacksonUtil.jsonNodeOf(attributes, "upper_color", "score").asDouble();
            people.setUpperColor(upperColor);
            /*
             * 上身服饰纹理
             */
            String upperWearTexture = JacksonUtil.jsonNodeOf(attributes, "upper_wear_texture", "name").asText();
            double upperWearTextureScore = JacksonUtil.jsonNodeOf(attributes, "upper_wear_texture", "score").asDouble();
            people.setUpperWearTexture(upperWearTexture);
            /*
             * 上身服饰细分类
             */
            String upperWearFg = JacksonUtil.jsonNodeOf(attributes, "upper_wear_fg", "name").asText();
            double upperWearFgScore = JacksonUtil.jsonNodeOf(attributes, "upper_wear_fg", "score").asDouble();
            people.setUpperWearFg(upperWearFg);
            /*
             * 下身服饰
             */
            String lowerWear = JacksonUtil.jsonNodeOf(attributes, "lower_wear", "name").asText();
            double lowerWearScore = JacksonUtil.jsonNodeOf(attributes, "lower_wear", "score").asDouble();
            people.setLowerWear(lowerWear);
            /*
             * 下身服饰颜色
             */
            String lowerColor = JacksonUtil.jsonNodeOf(attributes, "lower_color", "name").asText();
            double lowerColorScore = JacksonUtil.jsonNodeOf(attributes, "lower_color", "score").asDouble();
            people.setLowerColor(lowerColor);
            /*
             * 是否戴帽子
             */
            String headWear = JacksonUtil.jsonNodeOf(attributes, "headwear", "name").asText();
            double headWearScore = JacksonUtil.jsonNodeOf(attributes, "headwear", "score").asDouble();
            people.setHeadWear(headWear);
            /*
             * 是否戴眼镜
             */
            String glasses = JacksonUtil.jsonNodeOf(attributes, "glasses", "name").asText();
            double glassesScore = JacksonUtil.jsonNodeOf(attributes, "glasses", "score").asDouble();
            people.setGlasses(glasses);
            /*
             * 是否背背包
             */
            String bag = JacksonUtil.jsonNodeOf(attributes, "bag", "name").asText();
            double bagScore = JacksonUtil.jsonNodeOf(attributes, "bag", "score").asDouble();
            people.setBag(bag);
            /*
             * 是否戴口罩
             */
            String faceMask = JacksonUtil.jsonNodeOf(attributes, "face_mask", "name").asText();
            double faceMaskScore = JacksonUtil.jsonNodeOf(attributes, "face_mask", "score").asDouble();
            people.setFaceMask(faceMask);
            /*
             * 人体朝向
             */
            String orientation = JacksonUtil.jsonNodeOf(attributes, "orientation", "name").asText();
            double orientationScore = JacksonUtil.jsonNodeOf(attributes, "orientation", "score").asDouble();
            people.setOrientation(orientation);
            /*
             * 是否使用手机
             */
            String cellphone = JacksonUtil.jsonNodeOf(attributes, "cellphone", "name").asText();
            double cellphoneScore = JacksonUtil.jsonNodeOf(attributes, "cellphone", "score").asDouble();
            people.setCellphone(cellphone);
            /*
             * 是否抽烟
             */
            String smoke = JacksonUtil.jsonNodeOf(attributes, "smoke", "name").asText();
            double smokeScore = JacksonUtil.jsonNodeOf(attributes, "smoke", "score").asDouble();
            people.setSmoke(smoke);
            /*
             * 是否有手提物
             */
            String carryingItem = JacksonUtil.jsonNodeOf(attributes, "carrying_item", "name").asText();
            double carryingItemScore = JacksonUtil.jsonNodeOf(attributes, "carrying_item", "score").asDouble();
            people.setCarryingItem(carryingItem);
            /*
             * 是否打伞
             */
            String umbrella = JacksonUtil.jsonNodeOf(attributes, "umbrella", "name").asText();
            double umbrellaScore = JacksonUtil.jsonNodeOf(attributes, "umbrella", "score").asDouble();
            people.setUmbrella(umbrella);
            /*
             * 交通工具
             */
            String vehicle = JacksonUtil.jsonNodeOf(attributes, "vehicle", "name").asText();
            double vehicleScore = JacksonUtil.jsonNodeOf(attributes, "vehicle", "score").asDouble();
            people.setVehicle(vehicle);
            /*
             * 遮挡情况
             */
            String occlusion = JacksonUtil.jsonNodeOf(attributes, "occlusion", "name").asText();
            double occlusionScore = JacksonUtil.jsonNodeOf(attributes, "occlusion", "score").asDouble();
            people.setOcclusion(occlusion);
            /*
             * 上方截断
             */
            String upperCut = JacksonUtil.jsonNodeOf(attributes, "upper_cut", "name").asText();
            double upperCutScore = JacksonUtil.jsonNodeOf(attributes, "upper_cut", "score").asDouble();
            people.setUpperCut(upperCut);
            /*
             * 下方截断
             */
            String lowerCut = JacksonUtil.jsonNodeOf(attributes, "lower_cut", "name").asText();
            double lowerCutScore = JacksonUtil.jsonNodeOf(attributes, "lower_cut", "score").asDouble();
            people.setLowerCut(lowerCut);
            /*
             * 是否是正常人体，用于判断说明人体的截断/遮挡情况，并非判断动物等非人类生物
             */
            String isHuman = JacksonUtil.jsonNodeOf(attributes, "is_human", "name").asText();
            double isHumanScore = JacksonUtil.jsonNodeOf(attributes, "is_human", "score").asDouble();
            people.setIsHuman(isHuman);
            /*
             * 非法判断
             */
            Field[] standardFields = PublicStandard.class.getDeclaredFields();
            Field[] peopleFields = PublicPeople.class.getDeclaredFields();
            boolean flag = false; // 表示当前人体信息是否非法
            for (int j = 7; j < standardFields.length; j++) {
                standardFields[j].setAccessible(true);
                peopleFields[j].setAccessible(true);
                String standardField = (String) standardFields[j].get(standard);
                String peopleField = (String) peopleFields[j].get(people);
                if (standardField != null && standardField.contains(peopleField)) { // 出现非法信息
                    illegalType.add(peopleField);
                    flag = true;
                }
            }
            if (flag) {
                publicPeopleList.add(people);
            }
        }

        /*
         * 保存非法信息
         */
        if (publicPeopleList.size() > 0) {
            // 保存非法监控图像
            File illegalImg = FileUtil.createFile(imgBasePath.getPublicMonitorPath() + "/" + deviceDTO.getDeviceSerial() + "/" + DateUtil.getNow() + ".jpg");
            FileUtil.copy(monitorImg, illegalImg);

            String s = illegalType.toString();
            s = s.substring(1, s.length() - 1); // 去除"["和"]"

            PublicImg publicImg = new PublicImg();
            publicImg.setPath(illegalImg.getPath());
            publicImg.setIllegalType(s);
            publicImg.setDeviceId(deviceDTO.getDeviceId());
            publicImgMapper.insert(publicImg);

            // 保存非法监控信息
            for (PublicPeople people : publicPeopleList) {
                people.setImgId(publicImg.getId());
                publicPeopleMapper.insert(people);

                // 非法统计
                PublicStatisDTO publicStatisDTO = new PublicStatisDTO(
                        deviceDTO.getDeviceId(),
                        DateUtil.toDate(people.getCreateTime().toString(), "yyyy-MM-dd")
                );
                publicStatisService.add(publicStatisDTO);
            }
        }

        /*
         * 返回非法信息
         */
        PublicIllegalInfoVO publicIllegalInfoVO = new PublicIllegalInfoVO();
        List<PublicPeopleVO> publicPeopleVOList = new ArrayList<>(publicPeopleList.size());
        for (PublicPeople publicPeople : publicPeopleList) {
            PublicPeopleVO publicPeopleVO = new PublicPeopleVO();
            BeanUtil.copyBeanProp(publicPeopleVO, publicPeople);
            publicPeopleVOList.add(publicPeopleVO);
        }
        publicIllegalInfoVO.setIllegalInfoList(publicPeopleVOList);
        return publicIllegalInfoVO;
    }

    @Override
    public List<PublicIllegalInfoVO> findIllegalInfoAll(DeviceDTO deviceDTO) throws IOException {
        // 用于保存当前设备的所有非法监控图片及非法信息列表
        List<PublicIllegalInfoVO> publicIllegalInfoVOList = new LinkedList<>();

        // 获取所有非法监控图像
        List<PublicImg> publicImgList = publicImgMapper.selectPage(
                new Page<>(1, 20),
                new QueryWrapper<PublicImg>()
                        .eq("device_id", deviceDTO.getDeviceId())
                        .orderByDesc("create_time")
        ).getRecords();

        // 获取每个非法监控图像对应的非法信息
        for (PublicImg publicImg : publicImgList) {
            List<PublicPeople> publicPeopleList = publicPeopleMapper.selectList(
                    new QueryWrapper<PublicPeople>()
                            .eq("img_id", publicImg.getId())
            );
            List<PublicPeopleVO> publicPeopleVOList = new LinkedList<>();
            for (PublicPeople publicPeople : publicPeopleList) {
                PublicPeopleVO publicPeopleVO = new PublicPeopleVO();
                BeanUtil.copyBeanProp(publicPeopleVO, publicPeople);
                publicPeopleVOList.add(publicPeopleVO);
            }

            // 保存当前设备的当前非法监控图片及非法信息列表
            PublicIllegalInfoVO publicIllegalInfoVO = new PublicIllegalInfoVO(
                    publicImg.getCreateTime(),
                    Base64Utils.encodeToString(FileUtil.readFileAsBytes(publicImg.getPath())),
                    publicImg.getStatus(),
                    publicImg.getIllegalType(),
                    publicPeopleVOList
            );
            publicIllegalInfoVOList.add(publicIllegalInfoVO);
        }

        // 返回当前设备的所有非法监控图片及非法信息列表
        return publicIllegalInfoVOList;
    }
}
