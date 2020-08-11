package com.suju.vote.config.webSecurityConfig;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.suju.vote.model.User;
import com.suju.vote.utils.response.RespBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.PrintWriter;

@Slf4j
// @Configuration
// @Order(2)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userService;

    @Autowired
    private LoginFilter loginFilter;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // @Bean
    LoginFilter loginFilter() throws Exception {
        // LoginFilter loginFilter = new LoginFilter();
        loginFilter.setUsernameParameter("name");
        loginFilter.setPasswordParameter("password");
        // 登录时候访问的url
        loginFilter.setFilterProcessesUrl("/web/login");
        loginFilter.setAuthenticationSuccessHandler((request, response, authentication) -> {
            // 登录成功处理器
            response.setContentType("application/json;charset=utf-8");
            PrintWriter out = response.getWriter();
            User user = (User) authentication.getPrincipal();
            user.setPassword(null);
            RespBean res = RespBean.ok("登录成功", user);
            String s = new ObjectMapper().writeValueAsString(res);
            // out.write("登录成功！");
            out.write(s);
            out.flush();
            out.close();
        });
        loginFilter.setAuthenticationFailureHandler(
                (request, response, exception) -> {
                    // 登录失败处理器
                    response.setContentType("application/json;charset=utf-8");
                    PrintWriter out = response.getWriter();
                    String resp = "登录失败!";
                    if (exception instanceof LockedException) {
                        resp += "账户被锁定，请联系管理员!";
                    } else if (exception instanceof CredentialsExpiredException) {
                        resp += "密码过期，请联系管理员!";
                    } else if (exception instanceof AccountExpiredException) {
                        resp += "账户过期，请联系管理员!";
                    } else if (exception instanceof DisabledException) {
                        resp += "账户被禁用，请联系管理员!";
                    } else if (exception instanceof BadCredentialsException || exception instanceof UsernameNotFoundException) {
                        resp += "用户名或者密码输入错误，请重新输入!";
                    }
                    RespBean res = RespBean.error(resp);
                    out.write(new ObjectMapper().writeValueAsString(res));
                    out.flush();
                    out.close();
                }
        );
        loginFilter.setAuthenticationManager(authenticationManager());
        return loginFilter;
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.addFilterAfter(this.loginFilter(), UsernamePasswordAuthenticationFilter.class);
        http.antMatcher("/web/**")
            .authorizeRequests().anyRequest().hasRole("admin")
            .and()
            .logout().logoutUrl("/web/logout")
            .logoutSuccessHandler((request, response, authentication) -> {
                // 登出成功处理器
                response.setContentType("application/json;charset=utf-8");
                PrintWriter out = response.getWriter();
                out.write("登出成功");
                out.flush();
                out.close();
            }).permitAll()
            .and()
            .httpBasic()
            .and()
            .csrf().disable()
            .exceptionHandling()
            //没有认证时，在这里处理结果，不重定向到Spring Security的登录页面
            .authenticationEntryPoint((request, response, exception) -> {
                response.setContentType("application/json;charset=utf-8");
                response.setStatus(401);
                PrintWriter out = response.getWriter();
                RespBean respBean = RespBean.error("访问失败!");
                if (exception instanceof InsufficientAuthenticationException) {
                    respBean.setMsg("登录认证失败");
                }
                out.write(new ObjectMapper().writeValueAsString(respBean));
                out.flush();
                out.close();
            });
    }
}
