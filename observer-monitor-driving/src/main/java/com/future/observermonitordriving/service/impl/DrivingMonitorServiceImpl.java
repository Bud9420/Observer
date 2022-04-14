package com.future.observermonitordriving.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.future.observercommon.mapper.DrivingDriverMapper;
import com.future.observercommon.mapper.DrivingImgMapper;
import com.future.observercommon.mapper.DrivingStandardMapper;
import com.future.observercommon.mapper.DrivingUserStandardMapper;
import com.future.observercommon.po.*;
import com.future.observercommon.service.BaiDuAIService;
import com.future.observercommon.service.DrivingStatisService;
import com.future.observercommon.service.MonitorOfDrivingService;
import com.future.observercommon.service.YSOpenService;
import com.future.observercommon.util.DateUtil;
import com.future.observercommon.util.JacksonUtil;
import com.future.dto.BaiDuAIRequestInfoDto;
import com.future.observercommon.dto.ImgBasePath;
import com.future.mapper.*;
import com.future.po.*;
import com.future.observercommon.util.BeanUtils;
import com.future.observercommon.vo.IllegalInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class DrivingMonitorServiceImpl implements MonitorOfDrivingService {

    @Autowired
    private YSOpenService ysOpenService;

    @Autowired
    private BaiDuAIService baiDuAIService;

    @Autowired
    private DrivingStatisService drivingStatisService;

    @Autowired
    @SuppressWarnings("all")
    private DrivingUserStandardMapper drivingUserStandardMapper;

    @Autowired
    @SuppressWarnings("all")
    private DrivingStandardMapper drivingStandardMapper;

    @Autowired
    @SuppressWarnings("all")
    private DrivingImgMapper drivingImgMapper;

    @Autowired
    @SuppressWarnings("all")
    private DrivingDriverMapper drivingDriverMapper;

    @Autowired
    private BaiDuAIRequestInfoDto baiDuAIRequestInfoDto;

    @Autowired
    private ImgBasePath imageBasePathDto;

    @Override
    public void monitor(User user) throws Exception {
        File monitorImg = ysOpenService.capture(imageBasePathDto.getDrivingMonitorPath() + "/" + user.getUsername());

        String result = baiDuAIService.check(monitorImg, baiDuAIRequestInfoDto.getURLOfDrivingBehavior());
        JsonNode personInfo = JacksonUtil.jsonNodeOf(result, "person_info");

        DrivingUserStandard userStandard = drivingUserStandardMapper.selectOne(new QueryWrapper<DrivingUserStandard>().eq("user_id", user.getId()));
        DrivingStandard standard = drivingStandardMapper.selectById(userStandard.getStandardId());

        boolean flag = true; // 用于标识图片是否出现了非法信息， true表示未出现，false表示出现
        LinkedList<DrivingDriver> list = new LinkedList<>();

        for (int i = 0; i < personInfo.size(); i++) {
            JsonNode attributes = JacksonUtil.jsonNodeOf(personInfo.get(i), "attributes");

            if (attributes.asText().equals("")) {
                break;
            }

            DrivingDriver driver = new DrivingDriver();

            // 人体框的信息
            Integer locHeight = JacksonUtil.jsonNodeOf(personInfo.get(i), "location", "height").asInt();
            Integer locWidth = JacksonUtil.jsonNodeOf(personInfo.get(i), "location", "width").asInt();
            Integer locLeft = JacksonUtil.jsonNodeOf(personInfo.get(i), "location", "left").asInt();
            Integer locTop = JacksonUtil.jsonNodeOf(personInfo.get(i), "location", "top").asInt();
            driver.setLocHeight(locHeight);
            driver.setLocWidth(locWidth);
            driver.setLocLeft(locLeft);
            driver.setLocTop(locTop);
            // 吸烟
            double smoke = JacksonUtil.jsonNodeOf(attributes, "smoke", "threshold").asDouble();
            double smokeScore = JacksonUtil.jsonNodeOf(attributes, "smoke", "score").asDouble();
            if (smokeScore > smoke) {
                driver.setSmoke(1);
            } else {
                driver.setSmoke(0);
            }
            // 打手机
            double cellphone = JacksonUtil.jsonNodeOf(attributes, "cellphone", "threshold").asDouble();
            double cellphoneScore = JacksonUtil.jsonNodeOf(attributes, "cellphone", "score").asDouble();
            if (cellphoneScore > cellphone) {
                driver.setCellphone(1);
            } else {
                driver.setCellphone(0);
            }
            // 未系安全带
            double notBucklingUp = JacksonUtil.jsonNodeOf(attributes, "not_buckling_up", "threshold").asDouble();
            double notBucklingUpScore = JacksonUtil.jsonNodeOf(attributes, "not_buckling_up", "score").asDouble();
            if (notBucklingUpScore > notBucklingUp) {
                driver.setNotBucklingUp(1);
            } else {
                driver.setNotBucklingUp(0);
            }
            // 双手离开方向盘
            double bothHandsLeavingWheel = JacksonUtil.jsonNodeOf(attributes, "both_hands_leaving_wheel", "threshold").asDouble();
            double bothHandsLeavingWheelScore = JacksonUtil.jsonNodeOf(attributes, "both_hands_leaving_wheel", "score").asDouble();
            if (bothHandsLeavingWheelScore > bothHandsLeavingWheel) {
                driver.setBothHandsLeavingWheel(1);
            } else {
                driver.setBothHandsLeavingWheel(0);
            }
            // 视角未朝前方
            double notFacingFront = JacksonUtil.jsonNodeOf(attributes, "not_facing_front", "threshold").asDouble();
            double notFacingFrontScore = JacksonUtil.jsonNodeOf(attributes, "not_facing_front", "score").asDouble();
            if (notFacingFrontScore > notFacingFront) {
                driver.setNotFacingFront(1);
            } else {
                driver.setNotFacingFront(0);
            }
            // 未正确佩戴口罩
            double noFaceMask = JacksonUtil.jsonNodeOf(attributes, "no_face_mask", "threshold").asDouble();
            double noFaceMaskScore = JacksonUtil.jsonNodeOf(attributes, "no_face_mask", "score").asDouble();
            if (noFaceMaskScore > noFaceMask) {
                driver.setNoFaceMask(1);
            } else {
                driver.setNoFaceMask(0);
            }
            // 打哈欠
            double yawning = JacksonUtil.jsonNodeOf(attributes, "yawning", "threshold").asDouble();
            double yawningScore = JacksonUtil.jsonNodeOf(attributes, "yawning", "score").asDouble();
            if (yawningScore > yawning) {
                driver.setYawning(1);
            } else {
                driver.setYawning(0);
            }
            // 闭眼
            double eyesClosed = JacksonUtil.jsonNodeOf(attributes, "eyes_closed", "threshold").asDouble();
            double eyesClosedScore = JacksonUtil.jsonNodeOf(attributes, "eyes_closed", "score").asDouble();
            if (eyesClosedScore > eyesClosed) {
                driver.setEyesClosed(1);
            } else {
                driver.setEyesClosed(0);
            }
            // 低头
            double headLowered = JacksonUtil.jsonNodeOf(attributes, "head_lowered", "threshold").asDouble();
            double headLoweredScore = JacksonUtil.jsonNodeOf(attributes, "head_lowered", "score").asDouble();
            if (headLoweredScore > headLowered) {
                driver.setHeadLowered(1);
            } else {
                driver.setHeadLowered(0);
            }

            Field[] standardFields = DrivingStandard.class.getDeclaredFields();
            Field[] driverFields = DrivingDriver.class.getDeclaredFields();
            for (int j = 7; j < standardFields.length; j++) {
                standardFields[j].setAccessible(true);
                driverFields[j].setAccessible(true);
                Integer standardField = (Integer) standardFields[j].get(standard);
                Integer peopleField = (Integer) driverFields[j].get(driver);
                if (standardField != null && standardField.equals(peopleField)) { // 出现非法信息
                    flag = false;
                    list.add(driver);
                }
            }
        }

        if (flag) { // 图片未出现非法信息
            monitorImg.delete();
        } else {
            // 保存非法监控图像
            DrivingImg drivingImg = new DrivingImg();
            drivingImg.setPath(monitorImg.getPath());
            drivingImg.setUserId(user.getId());
            drivingImgMapper.insert(drivingImg);

            // 保存非法监控信息
            for (DrivingDriver driver : list) {
                driver.setImgId(drivingImg.getId());
                driver.setId(null);
                drivingDriverMapper.insert(driver);

                DrivingStatis statis = drivingStatisService.getOne(new QueryWrapper<DrivingStatis>().eq("user_id", user.getId()).eq("date", DateUtil.toDate(driver.getCreateTime().toString(), "yyyy-MM-dd")));
                if (statis == null) {
                    statis = new DrivingStatis();
                    statis.setDate(DateUtil.toDate(driver.getCreateTime().toString(), "yyyy-MM-dd"));
                    statis.setUserId(user.getId());
                    drivingStatisService.save(statis);
                } else {
                    statis.setTotalNum(statis.getTotalNum() + 1);
                    statis.setUntreatedNum(statis.getUntreatedNum() + 1);
                    drivingStatisService.updateById(statis);
                }
            }
        }
    }

    @Override
    public List<IllegalInfoVo> findImgsAll(User user) {
        List<DrivingImg> drivingImgList = drivingImgMapper.selectList(new QueryWrapper<DrivingImg>().eq("user_id", user.getId()).orderByDesc("create_time"));
        List<IllegalInfoVo> drivingImgVoList = new LinkedList<>();
        for (DrivingImg drivingImg : drivingImgList) {
            List<DrivingDriver> drivingDriverList = drivingDriverMapper.selectList(new QueryWrapper<DrivingDriver>().eq("img_id", drivingImg.getId()));

            IllegalInfoVo illegalImgVo = new IllegalInfoVo();
            BeanUtils.copyBeanProp(illegalImgVo, drivingImg);
            illegalImgVo.setIllegalInfoList(Collections.singletonList(drivingDriverList));

            drivingImgVoList.add(illegalImgVo);
        }
        return drivingImgVoList;
    }
}
