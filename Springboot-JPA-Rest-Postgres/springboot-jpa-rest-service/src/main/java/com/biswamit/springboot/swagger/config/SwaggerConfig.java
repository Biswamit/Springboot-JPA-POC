package com.biswamit.springboot.swagger.config;

/*import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static com.google.common.collect.Lists.newArrayList;*/

/*@Configuration*/
/*@EnableSwagger2*/
public class SwaggerConfig {

    /*@Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("com.biswamit.springboot"))
                .paths(PathSelectors.ant("/api/users/*"))
                .build()
                .apiInfo(apiInfo())
                .useDefaultResponseMessages(false)
                .globalResponses(HttpMethod.GET, newArrayList(
                        new ResponseBuilder().code("500")
                                .description("500 message").build(),
                        new ResponseBuilder().code("403")
                                .description("Forbidden!!!!!").build()
                ));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Springboot TE Service REST API")
                .description("Demo project for Spring Boot with LoggedInUser Management")
                .license("Apache 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                .termsOfServiceUrl("www.biswamit.co.in")
                .contact(new Contact("Biswamit Sarkar","www.biswamit.co.in","biswamit.sarkar@gmail.com"))
                .version("1.0.0").build();
    }*/


}
