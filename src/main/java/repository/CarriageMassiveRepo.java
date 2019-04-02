package repository;

import models.CarriageMassive;
import org.apache.tomcat.jni.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Repository
public interface CarriageMassiveRepo extends JpaRepository<CarriageMassive,Long> {
    CarriageMassive findFirstByOrderByIdDesc();

    Iterable<CarriageMassive> findAllByStartTimeBetweenOrderByIdDesc(LocalDateTime start , LocalDateTime end);






 }
