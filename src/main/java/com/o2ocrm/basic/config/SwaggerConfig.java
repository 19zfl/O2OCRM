package com.o2ocrm.basic.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @ClassName:SwaggerConfig
 * @Description: Swagger2的接口配置
 * @Author:zfl19
 * @CreateDate:2024/3/9 16:45
 *
 * Swagger固定地址：http://ip:port/swagger-ui.html
 *
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /**
     * 创建api
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                // 用来创建该API的基本信息，展示在文档的页面中（自定义展示的信息）
                .apiInfo(apiInfo())
                // 设置哪些接口暴露给Swagger展示
                .select()
                // 扫描所有有注解的api，用这种方式更灵活
                // .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                // 扫描指定包中的swagger注解
                .apis(RequestHandlerSelectors.basePackage("com.o2ocrm"))
                // 扫描所有 .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 添加摘要信息
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                // 设置标题
                .title("标题：项目接口文档")
                // 描述
                .description("描述：本项目有XXX、XXX、XXX等模块")
                // 作者信息
                .contact(new Contact("zfl19", "https://github.com/19zfl/O2OCRM.git", "19aleiya@gmail.com"))
                // 版本
                .version("1.0")
                .build();
    }

}
