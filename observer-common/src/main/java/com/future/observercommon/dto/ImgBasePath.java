package com.future.observercommon.dto;

import com.future.observercommon.util.FileUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.lang.reflect.Field;

/**
 * 图片存储路径
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ImgBasePath {

    /*
     * 存储图片的根目录
     */
    private String imgPath;

    /*
     * 存储监控图片
     */
    // 根目录
    private String monitorPath;

    /*
     * 存储企业信息
     */
    // 根目录
    private String companyPath;

    // 存储营业执照的根目录
    private String companyLicensePath;

    /*
     * 存储用户信息
     */
    // 根目录
    private String userPath;

    // 存储用户头像的根目录
    private String userHeadImgPath;

    @PostConstruct
    public void init() throws IllegalAccessException, IOException {
        Class<ImgBasePath> cls = ImgBasePath.class;
        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            String path = (String) field.get(this);
            FileUtil.createDir(path);
        }
    }
}
