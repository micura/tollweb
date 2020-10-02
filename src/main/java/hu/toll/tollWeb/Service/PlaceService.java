package hu.toll.tollWeb.Service;

import hu.toll.tollWeb.Entity.Place;
import hu.toll.tollWeb.Repository.PlaceRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaceService {
    @Autowired
    private PlaceRepository placeRepo;

    public List<Place> getPlaces() {
        return placeRepo.findAll();
    }

    public String save(Place place) {
        try {
            placeRepo.save(place);
            return "ok";
        }
        catch (DataIntegrityViolationException e) {
            return "alreadyExist" ;
        }
    }

    public List<String> findActiveCities(String status) {
        return placeRepo.findCitiesByStatus(status);
    }
}