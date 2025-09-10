package hu.toll.tollWeb.Service;

import com.google.api.services.people.v1.model.Person;
import hu.toll.tollWeb.Entity.Place;
import hu.toll.tollWeb.Model.ContactService;
import hu.toll.tollWeb.Repository.PlaceRepositoryJson;
import hu.toll.tollWeb.ServiceInterfaces.DataProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.sql.Date;
import java.util.*;

@Component("DataProcessJSON")
public class DataProcessJson implements DataProcess {
    @Autowired
    PlaceRepositoryJson placeRepo;
    Calendar calendar = Calendar.getInstance();

    public Place converter(Person person) {
        Place newPlace = new Place(
                person.getNames().get(0).getDisplayName(),
                person.getPhoneNumbers().get(0).getValue(),
                new Date(calendar.getTime().getTime()),
                "Aktív",
                "Telefon"
        );
        return newPlace;
    }

    @Override
    public List<Place> getPlaces() {
        return (List<Place>) placeRepo.readJSON();
    }

    @Override
    public void process() throws Exception {
        Set<Person> placesOnPhone = ContactService.getContacts();
        if (placesOnPhone != null && placesOnPhone.size() > 0) {
            List<Place> places = new ArrayList<>();
            for (Person person : placesOnPhone) {
                Place convertedPlace = converter(person);
                places.add(convertedPlace);
            }
            save(places);
        } else {
            System.out.println("No connections found.");
        }
    }

    public String save(List<Place> newPlace) {
        try {
            placeRepo.writeJSON(newPlace);
            return "ok";
        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        }
    }
}