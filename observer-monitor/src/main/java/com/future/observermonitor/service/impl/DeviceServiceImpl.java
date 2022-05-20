package com.future.observermonitor.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.databind.JsonNode;
import com.future.observercommon.dto.DeviceDTO;
import com.future.observercommon.dto.UserDTO;
import com.future.observercommon.util.BeanUtil;
import com.future.observercommon.util.JacksonUtil;
import com.future.observermonitor.vo.DeviceVO;
import com.future.observermonitor.mapper.DeviceMapper;
import com.future.observermonitor.po.Device;
import com.future.observermonitor.po.Scene;
import com.future.observermonitor.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class DeviceServiceImpl extends ServiceImpl<DeviceMapper, Device> implements DeviceService {

    @Autowired
    private SceneService sceneService;

    @Autowired
    @SuppressWarnings("all")
    private YSOpenService ysOpenService;

    @Autowired
    private SecretService secretService;

    @Autowired
    @SuppressWarnings("all")
    private DeviceMapper deviceMapper;

    @Override
    public List<DeviceVO> list(UserDTO userDTO) throws Exception {
        List<Device> deviceList = deviceMapper.selectOneByUsername(userDTO.getUsername());

        // 根据用户名，获取监控AccessToken
        String accessToken = secretService.getOne(userDTO).getAccessToken();

        List<DeviceVO> deviceVOList = new ArrayList<>(deviceList.size());
        for (Device device : deviceList) {
            DeviceVO deviceVO = new DeviceVO();
            BeanUtil.copyBeanProp(deviceVO, device);

            /*
             * 获取监控设备的应用场景名，并保存至deviceVO
             */
            Scene scene = sceneService.getOne(new QueryWrapper<Scene>().eq("id", device.getSceneId()));
            deviceVO.setSceneName(scene.getSceneName());

            /*
             * 获取监控设备的状态信息，并保存至deviceVO
             */
            // 封装DeviceDTO
            DeviceDTO deviceDTO = new DeviceDTO();
            BeanUtil.copyBeanProp(deviceDTO, device);
            deviceDTO.setAccessToken(accessToken);

            // 请求获取监控设备的状态信息，返回json结果
            String rs = (String) ysOpenService.getDeviceInfo(deviceDTO).getResult();

            // 获取result失败
            int returnCode = JacksonUtil.jsonNodeOf(rs, "code").asInt();
            if (returnCode == 10002) {
                // 重新获取accessToken
                deviceDTO.setAccessToken(secretService.getAccessTokenAgain(userDTO));
                // 重新获取监控设备的状态信息
                rs = (String) ysOpenService.getDeviceInfo(deviceDTO).getResult();
            }

            // 解析json结果，获取监控设备的状态信息
            JsonNode data = JacksonUtil.jsonNodeOf(rs, "data");
            Integer status = JacksonUtil.jsonNodeOf(data, "status").asInt();
            String signal = JacksonUtil.jsonNodeOf(data, "signal").asText();

            // 保存至deviceVO
            deviceVO.setStatus(status);
            deviceVO.setDeviceSignal(signal);

            deviceVOList.add(deviceVO);
        }

        return deviceVOList;
    }

    @Override
    public void capture(DeviceDTO deviceDTO) throws ParseException, Exception {
        // 抓取监控图片，返回json结果
        String rs = (String) ysOpenService.capture(deviceDTO).getResult();

        // 获取result失败
        int returnCode = JacksonUtil.jsonNodeOf(rs, "code").asInt();
        if (returnCode == 10002) {
            // 重新获取accessToken
            UserDTO userDTO = new UserDTO();
            userDTO.setUsername(deviceDTO.getUsername());
            deviceDTO.setAccessToken(secretService.getAccessTokenAgain(userDTO));
            // 重新抓取监控图片，返回json结果
            rs = (String) ysOpenService.capture(deviceDTO).getResult();
        }

        // 获取监控图片的网络路径，并保存至deviceDTO
        String picUrl = JacksonUtil.jsonNodeOf(rs, "data", "picUrl").asText();
        deviceDTO.setPicUrl(picUrl);
    }

    @Override
    public boolean update(DeviceDTO deviceDTO) {
        Device device = new Device();
        BeanUtil.copyBeanProp(device, deviceDTO);

        return update(device, new UpdateWrapper<Device>().eq("device_serial", device.getDeviceSerial()));
    }
}
