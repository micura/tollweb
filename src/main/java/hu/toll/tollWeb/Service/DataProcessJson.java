package hu.toll.tollWeb.Service;

import com.google.api.services.people.v1.model.Person;
import hu.toll.tollWeb.Entity.Place;
import hu.toll.tollWeb.Model.ContactService;
import hu.toll.tollWeb.Repository.PlaceRepositoryJson;
import hu.toll.tollWeb.ServiceInterfaces.DataProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

@Component("DataProcessJSON")
public class DataProcessJson implements DataProcess {
    @Autowired
    PlaceRepositoryJson placeRepo;
    Calendar calendar = Calendar.getInstance();

    public void convertObjectToJsonObject(Person person) {
        Place newPlace = new Place(
                person.getNames().get(0).getDisplayName(),
                person.getPhoneNumbers().get(0).getValue(),
                new Date(calendar.getTime().getTime()),
                "Aktív",
                "Telefon"
        );
        save(newPlace);
    }

    /*
    public boolean checkExistsOnDatabase(Person person) {
        Place checkedPlace = placeRepo.findPlaceByPhoneNumber(person.getPhoneNumbers().get(0).getValue());
        if (checkedPlace != null) {
            return true;
        }
        return false;
    }


    public void isDeactivateContact(Place checkedContact, Set<Person> placesOnPhone) {
        int counter = 0;
        for (Person phonePlace: placesOnPhone) {
            counter++;

            if (phonePlace.getPhoneNumbers().get(0).getValue().equals(checkedContact.getPhoneNumber())) {
                break;
            }

            if (counter == placesOnPhone.size()) {
                System.out.println("A " + checkedContact.getName() + " kontakt törölve lett");
                checkedContact.setStatus("Törölve");
                save(checkedContact);
            }
        }
    }
    */

    @Override
    public List<Place> getPlaces() {
        return (List<Place>) placeRepo.readJSON();
    }

    @Override
    public void process() throws Exception {
        Set<Person> placesOnPhone = ContactService.getContacts();
        if (placesOnPhone != null && placesOnPhone.size() > 0) {
            for (Person person : placesOnPhone) {
                //if (checkExistsOnDatabase(person) == false) {
                    //loadToDataBase(person);
                    convertObjectToJsonObject(person);
                    System.out.println(person.getNames().get(0).getDisplayName() + "-->" + person.getPhoneNumbers().get(0).getValue() +
                            " kontakt bekerült a JSON fájlba.");
                /*} else {
                    System.out.println(person.getNames().get(0).getDisplayName() + " kontakthoz tartozó " + person.getPhoneNumbers().get(0).getValue() +
                            " telefonszám már létezik a JSON fájlban!");
                }*/
            }
        } else {
            System.out.println("No connections found.");
        }

        /*
        List<Place> activePhonePlacesOnDatabase = placeRepo.findPlacesBySourceAndStatus("Telefon", "Aktív");

        for (Place checkedContact: activePhonePlacesOnDatabase) {
            isDeactivateContact(checkedContact, placesOnPhone);
        }
         */
    }

    @Override
    public String save(Place newPlace) {
        try {
            placeRepo.writeJSON(newPlace);
            return "ok";
        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        }
    }
}