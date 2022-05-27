package com.future.observermonitor.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.databind.JsonNode;
import com.future.observercommon.dto.DeviceDTO;
import com.future.observercommon.dto.ImgBasePath;
import com.future.observercommon.util.BeanUtil;
import com.future.observercommon.util.DateUtil;
import com.future.observercommon.util.FileUtil;
import com.future.observercommon.util.JacksonUtil;
import com.future.observermonitor.dto.StatisticDTO;
import com.future.observermonitor.mapper.IllegalInfoMapper;
import com.future.observermonitor.mapper.ImgMapper;
import com.future.observermonitor.po.Device;
import com.future.observermonitor.po.IllegalInfo;
import com.future.observermonitor.po.Img;
import com.future.observermonitor.service.*;
import com.future.observermonitor.vo.IllegalInfoVO;
import com.future.observermonitor.vo.ImgVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class MonitorServiceImpl implements MonitorService {

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private BaiDuAIService baiDuAIService;

    @Autowired
    private StatisticForUserService publicStatisticForUserService;

    @Autowired
    private StatisticForDeviceService publicStatisticForDeviceService;

    @Autowired
    @SuppressWarnings("all")
    private ImgMapper imgMapper;

    @Autowired
    @SuppressWarnings("all")
    private IllegalInfoMapper illegalInfoMapper;

    @Autowired
    private ImgBasePath imgBasePath;

    @Override
    public List<ImgVO> listOfImgVO(DeviceDTO deviceDTO) throws IOException {
        List<ImgVO> imgVOList = new LinkedList<>();

        List<Img> imgList = imgMapper.selectPageByDeviceSerial(new Page<>(1, 20), deviceDTO.getDeviceSerial());

        for (Img img : imgList) {
            // 获取每个非法监控图像对应的非法信息
            List<IllegalInfo> illegalInfoList = illegalInfoMapper.selectList(
                    new QueryWrapper<IllegalInfo>()
                            .eq("img_id", img.getId())
            );

            List<IllegalInfoVO> illegalInfoVOList = new ArrayList<>();
            BeanUtil.copyListProp(illegalInfoVOList, illegalInfoList, IllegalInfoVO.class);

            ImgVO imgVO = new ImgVO();
            BeanUtil.copyBeanProp(imgVO, img);
            imgVO.setBase64OfImg(Base64Utils.encodeToString(FileUtil.readFileAsBytes(img.getPath())));
            imgVO.setIllegalInfoList(illegalInfoVOList);

            imgVOList.add(imgVO);
        }

        return imgVOList;
    }

    @Override
    public ImgVO check(DeviceDTO deviceDTO) throws Exception {
        // 获取监控图片的字节流
        byte[] monitorImg = FileUtil.receiveFile(deviceDTO.getPicUrl());
        // 检测结果
        String detectionResult = (String) baiDuAIService.check(deviceDTO).getResult();

        // 获取监控设备的非法信息标准
        Device device = deviceService.getOne(new QueryWrapper<Device>().eq("device_serial", deviceDTO.getDeviceSerial()));

        // 当前图片的非法类型
        Set<String> illegalType = new HashSet<>();

        // 保存当前图片的非法检测信息
        List<IllegalInfo> illegalInfoList = new LinkedList<>();

        // 保存非法信息的统计数据
        StatisticDTO statisticDTO = new StatisticDTO();

        /*
         * 获取非法信息中的每个属性
         * 根据非法信息标准，对属性进行判断
         * 若出现了非法信息，则将当前图片及其非法信息保存
         */
        JsonNode personInfo = JacksonUtil.jsonNodeOf(detectionResult, "person_info");
        for (int i = 0; i < personInfo.size(); i++) {
            JsonNode attributes = JacksonUtil.jsonNodeOf(personInfo.get(i), "attributes");

            IllegalInfo illegalInfo = new IllegalInfo();

            /*
             * 人体框的信息
             */
            JsonNode location = JacksonUtil.jsonNodeOf(personInfo.get(i), "location");
            illegalInfo.setLocHeight(location.path("height").asInt());
            illegalInfo.setLocWidth(location.path("width").asInt());
            illegalInfo.setLocLeft(location.path("left").asInt());
            illegalInfo.setLocTop(location.path("top").asInt());
            /*
             * 性别
             */
            illegalInfo.setGender(JacksonUtil.jsonNodeOf(attributes, "gender", "name").asText());
            double genderScore = JacksonUtil.jsonNodeOf(attributes, "gender", "score").asDouble();
            /*
             * 年龄阶段
             */
            illegalInfo.setAge(JacksonUtil.jsonNodeOf(attributes, "age", "name").asText());
            double ageScore = JacksonUtil.jsonNodeOf(attributes, "age", "score").asDouble();
            /*
             * 上身服饰
             */
            illegalInfo.setUpperWear(JacksonUtil.jsonNodeOf(attributes, "upper_wear", "name").asText());
            double upperWearScore = JacksonUtil.jsonNodeOf(attributes, "upper_wear", "score").asDouble();
            /*
             * 上身服饰颜色
             */
            illegalInfo.setUpperColor(JacksonUtil.jsonNodeOf(attributes, "upper_color", "name").asText());
            double upperColorScore = JacksonUtil.jsonNodeOf(attributes, "upper_color", "score").asDouble();
            /*
             * 上身服饰纹理
             */
            illegalInfo.setUpperWearTexture(JacksonUtil.jsonNodeOf(attributes, "upper_wear_texture", "name").asText());
            double upperWearTextureScore = JacksonUtil.jsonNodeOf(attributes, "upper_wear_texture", "score").asDouble();
            /*
             * 上身服饰细分类
             */
            illegalInfo.setUpperWearFg(JacksonUtil.jsonNodeOf(attributes, "upper_wear_fg", "name").asText());
            double upperWearFgScore = JacksonUtil.jsonNodeOf(attributes, "upper_wear_fg", "score").asDouble();
            /*
             * 下身服饰
             */
            illegalInfo.setLowerWear(JacksonUtil.jsonNodeOf(attributes, "lower_wear", "name").asText());
            double lowerWearScore = JacksonUtil.jsonNodeOf(attributes, "lower_wear", "score").asDouble();
            /*
             * 下身服饰颜色
             */
            illegalInfo.setLowerColor(JacksonUtil.jsonNodeOf(attributes, "lower_color", "name").asText());
            double lowerColorScore = JacksonUtil.jsonNodeOf(attributes, "lower_color", "score").asDouble();
            /*
             * 是否戴帽子
             */
            illegalInfo.setHeadWear(JacksonUtil.jsonNodeOf(attributes, "headwear", "name").asText());
            double headWearScore = JacksonUtil.jsonNodeOf(attributes, "headwear", "score").asDouble();
            /*
             * 是否戴眼镜
             */
            illegalInfo.setGlasses(JacksonUtil.jsonNodeOf(attributes, "glasses", "name").asText());
            double glassesScore = JacksonUtil.jsonNodeOf(attributes, "glasses", "score").asDouble();
            /*
             * 是否背背包
             */
            illegalInfo.setBag(JacksonUtil.jsonNodeOf(attributes, "bag", "name").asText());
            double bagScore = JacksonUtil.jsonNodeOf(attributes, "bag", "score").asDouble();
            /*
             * 是否戴口罩
             */
            illegalInfo.setFaceMask(JacksonUtil.jsonNodeOf(attributes, "face_mask", "name").asText());
            double faceMaskScore = JacksonUtil.jsonNodeOf(attributes, "face_mask", "score").asDouble();
            /*
             * 人体朝向
             */
            illegalInfo.setOrientation(JacksonUtil.jsonNodeOf(attributes, "orientation", "name").asText());
            double orientationScore = JacksonUtil.jsonNodeOf(attributes, "orientation", "score").asDouble();
            /*
             * 是否使用手机
             */
            illegalInfo.setCellphone(JacksonUtil.jsonNodeOf(attributes, "cellphone", "name").asText());
            double cellphoneScore = JacksonUtil.jsonNodeOf(attributes, "cellphone", "score").asDouble();
            /*
             * 是否抽烟
             */
            illegalInfo.setSmoke(JacksonUtil.jsonNodeOf(attributes, "smoke", "name").asText());
            double smokeScore = JacksonUtil.jsonNodeOf(attributes, "smoke", "score").asDouble();
            /*
             * 是否有手提物
             */
            illegalInfo.setCarryingItem(JacksonUtil.jsonNodeOf(attributes, "carrying_item", "name").asText());
            double carryingItemScore = JacksonUtil.jsonNodeOf(attributes, "carrying_item", "score").asDouble();
            /*
             * 是否打伞
             */
            illegalInfo.setUmbrella(JacksonUtil.jsonNodeOf(attributes, "umbrella", "name").asText());
            double umbrellaScore = JacksonUtil.jsonNodeOf(attributes, "umbrella", "score").asDouble();
            /*
             * 交通工具
             */
            illegalInfo.setVehicle(JacksonUtil.jsonNodeOf(attributes, "vehicle", "name").asText());
            double vehicleScore = JacksonUtil.jsonNodeOf(attributes, "vehicle", "score").asDouble();
            /*
             * 遮挡情况
             */
            illegalInfo.setOcclusion(JacksonUtil.jsonNodeOf(attributes, "occlusion", "name").asText());
            double occlusionScore = JacksonUtil.jsonNodeOf(attributes, "occlusion", "score").asDouble();
            /*
             * 上方截断
             */
            illegalInfo.setUpperCut(JacksonUtil.jsonNodeOf(attributes, "upper_cut", "name").asText());
            double upperCutScore = JacksonUtil.jsonNodeOf(attributes, "upper_cut", "score").asDouble();
            /*
             * 下方截断
             */
            illegalInfo.setLowerCut(JacksonUtil.jsonNodeOf(attributes, "lower_cut", "name").asText());
            double lowerCutScore = JacksonUtil.jsonNodeOf(attributes, "lower_cut", "score").asDouble();
            /*
             * 是否是正常人体，用于判断说明人体的截断/遮挡情况，并非判断动物等非人类生物
             */
            illegalInfo.setIsHuman(JacksonUtil.jsonNodeOf(attributes, "is_human", "name").asText());
            double isHumanScore = JacksonUtil.jsonNodeOf(attributes, "is_human", "score").asDouble();

            /*
             * 非法判断
             */
            // 非法信息（可能）
            Field[] fieldsOfIllegalInfo = illegalInfo.getClass().getDeclaredFields();
            // 非法标准
            Field[] fieldsOfDevice = device.getClass().getDeclaredFields();
            // 非法统计信息
            Field[] fieldsOfStatistic = statisticDTO.getClass().getDeclaredFields();

            boolean flag = false; // 表示当前人体信息是否非法
            for (int j = 7; j < fieldsOfIllegalInfo.length - 1; j++) {
                Field fieldOfIllegalInfo = fieldsOfIllegalInfo[j];
                fieldOfIllegalInfo.setAccessible(true);
                String fieldValueOfIllegalInfo = (String) fieldOfIllegalInfo.get(illegalInfo);

                Field fieldOfDevice = fieldsOfDevice[j + 1];
                fieldOfDevice.setAccessible(true);
                String fieldValueOfDevice = (String) fieldOfDevice.get(device);

                if (fieldValueOfDevice != null && fieldValueOfDevice.contains(fieldValueOfIllegalInfo)) { // 出现非法信息
                    // 添加非法类型
                    illegalType.add(fieldValueOfIllegalInfo);

                    /*
                     * 该类型的非法数 + 1
                     */
                    Field fieldOfStatistic = fieldsOfStatistic[j + 1];
                    fieldOfStatistic.setAccessible(true);
                    Integer fieldValueOfStatistic = (Integer) fieldOfStatistic.get(statisticDTO);
                    fieldValueOfStatistic = fieldValueOfStatistic == null ? 1 : fieldValueOfStatistic + 1;
                    fieldOfStatistic.set(statisticDTO, fieldValueOfStatistic);

                    /*
                     * 非法总数 + 1，未处理数 + 1
                     */
                    Integer totalNum = statisticDTO.getTotalNum();
                    totalNum = totalNum == null ? 1 : totalNum + 1;
                    statisticDTO.setTotalNum(totalNum);
                    statisticDTO.setUntreatedNum(totalNum);

                    flag = true;
                }
            }

            if (flag) {
                illegalInfoList.add(illegalInfo);
            }
        }

        /*
         * 保存非法信息
         */
        if (illegalInfoList.size() > 0) {
            // 保存非法监控图像
            File illegalImg = FileUtil.createFile(imgBasePath.getMonitorPath() + deviceDTO.getDeviceSerial() + "/" + DateUtil.getNow() + ".jpg");
            FileUtil.copy(monitorImg, illegalImg);

            String stringOfIllegalType = illegalType.toString();
            stringOfIllegalType = stringOfIllegalType.substring(1, stringOfIllegalType.length() - 1); // 去除"["和"]"

            Img img = new Img();
            img.setPath(illegalImg.getPath());
            img.setIllegalType(stringOfIllegalType);
            img.setDeviceSerial(deviceDTO.getDeviceSerial());
            imgMapper.insertByDeviceSerial(img);

            // 保存非法信息
            for (IllegalInfo illegalInfo : illegalInfoList) {
                illegalInfo.setImgId(img.getId());
                illegalInfoMapper.insert(illegalInfo);
            }

            // 非法统计
            statisticDTO.setUsername(deviceDTO.getUsername());
            statisticDTO.setDeviceSerial(deviceDTO.getDeviceSerial());
            statisticDTO.setDate(DateUtil.toDate(img.getCreateTime().toString(), "yyyy-MM-dd"));
            publicStatisticForDeviceService.add(statisticDTO);
            publicStatisticForUserService.add(statisticDTO);
        }

        /*
         * 返回非法信息
         */
        ImgVO imgVO = new ImgVO();

        List<IllegalInfoVO> illegalInfoVOList = new ArrayList<>(illegalInfoList.size());
        BeanUtil.copyListProp(illegalInfoVOList, illegalInfoList, IllegalInfoVO.class);

        imgVO.setIllegalInfoList(illegalInfoVOList);

        return imgVO;
    }
}
