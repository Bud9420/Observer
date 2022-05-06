package com.future.observermonitor.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.future.observercommon.dto.UserDTO;
import com.future.observermonitor.po.Scene;
import com.future.observermonitor.vo.SceneVO;

import java.util.List;

public interface SceneService extends IService<Scene> {

    /**
     * <获取用户拥有的所有应用场景>
     *
     * @param userDTO userDTO
     * @return 用户拥有的所有应用场景
     */
    List<SceneVO> list(UserDTO userDTO);
}
