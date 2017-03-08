package com.tanghuan.jwt.config;

import org.springframework.mobile.device.DeviceResolverRequestFilter;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.multipart.support.MultipartFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * Created by tanghuan on 2017/3/8.
 */

public class ServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{AppConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{WebMvcConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext);

        // 配置请求编码处理Filter
        CharacterEncodingFilter setCharacterEncoding = new CharacterEncodingFilter("UTF-8", true);
        servletContext.addFilter("setCharacterEncoding", setCharacterEncoding).addMappingForUrlPatterns(null, false, "/*");

        // 配置JPA懒处理的过滤器
        OpenEntityManagerInViewFilter openEntityManagerInViewFilter = new OpenEntityManagerInViewFilter();
        FilterRegistration.Dynamic openEntityManagerInView = servletContext.addFilter("openEntityManagerInViewFilter", openEntityManagerInViewFilter);
        openEntityManagerInView.setInitParameter("entityManagerFactoryBeanName", "entityManagerFactory");
        openEntityManagerInView.addMappingForUrlPatterns(null, false, "/*");

        // 配置处理文件上传的过滤器
        servletContext.addFilter("MultipartFilter", MultipartFilter.class).addMappingForUrlPatterns(null, false, "/*");

        // 配置设备过滤器
        servletContext.addFilter("deviceResolverRequestFilter", DeviceResolverRequestFilter.class).addMappingForUrlPatterns(null, false, "/*");

        // 配置SpringSecurity过滤器链代理的Filter
        DelegatingFilterProxy springSecurityFilterChain = new DelegatingFilterProxy();
        servletContext.addFilter("springSecurityFilterChain", springSecurityFilterChain).addMappingForUrlPatterns(null, false, "/*");

    }
}
