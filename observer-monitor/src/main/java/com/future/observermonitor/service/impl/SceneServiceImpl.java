package com.future.observermonitor.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.future.observercommon.dto.UserDTO;
import com.future.observermonitor.mapper.SceneMapper;
import com.future.observermonitor.mapper.UserSceneMapper;
import com.future.observermonitor.po.Scene;
import com.future.observermonitor.po.UserScene;
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
    private UserSceneMapper userSceneMapper;

    @Override
    public List<SceneVO> listByUserDTO(UserDTO userDTO) {
        // 根据用户id，获取UserScene列表
        List<UserScene> userSceneList = userSceneMapper.selectList(new QueryWrapper<UserScene>().eq("user_id", userDTO.getUserId()));

        List<SceneVO> sceneVOList = new ArrayList<>(userSceneList.size());

        for (UserScene userScene : userSceneList) {
            // 根据场景id，获取Scene
            Scene scene = getOne(new QueryWrapper<Scene>().eq("id", userScene.getSceneId()));

            SceneVO sceneVO = new SceneVO();
            sceneVO.setSceneName(scene.getName());
            sceneVO.setDeviceNum(userScene.getDeviceNum());

            sceneVOList.add(sceneVO);
        }

        return sceneVOList;
    }
}
