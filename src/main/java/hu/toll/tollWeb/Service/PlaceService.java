package hu.toll.tollWeb.Service;

import hu.toll.tollWeb.Entity.Place;
import hu.toll.tollWeb.Repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaceService {
    @Autowired
    private PlaceRepository placeRepo;

    public List<Place> getPlaces() {
        return placeRepo.findAll();
    }
}