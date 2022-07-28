package com.example.rabbitexample.infrastructure.swagger;

import static springfox.documentation.spi.DocumentationType.OAS_30;

import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

@Configuration
@EnableOpenApi
public class SwaggerUiConfig {

  @Bean
  public Docket swagger() {
    return new Docket(OAS_30)
        .select()
        .apis(RequestHandlerSelectors.any())
        .paths(PathSelectors.any())
        .build();
  }

  @Primary
  @Bean
  public SwaggerResourcesProvider swaggerResourcesProvider() {
    return () -> List.of(loadResource("person"));
  }

  private SwaggerResource loadResource(String resource) {
    SwaggerResource wsResource = new SwaggerResource();
    wsResource.setName(resource);
    wsResource.setSwaggerVersion("3.0");
    wsResource.setLocation("/openapi/" + resource + ".yaml");
    return wsResource;
  }
}
