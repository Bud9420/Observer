package com.future.observeruser.controller;

import com.future.observercommon.dto.CompanyDTO;
import com.future.observercommon.vo.ResponseResult;
import com.future.observeruser.service.CompanyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Api("企业的API")
@RestController
@CrossOrigin
@RequestMapping("/companies")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @ApiOperation("注册")
    @ApiImplicitParam(name = "companyDTO", value = "注册的企业信息", required = true)
    @PostMapping("/regist")
    public ResponseResult regist(@RequestBody CompanyDTO companyDTO) throws IOException {
        companyService.regist(companyDTO);
        return ResponseResult.success();
    }
}
