package repositories;

import domain.Finder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FinderRepository extends JpaRepository<Finder, Integer> {

}
