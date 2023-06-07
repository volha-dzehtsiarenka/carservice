package com.degtyarenko.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.degtyarenko.constant.OpenAPIConstant.APACHE_LICENSE_2_0;
import static com.degtyarenko.constant.OpenAPIConstant.DEGTYARENKO_OLGA;
import static com.degtyarenko.constant.OpenAPIConstant.FOR_WORKING_WITH_CAR_SERVICE;
import static com.degtyarenko.constant.OpenAPIConstant.HTTPS_WWW_APACHE_ORG_LICENSES_LICENSE_2_0_HTML;
import static com.degtyarenko.constant.OpenAPIConstant.OLGA_DEGTYARENKO_1_GMAIL_COM;
import static com.degtyarenko.constant.OpenAPIConstant.SERVICE_API;
import static com.degtyarenko.constant.OpenAPIConstant.VERSION;

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
