package com.tanghuan.jwt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

/**
 * Created by tanghuan on 2017/3/8.
 */

@Configuration
@Import(value = {JpaConfig.class, WebMvcConfig.class})
@ComponentScan(
        basePackages = {
                "com.tanghuan.jwt.controller",
                "com.tanghuan.jwt.service.impl",
                "com.tanghuan.jwt.exception"
        }
)
public class AppConfig {

    /**
     * 配置文件上传的解析器
     *
     * @return
     */
    @Bean(name = "filterMultipartResolver")
    public CommonsMultipartResolver filterMultipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        // 单位是字节 50 MB = 50000000
        multipartResolver.setMaxUploadSize(70000000);
        multipartResolver.setDefaultEncoding("utf-8");
        return multipartResolver;
    }

}
