package com.future.observeruser.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.future.observercommon.dto.CompanyDTO;
import com.future.observercommon.dto.ImgBasePath;
import com.future.observercommon.util.BeanUtil;
import com.future.observercommon.util.FileUtil;
import com.future.observeruser.mapper.CompanyMapper;
import com.future.observeruser.po.Company;
import com.future.observeruser.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;

import java.io.IOException;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class CompanyServiceImpl extends ServiceImpl<CompanyMapper, Company> implements CompanyService {

    @Autowired
    private ImgBasePath imgBasePath;

    @Override
    public void regist(CompanyDTO companyDTO) throws IOException {
        /*
         * 插入企业
         */
        Company company = new Company();
        BeanUtil.copyBeanProp(company, companyDTO);
        // 保存营业执照
        String path = imgBasePath.getCompanyLicensePath() + company.getName() + "/" + company.getName() + ".jpg";
        FileUtil.createFile(Base64Utils.decodeFromString(companyDTO.getLicense()), path);
        company.setLicensePath(path);
        save(company);
    }
}
