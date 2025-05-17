package com.manoj.Repository;

import com.manoj.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CarRepository extends JpaRepository<Car,Long> {

    List<Car> findByCarDId(Long CarDId);

    @Query("SELECT f FROM Car f WHERE f.name LIKE %:keyword% OR f.carCategory.name LIKE %:keyword%")
    List<Car>searchCar(@Param("keyword")String keyword);
}
