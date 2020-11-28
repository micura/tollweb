package hu.toll.tollWeb.Controller;

import hu.toll.tollWeb.Entity.Place;
import hu.toll.tollWeb.ServiceInterfaces.DataProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class JsonSiteController {
    @Autowired
    @Qualifier("DataProcessJSON")
    DataProcess dataProcessJSON;

    @RequestMapping("/json")
    public String json() throws Exception {
        dataProcessJSON.process();
        return "ok";
    }
}
