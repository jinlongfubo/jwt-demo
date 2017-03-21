package com.tanghuan.jwt.config;

import org.springframework.context.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

/**
 * Created by tanghuan on 2017/3/8.
 */

@Configuration
@Import(value = {JpaConfig.class, WebSecurityConfig.class})
@ComponentScan(
        basePackages = {
                "com.tanghuan.jwt.controller",
                "com.tanghuan.jwt.service.impl",
                "com.tanghuan.jwt.exception"
        }
)
@PropertySource("classpath:sysconf.properties")
public class AppConfig {

    /**
     * Spring 提供的HTTP Client工具i
     * @return
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
