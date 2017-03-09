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

//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        // 配置SpringSecurity忽略静态资源
//        web.ignoring().antMatchers("/**/*.css", "/**/*.js", "/**/*.png", "/**/*.jpg", "/**/*.ico");
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService());
//    }

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

//    @Bean
//    public UserDetailsService userDetailsService() {
//        return new UserDetailsServiceImpl();
//    }
//
//
//    @Bean(BeanIds.AUTHENTICATION_MANAGER)
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder(8);
//    }

}
