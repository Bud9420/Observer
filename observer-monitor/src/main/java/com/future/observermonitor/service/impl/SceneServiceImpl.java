package com.future.observermonitor.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.future.observercommon.dto.UserDTO;
import com.future.observercommon.util.BeanUtil;
import com.future.observermonitor.mapper.SceneMapper;
import com.future.observermonitor.po.Scene;
import com.future.observermonitor.service.SceneService;
import com.future.observermonitor.vo.SceneVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class SceneServiceImpl extends ServiceImpl<SceneMapper, Scene> implements SceneService {

    @Autowired
    @SuppressWarnings("all")
    private SceneMapper sceneMapper;

    @Override
    public List<SceneVO> list(UserDTO userDTO) {
        List<Scene> sceneList = sceneMapper.selectOneByUsername(userDTO.getUsername());

        List<SceneVO> sceneVOList = new ArrayList<>(sceneList.size());

        for (Scene scene : sceneList) {
            SceneVO sceneVO = new SceneVO();
            BeanUtil.copyBeanProp(sceneVO, scene);

            sceneVOList.add(sceneVO);
        }

        return sceneVOList;
    }
}
