package mx.com.ananda.midgard.configuration;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

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
;import java.util.Collections;

@Configuration
@EnableSwagger2
@Slf4j
public class SwaggerConfig {



    @Bean
    public Docket apiDocket() {

        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("mx.com.ananda.midgard"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(getApiInfo())
                .pathMapping("/midgard/v1")
                ;
    }

    private ApiInfo getApiInfo() {
        return new ApiInfo(
                "MIDGARD",
                "API para la liberacion y gestion de ordenes de venta",
                "1.0",
                "http://codmind.com/terms",
                new Contact("Soporte TI", "lvega@anandaproducts.mx", "lvega@ananda.mx"),
                "LICENSE",
                "LICENSE URL",
                Collections.emptyList()
        );
    }
}
