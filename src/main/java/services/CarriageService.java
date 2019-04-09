package services;


import models.CarriageMassive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.CarriageMassiveRepo;
import repository.CarriageRepo;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Service
public class CarriageService {


    @Autowired
    CarriageRepo carriageRepo;

    @Autowired
    CarriageMassiveRepo carriageMassiveRepo;



    public Iterable<CarriageMassive> findAllOrderByIdDescByOneDay(){

        LocalDateTime start = LocalDateTime.now().minusDays(1);
        LocalDateTime stop  = LocalDateTime.now();


return carriageMassiveRepo.findAllByStartTimeBetweenOrderByIdDesc(start,stop);
    }


    public Iterable<CarriageMassive> findAllByDate(String date){

        String startDay = date + " 00:00:00";
        String endDay = date + " 23:59:59";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");

        LocalDateTime StartOfSendedDay = LocalDateTime.parse(startDay, formatter);
        LocalDateTime EndOfSendedDay = LocalDateTime.parse(endDay, formatter);


        return carriageMassiveRepo.findAllByStartTimeBetweenOrderByIdDesc(StartOfSendedDay,EndOfSendedDay);
    }
}
