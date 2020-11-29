package hu.toll.tollWeb.Controller;

import hu.toll.tollWeb.Repository.PlaceRepositoryJson;
import hu.toll.tollWeb.ServiceInterfaces.DataProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JsonSiteController {
    @Autowired
    @Qualifier("DataProcessJSON")
    DataProcess dataProcessJSON;

    @Autowired
    @Qualifier("DataProcessDB")
    DataProcess dataProcessDB;

    @Autowired
    PlaceRepositoryJson jsonRepo;

    @RequestMapping("/json")
    public String json() throws Exception {
        //dataProcessJSON.process();

        //A DB kontaktok beírása JSON fájlba.
        jsonRepo.writeJSON(dataProcessDB.getPlaces());
        return "ok";
    }
}
