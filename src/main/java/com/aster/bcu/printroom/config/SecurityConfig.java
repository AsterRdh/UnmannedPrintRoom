package com.aster.bcu.printroom.config;

import com.aster.bcu.printroom.service.impl.CustomUserDetailsService;
import com.aster.bcu.printroom.tools.MyPasswordEncoder;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    @Qualifier("userDetailsService")
    private CustomUserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new MyPasswordEncoder());

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

//        http.authorizeRequests()
//                .antMatchers("/res/**").permitAll().and().authorizeRequests().and().formLogin();
        http.formLogin()
                    .loginPage("http://localhost:8001")
                    .loginProcessingUrl("/login/p")
                    .usernameParameter("username")
                    .passwordParameter("password")
                    .permitAll()
                    .failureHandler(new AuthenticationFailureHandler() {
                        @Override
                        public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
                            httpServletResponse.setContentType("application/json;charset=utf-8");
                            PrintWriter out = httpServletResponse.getWriter();
                            StringBuffer sb = new StringBuffer();
                            sb.append("{\"status\":\"error\",\"msg\":\"");
                            if (e instanceof UsernameNotFoundException || e instanceof BadCredentialsException) {
                                sb.append("用户名或密码输入错误，登录失败!");
                            } else if (e instanceof DisabledException) {
                                sb.append("账户被禁用，登录失败，请联系管理员!");
                            } else {
                                sb.append("登录失败!");
                            }
                            sb.append("\"}");
                            out.write(sb.toString());
                            out.flush();
                            out.close();
                        }
                    })
                    .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
                        httpServletResponse.setContentType("application/json;charset=utf-8");
                        PrintWriter out = httpServletResponse.getWriter();
                        httpServletRequest.getSession().setAttribute(
                                HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                                SecurityContextHolder.getContext()
                                );
                        String sessionId = httpServletRequest.getSession().getId();

                        String s = "{\"status\":\"success\",\"msg\":" + sessionId + "}";
                        Map<String,String> map=new HashMap<>();
                        map.put("status","success");
                        map.put("msg",sessionId);
                        out.write(new Gson().toJson(map));
                        out.flush();
                        out.close();
                    }})
                .and().logout()
                    .permitAll()
                .and().csrf()
                    .disable()
                    .exceptionHandling()
                .and().authorizeRequests()
                    .antMatchers(
                            "/resource/**",
                            "/api/file/upload",
                            "/ad/adSrc",
                            "/doSocket/pushVideoListToWeb","/myWs","/lr/**","/test/**").permitAll()
                    .anyRequest().authenticated()
        ;


    }
}
