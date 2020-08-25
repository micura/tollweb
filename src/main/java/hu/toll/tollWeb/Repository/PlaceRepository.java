package hu.toll.tollWeb.Repository;

import hu.toll.tollWeb.Entity.Place;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PlaceRepository extends CrudRepository<Place, Long> {
    List<Place> findAll();

    Place findPlaceByPhoneNumber(String value);
}
