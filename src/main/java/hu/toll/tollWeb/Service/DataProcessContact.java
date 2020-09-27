package hu.toll.tollWeb.Service;

import com.google.api.services.people.v1.model.Person;
import hu.toll.tollWeb.Entity.Place;

import java.sql.Date;

import hu.toll.tollWeb.Model.ContactService;
import hu.toll.tollWeb.Repository.PlaceRepository;
import hu.toll.tollWeb.ServiceInterfaces.DataProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

@Component("DataProcessContact")
public class DataProcessContact implements DataProcess {
    @Autowired
    PlaceRepository placeRepo;
    Calendar calendar = Calendar.getInstance();

    public void loadToDataBase(Person person) {
        try {
            Place newPlace = new Place(
                    person.getNames().get(0).getDisplayName(),
                    person.getPhoneNumbers().get(0).getValue(),
                    new Date(calendar.getTime().getTime()),
                    "Aktív",
                    "Telefon"
            );
            placeRepo.save(newPlace);
        } catch (Exception e) {
            System.out.println("Hiba történt: " + e);
        }
    }

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
                placeRepo.save(checkedContact);
            }
        }
    }


    @Override
    public void process() throws Exception {
        Set<Person> placesOnPhone = ContactService.getContacts();
        if (placesOnPhone != null && placesOnPhone.size() > 0) {
            for (Person person : placesOnPhone) {
                if (checkExistsOnDatabase(person) == false) {
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

        List<Place> placesOnDatabase = placeRepo.findByStatus("Aktív");
        for (Place checkedContact: placesOnDatabase) {
            isDeactivateContact(checkedContact, placesOnPhone);
        }
    }
}