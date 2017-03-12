package repositories;

import domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * Created by daviddelatorre on 10/3/17.
 */
@Repository
public interface ApplicationRepository extends JpaRepository<Application, Integer> {




}
