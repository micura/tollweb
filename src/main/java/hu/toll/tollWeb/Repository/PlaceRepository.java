package hu.toll.tollWeb.Repository;

import hu.toll.tollWeb.Entity.Place;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaceRepository extends CrudRepository<Place, Long> {
    List<Place> findAll();
    Place findPlaceByPhoneNumber(String value);
    List<Place> findByStatus(String status);


    @Query(value = "SELECT city FROM place WHERE status=?1", nativeQuery = true)
    public List<String> findCitiesByStatus(String status);

}
