package com.future.observermonitor.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.future.observercommon.dto.DeviceDTO;
import com.future.observercommon.dto.UserDTO;
import com.future.observercommon.util.BeanUtil;
import com.future.observercommon.util.JacksonUtil;
import com.future.observermonitor.mapper.DeviceMapper;
import com.future.observermonitor.po.Device;
import com.future.observermonitor.po.Secret;
import com.future.observermonitor.service.DeviceService;
import com.future.observermonitor.service.SecretService;
import com.future.observermonitor.service.YSOpenService;
import com.future.observermonitor.vo.DeviceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class DeviceServiceImpl extends ServiceImpl<DeviceMapper, Device> implements DeviceService {

    @Autowired
    private YSOpenService ysOpenService;

    @Autowired
    private SecretService secretService;

    @Override
    public List<DeviceVO> listByUserDTO(UserDTO userDTO) {
        List<Device> deviceList = list(new QueryWrapper<Device>().eq("user_id", userDTO.getId()));
        List<DeviceVO> deviceVOList = new ArrayList<>();
        for (Device device : deviceList) {
            DeviceVO deviceVO = new DeviceVO();
            BeanUtil.copyBeanProp(deviceVO, device);
            deviceVOList.add(deviceVO);
        }
        return deviceVOList;
    }

    @Override
    public String getAccessToken(DeviceDTO deviceDTO) throws Exception {
        // 获取监控AccessToken
        String accessToken = (String) ysOpenService.getAccessToken(deviceDTO).getResult();

        // 根据设备号更新设备的AccessToken
        secretService.update(null, new UpdateWrapper<Secret>()
                .set("access_token", accessToken)
                .eq("user_id", deviceDTO.getUserId())
        );

        // 返回AccessToken
        return accessToken;
    }

    @Override
    public void capture(DeviceDTO deviceDTO) throws Exception {
        // 抓取监控图片，返回json结果
        String rs = (String) ysOpenService.capture(deviceDTO).getResult();

        // 获取result失败
        int returnCode = JacksonUtil.jsonNodeOf(rs, "code").asInt();
        if (returnCode == 10002) {
            // 重新获取accessToken
            String accessToken = getAccessToken(deviceDTO);
            deviceDTO.setAccessToken(accessToken);
            // 重新抓取监控图片，返回json结果
            rs = (String) ysOpenService.capture(deviceDTO).getResult();
        }

        // 获取监控图片的网络路径，并保存至deviceDTO
        String picUrl = JacksonUtil.jsonNodeOf(rs, "data", "picUrl").asText();
        deviceDTO.setPicUrl(picUrl);

        // 获取监控设备的主键id，并保存至deviceDTO
        getId(deviceDTO);
    }

    @Override
    public void getId(DeviceDTO deviceDTO) {
        Device device = getOne(new QueryWrapper<Device>().eq("device_serial", deviceDTO.getDeviceSerial()));
        deviceDTO.setDeviceId(device.getId());
    }
}
