package by.chukotka.manager.client;

import by.chukotka.manager.controller.payload.CreateCarRentPayload;
import by.chukotka.manager.controller.payload.UpdateCarRentPayload;
import by.chukotka.manager.entity.CarRent;
import by.chukotka.manager.entity.Fuel;
import by.chukotka.manager.entity.Gear;
import by.chukotka.manager.entity.TypeCar;
import by.chukotka.manager.exeption.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RequiredArgsConstructor
public class CarsRentRestClientImpl implements CarsRentRestClient {

    private final RestClient restClient;
    private final static ParameterizedTypeReference<List<CarRent>> CAR_TYPE_REFERENCE =
    new ParameterizedTypeReference<>() {     // это для того что бы JSON понял что мы передаем список машин а не просто неизвестно чего
    };

    @Override
    public List<CarRent> findAllCars() {
        return this.restClient
                .get()
                .uri("/catalog-api/carsRent")
                .retrieve()
                .body(CAR_TYPE_REFERENCE);
    }

    @Override
    public CarRent createCar(String brand, String model, String registrationNumber, short seats, int rentCost, TypeCar type, Gear gear, Fuel fuel) {
        try {
            return this.restClient
                    .post()
                    .uri("/catalog-api/carsRent/")
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(new CreateCarRentPayload(brand, model, registrationNumber, seats, rentCost, type, gear, fuel))
                    .retrieve()
                    .body(CarRent.class);
        }catch (HttpClientErrorException.BadRequest exception){
            ProblemDetail problemDetail = exception.getResponseBodyAs(ProblemDetail.class);
            throw new BadRequestException((List<String>) problemDetail.getProperties().get("errors"));
        }
    }

    @Override
    public Optional<CarRent> findCar(int carId) {
        try {
        return Optional.of(this.restClient.get()
                .uri("/catalog-api/carsRent/{carId}", carId)
                .retrieve()
                .body(CarRent.class));
        }catch (HttpClientErrorException.NotFound exception){
           return Optional.empty();
        }
    }

    @Override
    public void editCar(Integer carId, String brand, String model, String registrationNumber, short seats, int rentCost, TypeCar type, Gear gear, Fuel fuel) {
        try {
            this.restClient
                    .patch()
                    .uri("/catalog-api/carsRent/{carId}", carId)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(new UpdateCarRentPayload(brand, model, registrationNumber, seats, rentCost, type, gear, fuel))
                    .retrieve()
                    .toBodilessEntity();
        }catch (HttpClientErrorException.BadRequest exception){
            ProblemDetail problemDetail = exception.getResponseBodyAs(ProblemDetail.class);
            throw new BadRequestException((List<String>) problemDetail.getProperties().get("errors"));
        }
    }

    @Override
    public void deleteCar(int carId) {
        try {
            this.restClient
                    .delete()
                    .uri("/catalog-api/carsRent/{carId}", carId)
                    .retrieve()
                    .toBodilessEntity();
        }catch (HttpClientErrorException.NotFound exception){
            throw new NoSuchElementException(exception);
        }
    }
}
