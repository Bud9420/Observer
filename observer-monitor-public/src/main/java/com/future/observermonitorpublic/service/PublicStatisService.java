package com.future.observermonitorpublic.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.future.observercommon.vo.PublicStatisVO;
import com.future.observercommon.dto.PublicStatisDTO;
import com.future.observermonitorpublic.po.PublicStatis;

import java.text.ParseException;
import java.util.List;

public interface PublicStatisService extends IService<PublicStatis> {

    /**
     * <获取非法统计数据>
     *
     * @param publicStatisDTO 非法统计信息DTO
     * @return 非法统计数据
     * @throws ParseException 日期解析异常
     */
    List<PublicStatisVO> listByPublicStatisDTO(PublicStatisDTO publicStatisDTO) throws ParseException;

    /**
     * <非法总数 + 1>
     * <未处理的非法数 + 1>
     *
     * @param publicStatisDTO 非法统计信息DTO
     */
    void add(PublicStatisDTO publicStatisDTO);
}
