package com.tanghuan.jwt.config;

import com.tanghuan.jwt.security.adm.AccessDecisionManagerImpl;
import com.tanghuan.jwt.security.filter.JwtAuthenEntryPoint;
import com.tanghuan.jwt.security.filter.JwtAuthenFilter;
import com.tanghuan.jwt.security.smds.FilterInvocationSecurityMetadataSourceImpl;
import com.tanghuan.jwt.security.uds.UserDetailsServiceImpl;
import com.tanghuan.jwt.security.utils.JwtUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

/**
 * Created by Arthur on 2017/3/21.
 */

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web) throws Exception {
        // 配置SpringSecurity忽略静态资源
        web.ignoring().antMatchers("/**/*.css", "/**/*.js", "/**/*.png", "/**/*.jpg", "/**/*.ico");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
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

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(8);
    }


    @Bean
    public FilterInvocationSecurityMetadataSource filterInvocationSecurityMetadataSource() {
        return new FilterInvocationSecurityMetadataSourceImpl();
    }

    @Bean
    public AccessDecisionManager accessDecisionManager() {
        return new AccessDecisionManagerImpl();
    }

    /**
     * 注册Session统计在线人数
     * @return
     */
    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

    /**
     * 普通用户访问控制配置
     */
    @Configuration
    @Order(3)
    public class UserWebSecurityConfig extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.csrf().disable()
                    .authorizeRequests().antMatchers("/", "/index", "/index.html").permitAll()
                    .anyRequest().authenticated().withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                public <O extends FilterSecurityInterceptor> O postProcess(O fsi) {
                    // 添加自定义的SecurityMetadataSource
                    fsi.setSecurityMetadataSource(filterInvocationSecurityMetadataSource());

                    // 添加自定义的权限鉴定
                    fsi.setAccessDecisionManager(accessDecisionManager());
                    return fsi;
                }
            })
                    .and()

                    // 配置登录
                    .formLogin().loginPage("/login.html").loginProcessingUrl("/login")
                    .usernameParameter("username").passwordParameter("password")
                    .defaultSuccessUrl("/main.html").failureUrl("/login.html?err=true").permitAll()

                    // 配置登出
                    .and().logout().logoutUrl("/logout").logoutSuccessUrl("/login.html?logout=true").permitAll()

                    // 配置SESSION管理
                    .and().sessionManagement().maximumSessions(1).maxSessionsPreventsLogin(true).expiredUrl("/login.html").sessionRegistry(sessionRegistry());
        }

    }

    /**
     * 管理员访问控制配置
     */
    @Configuration
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
                    .and().sessionManagement().maximumSessions(1).maxSessionsPreventsLogin(true).expiredUrl("/admin/login.html").sessionRegistry(sessionRegistry());
        }

    }

    /**
     * API接口的访问控制
     */
    @Configuration
    @Order(1)
    public class ApiSecurityConfig extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.antMatcher("/api/**")
                    .csrf().disable()
                    .authorizeRequests().antMatchers("/api/login", "/api/refresh", "/api/logout").permitAll()
                    .anyRequest().authenticated()
                    .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and().exceptionHandling().authenticationEntryPoint(jwtAuthenEntryPoint());

            http.addFilterBefore(jwtAuthenFilter(), BasicAuthenticationFilter.class);
        }

        @Bean
        public JwtAuthenFilter jwtAuthenFilter() {
            return new JwtAuthenFilter();
        }

        @Bean
        public JwtAuthenEntryPoint jwtAuthenEntryPoint() {
            return new JwtAuthenEntryPoint();
        }

        @Bean
        public JwtUtil jwtUtil() {
            return new JwtUtil();
        }
    }
}