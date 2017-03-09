package com.tanghuan.jwt.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by tanghuan on 2017/3/8.
 * 管理后台登陆
 */

@Configuration
@EnableWebSecurity
@Order(2)
public class AdminWebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/admin/**")
            .csrf().disable()
            .authorizeRequests().anyRequest().authenticated()
            .and()

            // 配置登录
            .formLogin().loginPage("/admin/login.html").loginProcessingUrl("/login")
                .usernameParameter("username").passwordParameter("password")
                .defaultSuccessUrl("/admin/main.html").failureUrl("/admin/login.html?err=true").permitAll()

            // 配置登出
            .and().logout().logoutUrl("/logout").logoutSuccessUrl("/admin/login.html").permitAll()

            // 配置SESSION管理
            .and().sessionManagement().maximumSessions(1).maxSessionsPreventsLogin(true).expiredUrl("/admin/login.html");
    }

}
