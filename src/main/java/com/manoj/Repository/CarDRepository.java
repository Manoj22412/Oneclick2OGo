package com.manoj.Repository;

import com.manoj.model.Car;
import com.manoj.model.CarD;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CarDRepository extends JpaRepository <CarD,Long>{

    @Query("SELECT r FROM Car r WHERE lower(r.name) LIKE lower(concat('%',:query,'%'))" +
            " OR LOWER(r.carD) LIKE lower(concat('%',:query,'%')) ")
    List<CarD> findBySearchQuery(String Query);

    CarD findByOwnerId(Long userId);

}
