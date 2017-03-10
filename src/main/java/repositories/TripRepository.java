package repositories;

import domain.Actor;
import domain.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by daviddelatorre on 10/3/17.
 */
@Repository
public interface TripRepository extends JpaRepository<Trip, Integer> {

}
