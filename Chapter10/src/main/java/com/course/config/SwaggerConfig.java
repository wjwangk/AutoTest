package com.course.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger 可以自动生成接口文档
 * 生成接口文档后，访问url为：http://localhost:8899/swagger-ui.html
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .pathMapping("/")
                .select()
                .paths(PathSelectors.regex("/.*"))
                .build();

    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder().title("我的第一个接口文档")
                .contact(new Contact("wwj","","243227401"))
                .description("这是我的swaggerUI生成的接口文档")
                .version("1.0.0.0")   //企业版本号一般四位
                .build();
    }
}
