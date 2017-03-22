package services;

import domain.Finder;
import domain.Trip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import repositories.FinderRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
public class FinderService {

	// Managed Repository ------------------------
	@Autowired
	private FinderRepository finderRepository;
	@Autowired
	private TripService tripService;
	// Constructor -------------------------------
	public FinderService() {
		super();
	}

	// Supporting services -----------------------

	// Simple CRUD methods -----------------------
	
	public Finder create() {
		return new Finder();
	}

	public Finder findOne(int finderId) {
		Finder result;

		result = finderRepository.findOne(finderId);

		return result;
	}

	public Collection<Finder> findAll() {
		Collection<Finder> result;

		result = finderRepository.findAll();

		return result;
	}

	public Finder save(Finder finder) {
		Assert.notNull(finder);
		return finderRepository.save(finder);
	}
	
	public void delete(Finder finder) {
		Assert.notNull(finder);
		Assert.isTrue(finderRepository.exists(finder.getId()));
		finderRepository.delete(finder);
	}



	// Other business methods -----------------------

	public List<Trip> finder(String title, String description, String originAddress, String destinationAddress, String keyword){
       Assert.notNull(keyword);
        List<Trip> properties = new ArrayList<>(tripService.findAll());
        List<Trip> aux = new ArrayList<>();
        for (Trip p : properties){
			if (p.getTitle().equals(title)||p.getDestinationAddress().equals(destinationAddress)||p.getOriginAddress().equals(originAddress)||p.getDescription().equals(description)&& containsKey(properties,keyword)){
                   aux.add(p);
               }
            }
            return aux;

	}
	private Boolean containsKey(List<Trip> properties, String keyword){
            Boolean res = false;
            for (Trip p : properties){
                if (p.getTitle().contains(keyword) || p.getDescription().contains(keyword) || p.getDestinationAddress().contains(keyword)||p.getOriginAddress().contains(keyword)){
                    res = true;
                }
            }
            return res;
    }

}