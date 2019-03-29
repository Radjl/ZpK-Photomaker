package repository;

import models.CarriageMassive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface CarriageMassiveRepo extends JpaRepository<CarriageMassive,Long> {
    CarriageMassive findFirstByOrderByIdDesc();

    Iterable<CarriageMassive> findAllByStartTimeBetweenOrderByIdDesc(LocalDateTime start , LocalDateTime end);

}
