package br.com.math012.config.openapi;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    public OpenAPI customConfig(){
        return new OpenAPI()
                .info(new Info()
                        .title("Api for a video platform")
                        .version("v1")
                        .description("A simple example of API for a video platform")
                        .termsOfService("https://github.com/Math012")
                        .license(new License()
                                .name("apache 2.0")
                                .url("https://github.com/Math012")));
    }
}