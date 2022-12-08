package br.com.aceleragep.avalicao03.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {

	@Bean
	public OpenAPI configuration() {
		OpenAPI openAPI = new OpenAPI();

		Info info = new Info();
		info.title("Avaliação 03");
		info.version("v1");
		info.description("<h2>Avaliação 03:</h2><p>Feita pelos integrantes do Acelera G&P</p>");
		openAPI.info(info);

		return openAPI;
	}

}
