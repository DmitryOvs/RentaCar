package by.chukotka.manager.config;


import by.chukotka.manager.client.CarsRentRestClientImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class ClientBeans {

    @Bean
    public CarsRentRestClientImpl carsRentRestClient(
            @Value("${rentCar.services.catalog.uri}") String catalogueBaseUrl){
        return new CarsRentRestClientImpl(RestClient.builder()
                .baseUrl(catalogueBaseUrl)
                .build());
    }
}
