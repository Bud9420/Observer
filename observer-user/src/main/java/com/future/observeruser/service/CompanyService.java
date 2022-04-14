package com.future.observeruser.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.future.observercommon.dto.CompanyDTO;
import com.future.observeruser.po.Company;

import java.io.IOException;

public interface CompanyService extends IService<Company> {

    /**
     * <注册>
     *
     * @param companyDTO 注册的企业信息
     * @throws IOException 营业执照创建失败
     */
    void regist(CompanyDTO companyDTO) throws IOException;
}
