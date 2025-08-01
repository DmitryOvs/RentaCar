package by.chukotka.manager.config;


import by.chukotka.manager.client.CarsRentRestClientImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.web.client.RestClient;

@Configuration
public class ClientBeans {

    @Bean
    public CarsRentRestClientImpl carsRentRestClient(
            @Value("${rentCar.services.catalog.uri}") String catalogBaseUrl,
            @Value("${rentCar.services.catalog.username:}") String catalogUsername,
            @Value("${rentCar.services.catalog.password:}") String catalogPassword){
        return new CarsRentRestClientImpl(RestClient.builder()
                .baseUrl(catalogBaseUrl)
                .requestInterceptor(new BasicAuthenticationInterceptor(catalogUsername, catalogPassword))//перехватчик запросов на аутентификацию
                .build());
    }
}
