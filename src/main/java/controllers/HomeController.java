package controllers;


import models.Carriage;
import models.CarriageMassive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import repository.CarriageMassiveRepo;
import repository.CarriageRepo;
import services.CarriageService;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    CarriageRepo carriageRepo;

    @Autowired
    CarriageMassiveRepo carriageMassiveRepo;

    @Autowired
    CarriageService carriageService;

    @RequestMapping("/")
    public String main(Model model){


        Iterable<Carriage> carriages = carriageRepo.findAll();
        List<CarriageMassive> carriagesMassive = carriageMassiveRepo.findAll();


        model.addAttribute("carriage",carriages);
        model.addAttribute("carriagemassive",carriagesMassive);
        return "main2";
    }



    @RequestMapping("/test")
    public String main2(Model model){


        Iterable<Carriage> carriages = carriageRepo.findAll();
        List<CarriageMassive> carriagesMassive = carriageMassiveRepo.findAll();
        CarriageMassive lastcreated = carriageMassiveRepo.findFirstByOrderByIdDesc();


        Iterable<CarriageMassive> carriagesMassive2 = carriageService.findAllOrderByIdDescByOneDay();


        model.addAttribute("carriage",carriages);
        model.addAttribute("carriagemassive",carriagesMassive);
        model.addAttribute("carriagemassive2",carriagesMassive2);
        model.addAttribute("lastcreated",lastcreated);
        return "maintests";
    }

    @RequestMapping("/test2")
    public String main22(Model model){


        Iterable<Carriage> carriages = carriageRepo.findAll();
        List<CarriageMassive> carriagesMassive = carriageMassiveRepo.findAll();
        CarriageMassive lastcreated = carriageMassiveRepo.findFirstByOrderByIdDesc();


        Iterable<CarriageMassive> carriagesMassive2 = carriageService.findAllOrderByIdDescByOneDay();


        model.addAttribute("carriage",carriages);
        model.addAttribute("carriagemassive",carriagesMassive);
        model.addAttribute("carriagemassive2",carriagesMassive2);
        model.addAttribute("lastcreated",lastcreated);
        return "mains";
    }

    @PostMapping("/delete")
    public String deleteShip(Model model){
        carriageRepo.deleteAll();
        carriageMassiveRepo.deleteAll();

        return "main2";
    }

}
