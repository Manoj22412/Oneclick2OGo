package com.manoj.service;

import com.manoj.dto.CarDDto;
import com.manoj.model.CarD;
import com.manoj.model.User;
import com.manoj.request.CreateCarDRequest;

import java.util.List;


public interface CarDService {

    public CarD CreateCarD(CreateCarDRequest req, User user);

     CarD updateCarD(Long CarDId, CreateCarDRequest updateCarD) throws Exception;

    public void deleteCarD(Long carDId)throws Exception;

    public List<CarD> getAllCarD();

    public List<CarD>searchCarD(String keyword);

    public CarD findCarDById(Long id) throws Exception;

    public CarD getCarDByUserId(Long userId)throws Exception;

    public CarDDto addToFavorites(Long CarDId, User user)throws Exception;

    public CarD updateCarDStatus(Long Id) throws Exception;

}
