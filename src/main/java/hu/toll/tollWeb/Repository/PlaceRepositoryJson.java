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
        //TODO
        return null;
    }

    Place findPlaceByPhoneNumber(String value) {
        //TODO
        return null;
    }

    public void save(Place newPlace) {
        //TODO
    }

    public void writeJSON(List<Place> places) throws IOException {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.setPrettyPrinting().create();
        FileWriter writer = new FileWriter("places.json");
        writer.write(gson.toJson(places));
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
}
