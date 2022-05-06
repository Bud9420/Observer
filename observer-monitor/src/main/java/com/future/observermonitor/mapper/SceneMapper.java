package com.future.observermonitor.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.future.observermonitor.po.Scene;

import java.util.List;

public interface SceneMapper extends BaseMapper<Scene> {

    List<Scene> selectOneByUsername(String username);
}
