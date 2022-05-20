package com.future.observermonitor.service;

import com.future.observercommon.dto.DeviceDTO;
import com.future.observermonitor.vo.ImgVO;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public interface MonitorService {

    /**
     * <获取当前设备的所有非法监控图像VO>
     *
     * @param deviceDTO 监控设备DTO
     * @return 当前设备的所有非法监控图像VO
     * @throws ParseException JSON解析异常
     * @throws IOException    监控图片获取异常
     */
    List<ImgVO> listOfImgVO(DeviceDTO deviceDTO) throws ParseException, IOException;

    /**
     * <调用百度AI接口检测图片>
     * <根据非法信息标准，存储非法的图片及其检测信息，删除合法的图片>
     * <返回非法信息>
     *
     * @param deviceDTO 监控设备DTO
     * @return 非法监控图像VO
     * @throws Exception baidu-ai服务异常或JSON解析异常
     */
    ImgVO check(DeviceDTO deviceDTO) throws Exception;
}
