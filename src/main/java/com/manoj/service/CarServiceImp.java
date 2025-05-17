package com.manoj.service;

import com.manoj.Repository.CarRepository;
import com.manoj.model.Car;
import com.manoj.model.CarD;
import com.manoj.model.Category;
import com.manoj.request.CreateCarRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarServiceImp implements CarService {
    @Autowired
    private CarRepository carRepository;

    @Override
    public Car createCar(CreateCarRequest req, String category, CarD carD) {
       Car car = new Car();

       car.setCarD(carD);
       car.setDescription(req.getDescription());
       car.setImages(req.getImages());
       car.setName(req.getName());
       car.setPrice(Long.valueOf(req.getPrice()));
       car.setFeatures(req.getFeatures());
       car.setEv(req.isEV());
       car.setPetrol(req.isPetrol());
       car.setDiesel(req.isDiesel());
       car.setSports(req.isSportCar());

        Car saveCar = carRepository.save(car);
        carD.getCars().add(saveCar);

        return saveCar;
    }

    @Override
    public void deleteCar(Long carId) throws Exception {

        Car car=findCarById(carId);
        car.setCarD(null);
        carRepository.save(car);

    }

    @Override
    public List<Car> getCarDCar(Long carDId,
                                boolean iseV,
                                boolean isDiesel,
                                boolean isPetrol,
                                boolean isSportCar,
                                String carCategory) {
        List<Car> cars=carRepository.findByCarDId(carDId);
        if(iseV){
            cars=filterByeV(cars, iseV);
        }
        if(isDiesel){
            cars=filterByDiesel(cars,isDiesel);
        }
        if(isPetrol){
            cars=filterByPetrol(cars,isPetrol);
        }
        if(isSportCar){
            cars=filterBySportCar(carCategory,isSportCar);
        }
        if (carCategory!=null && !carCategory.equals("")) {
            cars=filterByCategory(cars,carCategory);
        }
        return cars;
    }

    private List<Car> filterByCategory(List<Car> cars, String carCategory) {
    return cars.stream().filter(car ->{
        if(car.getCarCategory()!=null){
            return car.getCarCategory().equals(carCategory);
        }
        return false;
    }).collect(Collectors.toList());
    }

    private List<Car> filterBySportCar(String carCategory, boolean isSportCar) {

        return null;
    }

    private List<Car> filterByPetrol(List<Car> cars, boolean isPetrol) {
        return cars.stream().filter(car -> car.isPetrol()==isPetrol).collect(Collectors.toList());
    }

    private List<Car> filterByDiesel(List<Car> cars, boolean isDiesel) {
        return cars.stream().filter(car -> car.isEv()==false).collect(Collectors.toList());
    }

    private List<Car> filterByeV(List<Car> cars, boolean iseV) {
        return cars.stream().filter(car -> car.isEv()==iseV).collect(Collectors.toList());
    }

    @Override
    public List<Car> searchCar(String keyword) {
        return carRepository.searchCar(keyword  );
    }

    @Override
    public Car findCarById(Long carId) throws Exception {
        Optional<Car> optionalCar=carRepository.findById(carId);

        if(optionalCar.isEmpty()){
            throw new Exception("Car will be coming soon");
        }
        return optionalCar.get();
    }

    @Override
    public Car updateAvailibilityStatus(Long carId) throws Exception {
        Car car=findCarById(carId);
        car.setAvailable(!car.isAvailable());
        return carRepository.save(car);
    }
}
