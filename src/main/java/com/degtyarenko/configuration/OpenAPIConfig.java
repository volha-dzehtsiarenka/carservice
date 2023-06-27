package com.degtyarenko.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.degtyarenko.constant.OpenAPIConstant.*;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(apiInfo());
    }

    private Info apiInfo() {
        return new Info()
                .title(SERVICE_API)
                .description(FOR_WORKING_WITH_CAR_SERVICE)
                .version(VERSION)
                .contact(apiContact())
                .license(apiLicence());
    }

    private License apiLicence() {
        return new License()
                .name(APACHE_LICENSE_2_0)
                .url(HTTPS_WWW_APACHE_ORG_LICENSES_LICENSE_2_0_HTML);
    }

    private Contact apiContact() {
        return new Contact()
                .name(DEGTYARENKO_OLGA)
                .email(OLGA_DEGTYARENKO_1_GMAIL_COM);
    }

}
