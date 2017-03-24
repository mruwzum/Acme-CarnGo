package repositories;

import domain.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by daviddelatorre on 10/3/17.
 */
@Repository
public interface ApplicationRepository extends JpaRepository<Application, Integer> {




}
