package com.future.observermonitor.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.future.observermonitor.mapper.SceneMapper;
import com.future.observermonitor.po.Scene;
import com.future.observermonitor.service.SceneService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class SceneServiceImpl extends ServiceImpl<SceneMapper, Scene> implements SceneService {

}
