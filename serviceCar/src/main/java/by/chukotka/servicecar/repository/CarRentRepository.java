package by.chukotka.servicecar.repository;



import by.chukotka.servicecar.entity.CarRent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface CarRentRepository extends JpaRepository<CarRent, Integer> {

    @Query(value = "select * from catalog.t_car_rent where c_brand like :filter", nativeQuery = true)
    Iterable<CarRent> findAllByBrandLikeIgnoreCase(@Param("filter") String filter);


}

// @Query(name = "Car.findAllByBrandLikeIgnoreCase", nativeQuery = true)
//Iterable<CarRent> findAllByBrandLikeIgnoreCase(@Param("filter") String filter);

// @Query(value = "select cr from CarRent cr where cr.brand like :filter")
// Iterable<CarRent> findAllByBrandLikeIgnoreCase(@Param("filter") String filter);

// Iterable<CarRent> findAllByBrandLikeIgnoreCase(String filter);