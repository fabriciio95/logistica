package com.log.api.doc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Response;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.log.api.controller"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(apiInfo())
				.useDefaultResponseMessages(false)
				.globalResponses(HttpMethod.GET, responseMessageForGet())
				.globalResponses(HttpMethod.POST, responseMessageForPost())
				.globalResponses(HttpMethod.PUT, responseMessageForPut())
				.globalResponses(HttpMethod.DELETE, responseMessageForDelete());
	}
	
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("Logística API")
				.description("Uma API de logística, onde clientes podem solicitar entregas,"
						+ " sendo que cada entrega tem ocorrências, objetos e um motorista responsável.")
				.contact(new Contact("Fabricio Siqueira Macedo", "https://github.com/fabriciio95",
						"fabriciousiqueira@gmail.com"))
				.build();
	}

	
	private List<Response> responseMessageForGet() {
		return new ArrayList<>() {
			private static final long serialVersionUID = 1L;
		{
			add(new ResponseBuilder()
					.code("500")
					.description("Erro interno no servidor")
					.build());
			add(new ResponseBuilder()
					.code("401")
					.description("Não autorizado")
					.build());
		}};
	}
	
	private List<Response> responseMessageForPost() {
		return new ArrayList<>() {
			private static final long serialVersionUID = 1L;
			{
				add(new ResponseBuilder().code("401").description("Não autorizado").build());
				add(new ResponseBuilder().code("400").description("Bad Request").build());
				add(new ResponseBuilder().code("500").description("Erro interno no servidor").build());
				
			}
		};
	}
	
	private List<Response> responseMessageForPut() {
		return new ArrayList<>() {
			private static final long serialVersionUID = 1L;
			{
				add(new ResponseBuilder().code("401").description("Não autorizado").build());
				add(new ResponseBuilder().code("400").description("Bad Request").build());
				add(new ResponseBuilder().code("404").description("Não Encontrado").build());
				add(new ResponseBuilder().code("405").description("Método não permitido").build());
				add(new ResponseBuilder().code("500").description("Erro interno no servidor").build());
			}
		};
	}
	
	
	private List<Response> responseMessageForDelete() {
		return new ArrayList<>() {
			private static final long serialVersionUID = 1L;
			{
				add(new ResponseBuilder().code("401").description("Não autorizado").build());
				add(new ResponseBuilder().code("404").description("Não Encontrado").build());
				add(new ResponseBuilder().code("405").description("Método não permitido").build());
				add(new ResponseBuilder().code("500").description("Erro interno no servidor").build());
			}
		};
	}
}
