package com.tanghuan.jwt.config;

import com.tanghuan.jwt.security.uds.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Created by tanghuan on 2017/3/8.
 */

@Configuration
@EnableWebSecurity
@Order(2)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web) throws Exception {
        // 配置SpringSecurity忽略静态资源
        web.ignoring().antMatchers("/**/*.css", "/**/*.js", "/**/*.png", "/**/*.jpg", "/**/*.ico");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeRequests().antMatchers("/", "/index", "/index.html").permitAll()
            .anyRequest().authenticated()
            .and()

            // 配置登录
            .formLogin().loginPage("/login.html").loginProcessingUrl("/login")
                .usernameParameter("username").passwordParameter("password")
                .defaultSuccessUrl("/main.html").failureUrl("/login.html?err=true").permitAll()

            // 配置登出
            .and().logout().logoutUrl("/logout").logoutSuccessUrl("/login.html").permitAll()

            // 配置SESSION管理
            .and().sessionManagement().maximumSessions(1).maxSessionsPreventsLogin(true).expiredUrl("/login.html");
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }


    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
