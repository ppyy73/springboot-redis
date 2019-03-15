package com.pp.redis.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
	@Bean
	public Docket api() {
		// add head start
		ParameterBuilder tokenPar = new ParameterBuilder();
		List<Parameter> pars = new ArrayList<>();
		tokenPar.name("token").description("token").modelRef(new ModelRef("string")).parameterType("header")
				.required(false).build();
		pars.add(tokenPar.build());
		// add head end
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
				// 自行修改为自己的包路径
				.apis(RequestHandlerSelectors.basePackage("com.pp.redis.controller")).paths(PathSelectors.any()).build()
				.globalOperationParameters(pars);
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("api文档").description("restful 风格接口")
				// 服务条款网址
				// .termsOfServiceUrl("http://blog.csdn.net/forezp")
				.version("1.0")
				// .contact(new Contact("帅呆了", "url", "email"))
				.build();
	}

}
