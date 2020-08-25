package hu.toll.tollWeb.Controller;

import hu.toll.tollWeb.Service.PlaceService;
import hu.toll.tollWeb.ServiceInterfaces.DataProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
    @Autowired
    private PlaceService placeService;

    @Autowired
    @Qualifier("DataProcessContact")
    DataProcess dataProcess;

    @RequestMapping("/")
    public String home(Model model) {
        model.addAttribute("places", placeService.getPlaces());
        return "index";
    }

    @RequestMapping("/admin")
    public String admin(Model model) {
        model.addAttribute("places", placeService.getPlaces());
        return "admin";
    }

    @RequestMapping(value="/action", method= RequestMethod.POST)
    public String onClickAction() throws Exception {
        dataProcess.process();
        return "index";
    }
}