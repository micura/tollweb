package hu.toll.tollWeb.Controller;

import hu.toll.tollWeb.Entity.Place;
import hu.toll.tollWeb.Service.PlaceService;
import hu.toll.tollWeb.ServiceInterfaces.DataProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class HomeController {
    @Autowired
    PlaceService placeService;
    @Autowired
    @Qualifier("DataProcessDB")
    DataProcess dataProcessDB;

    @RequestMapping("/")
    public String home(Model model) {
        model.addAttribute("places", dataProcessDB.getPlaces());
        return "index";
    }

    @RequestMapping("/admin")
    public String admin(Model model) {
        model.addAttribute("places", dataProcessDB.getPlaces());
        return "admin";
    }

    @RequestMapping("/map")
    public String map(Model model) {
        model.addAttribute("locations", dataProcessDB.getPlaces());
        //System.out.println(placeService.findActiveCities("Aktív"));
        return "map";
    }

    @RequestMapping(value = "/saveplace", method = RequestMethod.POST)
    public RedirectView savePlace(@RequestBody Place place) throws Exception {
        placeService.save(place);

        //TODO Exist Contact problem
        return new RedirectView("admin");
    }

    @RequestMapping(value = "/action", method = RequestMethod.POST)
    public RedirectView phoneContactUpdate() throws Exception {
        dataProcessDB.process();
        return new RedirectView("admin");
    }
}