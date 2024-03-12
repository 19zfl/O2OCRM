package com.o2ocrm.basic.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @ClassName:CorsConfig
 * @Description: 跨域配置
 * @Author:zfl19
 * @CreateDate:2024/3/12 22:54
 */

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        // 添加CORS配置信息
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        // 添加允许访问的域
        corsConfiguration.addAllowedOrigin("http://localhost:6001");
        corsConfiguration.addAllowedOrigin("http://127.0.0.1:6001");

        // 开启发送Cookie
        corsConfiguration.setAllowCredentials(true);
        // 允许的请求方式
        corsConfiguration.addAllowedMethod("OPTIONS");
        corsConfiguration.addAllowedMethod("HEAD");
        corsConfiguration.addAllowedMethod("GET");
        corsConfiguration.addAllowedMethod("POST");
        corsConfiguration.addAllowedMethod("PUT");
        corsConfiguration.addAllowedMethod("DELETE");
        corsConfiguration.addAllowedMethod("PATCH");
        // 允许的请求头信息
        corsConfiguration.addAllowedHeader("*");
        // 添加映射路径
        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        // 返回配置
        return new CorsFilter(urlBasedCorsConfigurationSource);
    }

}
