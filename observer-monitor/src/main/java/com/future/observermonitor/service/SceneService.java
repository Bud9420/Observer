package com.future.observermonitor.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.future.observermonitor.po.Scene;
import com.future.observermonitor.vo.SceneVO;

import java.util.List;

public interface SceneService extends IService<Scene> {

    /**
     * <获取所有应用场景VO>
     *
     * @return 所有应用场景VO
     */
    List<SceneVO> listOfVO();
}
