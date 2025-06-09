package com.heiyuk6.bilihub.infrastructure.config;

import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SaTokenConfig implements WebMvcConfigurer {

    /**
     * 注册 Sa-Token 拦截器
     */
    @Bean
    public SaInterceptor saInterceptor() {
        return new SaInterceptor(handler -> {
            // 如果请求路径是 /auth/** 或 /oauth/**，放行；其余必须登录
            SaRouter
                    // 先把要放行的两个路径全部排除掉
                    .match("/**")
                    .notMatch("/auth/**")
                    .notMatch("/oauth/**")
                    .notMatch("/swagger-ui/**")
                    // 然后对剩下的所有请求做登录校验
                    .check(r -> StpUtil.checkLogin());

        });
    }

}

