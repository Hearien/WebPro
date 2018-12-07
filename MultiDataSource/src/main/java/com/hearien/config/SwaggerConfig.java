package com.hearien.config;

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
 * @ClassName SwaggerConfig
 * @Author WangHaiyang
 * @Date 2018/10/29 9:44
 **/
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /**
     *
     * @return Docket
     */
    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.hearien.controller"))
                //.apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }


    /**
     *
     * @return ApiInf
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("DEMO API").description("")
                .termsOfServiceUrl("https://www.example.com/api")
                .contact(new Contact("Developers", "https://hearien.github.io", "ysilence@qq.com"))
                .license("Open Source")
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
                .version("1.0.0")
                .build();
    }

}
