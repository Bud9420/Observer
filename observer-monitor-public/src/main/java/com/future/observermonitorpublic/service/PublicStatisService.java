package com.future.observermonitorpublic.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.future.observermonitorpublic.dto.PublicStatisDTO;
import com.future.observermonitorpublic.po.PublicStatis;

public interface PublicStatisService extends IService<PublicStatis> {

    /**
     * 非法总数 + 1
     * 未处理的非法数 + 1
     *
     * @param publicStatisDTO 非法统计信息DTO
     */
    void add(PublicStatisDTO publicStatisDTO);
}
