package vu.lt.services;

import vu.lt.entities.Car;
import vu.lt.entities.CarBrand;
import vu.lt.services.Interfaces.IRentalService;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import java.io.Serializable;
import java.util.List;

@Alternative
@ApplicationScoped
public class RecommendedCarService implements IRentalService, Serializable {
    @Override
    public Car findFreeCar(List<Car> allCars) {
        var recommendedCar = new Car();
        var recommendedCarBrand = new CarBrand();
        recommendedCarBrand.setBrand("Audi");
        recommendedCar.setCarBrand(recommendedCarBrand);
        recommendedCar.setModel("A3");

        return recommendedCar;
    }
}
