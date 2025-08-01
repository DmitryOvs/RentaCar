package by.chukotka.manager.repository;



import by.chukotka.manager.entity.RolfUser;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RolfUserRepository extends CrudRepository<RolfUser, Integer> {

    Optional<RolfUser> findByUserName(String username);
}
