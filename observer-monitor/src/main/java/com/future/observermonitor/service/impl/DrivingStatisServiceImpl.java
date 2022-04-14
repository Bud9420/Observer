package com.future.observermonitor.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.future.observercommon.mapper.DrivingStatisMapper;
import com.future.observercommon.po.DrivingStatis;
import com.future.observercommon.service.DrivingStatisService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class DrivingStatisServiceImpl extends ServiceImpl<DrivingStatisMapper, DrivingStatis> implements DrivingStatisService {
}
