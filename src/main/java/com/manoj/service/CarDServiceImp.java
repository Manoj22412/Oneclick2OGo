package com.manoj.service;

import com.manoj.Repository.AddressRepository;
import com.manoj.Repository.CarDRepository;
import com.manoj.Repository.UserRepository;
import com.manoj.dto.CarDDto;
import com.manoj.model.Address;
import com.manoj.model.CarD;
import com.manoj.model.User;
import com.manoj.request.CreateCarDRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CarDServiceImp implements CarDService {



    @Autowired
    private CarDRepository carDRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public CarD CreateCarD(CreateCarDRequest req, User user) {

        Address address = addressRepository.save(req.getAddress());

        CarD carD = new CarD();
        carD.setAddress(address);
        carD.setContactInformation(req.getContactInformation());
        carD.setCuisineType(req.getCarType());
        carD.setDescription(req.getDescription());
        carD.setImages(req.getImages());
        carD.setName(req.getName());
        carD.setOpeningHours(req.getOpeningHours());
        carD.setRegistrationDate(LocalDateTime.now());
        carD.setOwner(user);

        return carDRepository.save(carD);
    }

    @Override
    public CarD updateCarD(Long CarDId, CreateCarDRequest updateCarD) throws Exception {
        CarD carD=findCarDById(CarDId);

        if (carD.getCuisineType()!=null){
            carD.setCuisineType(updateCarD.getCarType());
        }
        if (carD.getDescription()!=null){
            carD.setDescription(updateCarD.getDescription());
        }
        if (carD.getName()!=null){
            carD.setName(updateCarD.getName());
        }
        return carDRepository.save(carD);
    }

    @Override
    public void deleteCarD(Long carDId) throws Exception {

        CarD carD=findCarDById(carDId);

        carDRepository.delete(carD);

    }

    @Override
    public List<CarD> getAllCarD() {
        return carDRepository.findAll();
    }

    @Override
    public List<CarD> searchCarD(String keyword) {
        return carDRepository.findBySearchQuery(keyword);
    }


    public CarD findCarDById(Long id) throws Exception {
        Optional<CarD> opt=carDRepository.findById(id);

        if (opt.isEmpty()){
            throw new Exception("carD not found");
        }
        return opt.get();
    }

    @Override
    public CarD getCarDByUserId(Long userId) throws Exception {
        CarD carD=carDRepository.findByOwnerId(userId);
        if (carD==null){
            throw new Exception("carD not found with owner id"+userId);
        }
        return carD;
    }

    @Override
    public CarDDto addToFavorites(Long CarDId, User user) throws Exception {
        CarD carD=findCarDById(CarDId);

        CarDDto dto=new CarDDto();
        dto.setDescription(carD.getDescription());
        dto.setImages(carD.getImages());
        dto.setImages(carD.getImages());
        dto.setTitle(carD.getName());
        dto.setId(carD.getId());

        boolean isFavorited = false;
        List<CarDDto> favorites=user.getFavorites();
        for (CarDDto favorite:favorites){
            if (favorite.getId().equals(CarDId)){
                isFavorited=true;
                break;
            }
        }
        if (isFavorited){
            favorites.removeIf(favorite -> favorite.getId().equals(carD));
        }else {
            favorites.add(dto);
        }

        userRepository.save(user);
        return dto;
    }

    @Override
    public CarD updateCarDStatus(Long Id) throws Exception {
        CarD carD=findCarDById(Id);
        carD.setOpen(!carD.isOpen());
        return carDRepository.save(carD);
    }
}
