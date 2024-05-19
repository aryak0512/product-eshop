package com.aryak.product;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springdoc.core.models.GroupedOpenApi;
import org.springdoc.core.properties.SpringDocConfigProperties;
import org.springdoc.core.properties.SwaggerUiConfigParameters;
import org.springdoc.core.properties.SwaggerUiConfigProperties;
import org.springdoc.core.providers.SpringWebProvider;
import org.springdoc.webmvc.ui.SwaggerWelcomeWebMvc;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Profile;

/**
 * This class is a Swagger configuration class.
 *
 * @author Aryak D.
 * @version 1.0
 * @since 19/02/2023
 */
@Configuration
@Profile(value = { "dev", "qa", "uat" })
public class OpenApiConfiguration {

    @Bean
    public OpenAPI configuration() {

        Contact contact = new Contact();
        contact.setEmail("aryak.deshpande0512@gmail.com");
        contact.setName("Aryak Deshpande");
        contact.setUrl("https://www.aryakdeshpande.netlify.app");

        return new OpenAPI().info(new Info().title("Product App").contact(contact).description(
                        "This is a microservice for product app. API docs and actuator endpoints are exposed on management server port.")
                .version("v 1.0"));
    }

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder().group("products").pathsToMatch("/**").build();
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(name = { "springdoc.use-management-port" }, havingValue = "false", matchIfMissing = true)
    public SwaggerWelcomeWebMvc swaggerWelcome(	SwaggerUiConfigProperties swaggerUiConfig,
                                                SpringDocConfigProperties springDocConfigProperties,
                                                SwaggerUiConfigParameters swaggerUiConfigParameters,
                                                SpringWebProvider springWebProvider) {

        return new SwaggerWelcomeWebMvc(swaggerUiConfig, springDocConfigProperties, swaggerUiConfigParameters, springWebProvider);
    }

}