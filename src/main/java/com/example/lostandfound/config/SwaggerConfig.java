package com.example.lostandfound.config;


import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI mallTinyOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("校园失误招领系统 API")
                        .description("SpringDoc API 演示")
                        .version("v1.0.0")
                        .license(new License().name("Apache 2.0").url("https://github.com/macrozheng/mall-learning")))
                .externalDocs(new ExternalDocumentation()
                        .description("基于Springboot的全后端分离校园失物招领系统")
                        .url("https://github.com/ilpvc"));
    }


}
