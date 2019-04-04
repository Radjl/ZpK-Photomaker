package controllers;


import models.Carriage;
import models.CarriageMassive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import repository.CarriageMassiveRepo;
import repository.CarriageRepo;
import services.CarriageService;

import java.text.ParseException;
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
    public String main2(Model model){



        CarriageMassive lastcreated = carriageMassiveRepo.findFirstByOrderByIdDesc();
        Iterable<CarriageMassive> carriagesMassive2 = carriageService.findAllOrderByIdDescByOneDay();
        Iterable<Carriage> carriages = carriageRepo.findAll();

        System.out.println(((List<Carriage>) carriages).size());

        model.addAttribute("cariage",carriages);
        model.addAttribute("carriagemassive2",carriagesMassive2);
        model.addAttribute("lastcreated",lastcreated);
        return "maintests";
    }


    @PostMapping("/filter")
    public String filterByDate(@RequestParam String date, Model model) throws ParseException {

        CarriageMassive lastcreated = carriageMassiveRepo.findFirstByOrderByIdDesc();
        Iterable<CarriageMassive> carriagesMassive2 = carriageService.findAllByDate(date);
        Iterable<Carriage> carriages = carriageRepo.findAll();



        model.addAttribute("cariage",carriages);



        model.addAttribute("carriagemassive2",carriagesMassive2);
        model.addAttribute("lastcreated",lastcreated);
        return "maintests";
    }


    @RequestMapping("/delete")
    public String deleteShip(Model model){
        carriageRepo.deleteAll();


        return "maintests";
    }

}
