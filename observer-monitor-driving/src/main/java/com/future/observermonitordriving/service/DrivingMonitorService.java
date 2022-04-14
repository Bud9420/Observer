package com.future.observermonitordriving.service;

import com.future.observercommon.po.User;
import com.future.observercommon.vo.IllegalInfoVo;

import java.util.List;

/**
 * 应用场景：驾驶行为
 */
public interface DrivingMonitorService {

    /**
     * <通过萤石开发平台抓取监控图片>
     * <调用百度AI接口检测图片>
     * <根据非法信息标准，存储非法的图片及其检测信息，删除合法的图片>
     *
     * @param user 用户信息
     */
    void autoMonitor(User user) throws Exception;

    /**
     * <获取当前用户的所有非法监控图像>
     *
     * @param user 用户信息
     * @return 当前用户的所有非法监控图像
     */
    List<IllegalInfoVo> findImgsAll(User user);
}
