package hu.toll.tollWeb.Repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import hu.toll.tollWeb.Entity.Place;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.List;

@Repository
public class PlaceRepositoryJson {
    public List<Place> findAll() {
        return null;
    }

    Place findPlaceByPhoneNumber(String value) {
        return null;
    }

    public void save(Place newPlace) {
    }

    public void writeJSON(Place newPlace) throws IOException {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.setPrettyPrinting().create();
        FileWriter writer = new FileWriter("places.json");
        writer.write(gson.toJson(newPlace));
        writer.close();
    }

    public Place readJSON() {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        BufferedReader bufferedReader = null;

        try {
            bufferedReader = new BufferedReader(
                    new FileReader("places.json"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Place place = gson.fromJson(bufferedReader, Place.class);
        return place;
    }

    /*
    @Query("SELECT p FROM Place p WHERE p.source =?1 and p.status=?2")
    List<Place> findPlacesBySourceAndStatus(String source, String status);

    @Query(value = "SELECT city FROM place WHERE status=?1", nativeQuery = true)
    public List<String> findCitiesByStatus(String status);
     */
}
