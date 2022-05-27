package com.future.observermonitor.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.future.observercommon.util.BeanUtil;
import com.future.observermonitor.mapper.SceneMapper;
import com.future.observermonitor.po.Scene;
import com.future.observermonitor.service.SceneService;
import com.future.observermonitor.vo.SceneVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class SceneServiceImpl extends ServiceImpl<SceneMapper, Scene> implements SceneService {

    @Override
    public List<SceneVO> listOfVO() {
        List<Scene> sceneList = list();

        List<SceneVO> sceneVOList = new ArrayList<>(sceneList.size());
        BeanUtil.copyListProp(sceneVOList, sceneList, SceneVO.class);

        return sceneVOList;
    }
}
