package com.example.mall.mymall.config;

import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.mall.mymall.entity.MallUser;

import io.swagger.v3.oas.models.media.MediaType;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.RequestParameterBuilder;
import springfox.documentation.schema.Collections;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.schema.ScalarType;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ParameterType;
import springfox.documentation.service.RequestParameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
// import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
// @EnableSwagger2
public class Swagger2Config {

    @Bean
    public Docket api() {
        
        List<Parameter> parameters = new ArrayList<>();
        //  Adding Header
        RequestParameterBuilder aParameterBuilder = new RequestParameterBuilder();
        aParameterBuilder.name("token")
            .query(q -> q.defaultValue("")
                .model(modelSpecificationBuilder -> modelSpecificationBuilder.scalarModel(ScalarType.STRING)))
            .in(ParameterType.HEADER).required(true).build();

        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .ignoredParameterTypes(MallUser.class)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.mall.mymall.controller"))
                .paths(PathSelectors.any())
                .build()
                .globalRequestParameters(getGlobalRequestParameters());
    }

    private List<RequestParameter> getGlobalRequestParameters() {
        List<RequestParameter> parameters = new ArrayList<>();
        parameters.add(new RequestParameterBuilder()
                .name("token")
                .description("login token")
                .required(false)
                .in(ParameterType.HEADER)
                .query(q -> q.model(m -> m.scalarModel(ScalarType.STRING)))
                .build());
        return parameters;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("swagger-api docs")
                .description("swagger doc by songm")
                .version("1.0")
                .build();
    }
}
