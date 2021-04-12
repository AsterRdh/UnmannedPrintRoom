package com.aster.bcu.printroom.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
* @Author: Wls
* @Date: 17:06 2020/4/6
* @Description: 权限验证拦截器配置 以及 静态资源访问配置
*/
@Configuration
@EnableWebMvc
public class InterceptorConfig implements WebMvcConfigurer {

//    @Resource
//    private AuthInterceptor authInterceptor;
//
//    @Value("${spring.profiles.env}")
//    private String env;

    private Logger logger= LoggerFactory.getLogger(getClass());

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

//        if (env.equals("dev"))
//            logger.info("【开发环境不进行Token验证】");
//        else {
//            logger.info("【Token验证已启用，请在请求头中放置Token】");
//            registry.addInterceptor(authInterceptor).addPathPatterns("/api/**");
//        }
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
        registry.addResourceHandler("/resource/**").addResourceLocations("file:/usr/local/CloudMusic-res/");
//        registry.addResourceHandler("/resource/**").addResourceLocations("file:F:/MyCloudMusic-file/");
    }
}
