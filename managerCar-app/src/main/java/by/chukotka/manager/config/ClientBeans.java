package by.chukotka.manager.config;


import by.chukotka.manager.client.CarsRentRestClientImpl;
import by.chukotka.manager.security.OAuthClientRequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.web.client.RestClient;

@Configuration
public class ClientBeans {

    @Bean
    public CarsRentRestClientImpl carsRentRestClient(
            @Value("${rentCar.services.catalog.uri:http://localhost:8081}") String catalogBaseUri,
            ClientRegistrationRepository clientRegistrationRepository,
            OAuth2AuthorizedClientRepository authorizedClientRepository,
            @Value("${rentCar.services.catalog.registration-id:keycloak}") String registrationId){
        return new CarsRentRestClientImpl(RestClient.builder()
                .baseUrl(catalogBaseUri)
                .requestInterceptor(
                        new OAuthClientRequestInterceptor(
                                new DefaultOAuth2AuthorizedClientManager(clientRegistrationRepository,
                                        authorizedClientRepository), registrationId))
                .build());
    }
}

