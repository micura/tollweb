package hu.toll.tollWeb.Service;

import com.google.api.services.people.v1.model.Person;
import com.google.gson.Gson;
import hu.toll.tollWeb.Entity.Place;

import java.sql.Date;

import hu.toll.tollWeb.Model.ContactService;
import hu.toll.tollWeb.Repository.PlaceRepository;
import hu.toll.tollWeb.ServiceInterfaces.DataProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Calendar;
import java.util.List;

@Component("DataProcessContact")
public class DataProcessContact implements DataProcess {
    @Autowired
    PlaceRepository placeRepo;
    Calendar calendar = Calendar.getInstance();

    /*
    public void createJsonObject(Person person) {
        try {
            Place newTempPlace = new Place();
            Date syncDate = new Date(calendar.getTime().getTime());

            newTempPlace.setName(person.getNames().get(0).getDisplayName());
            newTempPlace.setPhoneNumber(person.getPhoneNumbers().get(0).getValue());
            newTempPlace.setSyncDate(syncDate);
            newTempPlace.setSource("Telefon");

            Gson gson = new Gson();
            String json = gson.toJson(newTempPlace);

            System.out.println(json);
        } catch (Exception e) {
            System.out.println("Hiba történt: " + e);
        }
    }
    */

    public void loadToDataBase(Person person) {
        try {
            Place newPlace = new Place();
            Date syncDate = new Date(calendar.getTime().getTime());

            newPlace.setName(person.getNames().get(0).getDisplayName());
            newPlace.setPhoneNumber(person.getPhoneNumbers().get(0).getValue());
            newPlace.setSyncDate(syncDate);
            newPlace.setSource("Telefon");

            placeRepo.save(newPlace);
        } catch (Exception e) {
            System.out.println("Hiba történt: " + e);
        }
    }

    public boolean checkExists(Person person) {
        Place placeCheck = placeRepo.findPlaceByPhoneNumber(person.getPhoneNumbers().get(0).getValue());
        if (placeCheck != null) {
            return true;
        }
        return false;
    }

    //TODO
    //Már az adatbázisban meglévő törölve lett a telefonból?

    @Override
    public void process() throws Exception {
        List<Person> connections = ContactService.getContacts();
        if (connections != null && connections.size() > 0) {
            for (Person person : connections) {
                if (checkExists(person) == false) {
                    //createJsonObject(person);
                    loadToDataBase(person);
                    System.out.println(person.getNames().get(0).getDisplayName() + "-->" + person.getPhoneNumbers().get(0).getValue() +
                            " kontakt bekerült az adatbázisba.");
                } else {
                    //Ha a kontakt már létezik az adatbázisban
                    System.out.println(person.getNames().get(0).getDisplayName() + " kontakthoz tartozó " + person.getPhoneNumbers().get(0).getValue() +
                            " telefonszám már létezik az adatbázisban!");
                }
            }
        } else {
            System.out.println("No connections found.");
        }
    }
}