package services;


import models.Carriage;
import models.CarriageMassive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.CarriageMassiveRepo;
import repository.CarriageRepo;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class CarriageService {


    @Autowired
    CarriageRepo carriageRepo;

    @Autowired
    CarriageMassiveRepo carriageMassiveRepo;

    public void AddCariegeToBd(Carriage carriage) {




    }

    public Iterable<Carriage> findLastCarriege() {

        long timeBetween;
        long timeMax = 2;


        List<Carriage> carriages = carriageRepo.findAll();

        LocalTime firstwagon = carriages.get(0).getLocalTime();

       // timeBetween =  Duration.between(firstwagon,carriages.get(i).getLocalTime()).toMinutes();

       // System.out.println(timeBetween);


      //  for (int i = 1; i <carriages.size() ; i++) {

       //     if (Duration.between(firstwagon,carriages.get(i).getLocalTime()). )







        List<Carriage> podacha;






     return null;
    }

    public Iterable<CarriageMassive> findAllOrderByIdDescByOneDay(){
        LocalDateTime start = LocalDateTime.now().minusDays(1);
        LocalDateTime stop  = LocalDateTime.now();



return carriageMassiveRepo.findAllByStartTimeBetweenOrderByIdDesc(start,stop);
    }


}
