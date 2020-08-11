package com.suju.vote.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Slf4j
@Configuration
// @Order(1)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // @Autowired
    // public WebSecurityConfig WebSecurityConfig;

    @Override
    public void configure(WebSecurity web) throws Exception {
        // 忽视swaggger2
        // web.ignoring().antMatchers( "/v2/api-docs", "/definitions/**", "/configuration/ui", "/swagger-resources/**", "/configuration/security", "/swagger-ui.html", "/webjars/**","/swagger-resources/configuration/ui",
                // "/css/**","/js/**", "/index.html","/img/**","/fonts/**","/favicon.ico");
        // 忽略的url无法获取当前用户的登录信息
        // web.ignoring().antMatchers("/wx/user/**","/css/**","/js/**","/index.html","/img/**","/fonts/**","/favicon.ico");
        web.ignoring().antMatchers("/*/**");
    }

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
