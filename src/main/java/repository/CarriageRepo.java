package repository;


import models.Carriage;
import models.CarriageMassive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarriageRepo extends JpaRepository<Carriage,Long> {




}
