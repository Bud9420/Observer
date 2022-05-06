package com.future.observermonitor.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.future.observercommon.dto.DeviceDTO;
import com.future.observercommon.dto.UserDTO;
import com.future.observercommon.vo.DeviceVO;
import com.future.observermonitor.po.Device;

import java.text.ParseException;
import java.util.List;

public interface DeviceService extends IService<Device> {

    /**
     * <获取用户的所有设备>
     *
     * @param userDTO useDTO
     * @return 用户的所有设备
     * @throws Exception ysopen-api服务异常
     */
    List<DeviceVO> list(UserDTO userDTO) throws Exception;

    /**
     * <通过萤石开放平台抓取监控图片>
     * <将监控图片的网络路径保存至deviceDTO>
     * <将监控设备的主键id保存至deviceDTO>
     *
     * @param deviceDTO deviceDTO
     * @throws ParseException JSON解析异常
     * @throws Exception      ysopen-api服务异常
     */
    void capture(DeviceDTO deviceDTO) throws ParseException, Exception;
}
