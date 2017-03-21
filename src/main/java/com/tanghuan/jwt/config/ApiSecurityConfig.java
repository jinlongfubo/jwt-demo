//package com.tanghuan.jwt.config;
//
//import com.tanghuan.jwt.security.filter.JwtAuthenEntryPoint;
//import com.tanghuan.jwt.security.filter.JwtAuthenFilter;
//import com.tanghuan.jwt.security.utils.JwtUtil;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
//
///**
// * Created by tanghuan on 2017/3/8.
// */
//
//
//@Configuration
//@EnableWebSecurity
//@Order(1)
//public class ApiSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.antMatcher("/api/**")
//            .csrf().disable()
//            .authorizeRequests().antMatchers("/api/login", "/api/refresh", "/api/logout").permitAll()
//            .anyRequest().authenticated()
//            .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//            .and().exceptionHandling().authenticationEntryPoint(jwtAuthenEntryPoint());
//
//        http.addFilterBefore(jwtAuthenFilter(), BasicAuthenticationFilter.class);
//    }
//
//    @Bean
//    public JwtAuthenFilter jwtAuthenFilter() {
//        return new JwtAuthenFilter();
//    }
//
//    @Bean
//    public JwtAuthenEntryPoint jwtAuthenEntryPoint() {
//        return new JwtAuthenEntryPoint();
//    }
//
//    @Bean
//    public JwtUtil jwtUtil() {
//        return new JwtUtil();
//    }
//}
