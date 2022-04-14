package com.future.observermonitorpublic.service;

import com.future.observercommon.dto.UserDTO;
import com.future.observermonitorpublic.vo.PublicIllegalInfoVo;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

/**
 * 应用场景：公共场所
 */
public interface PublicMonitorService {

    /**
     * <通过萤石开放平台抓取监控图片>
     * <调用百度AI接口检测图片>
     * <根据非法信息标准，存储非法的图片及其检测信息，删除合法的图片>
     *
     * @param userDTO 用户信息
     */
    void autoMonitor(UserDTO userDTO) throws Exception;

    /**
     * <获取当前用户的所有非法监控信息>
     *
     * @param userDTO 用户信息
     * @return 当前用户的所有非法监控图像
     */
    List<PublicIllegalInfoVo> findIllegalInfoAll(UserDTO userDTO) throws ParseException, IOException;
}
