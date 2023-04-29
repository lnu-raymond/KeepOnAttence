package com.leevan.configure;

import com.leevan.Interceptor.UserAuthenticationIntercepter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @projectName:    KeepOnAttence 
 * @package:        com.leevan.configure
 * @className:      WebMvcConfig
 * @author:     冯莉文
 * @description:  TODO  
 * @date:    2023/3/25 20:16
 * @version:    1.0
 */ 
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {



    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor( UsertokenRefreshInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/user/login","/user/register","**/export","/test","/attendance/**","/procedure/submit","/file/upload","/file/down/*","/rfid/**");
    }

    @Bean
    public UserAuthenticationIntercepter UsertokenRefreshInterceptor(){
        return new UserAuthenticationIntercepter();
    }


}