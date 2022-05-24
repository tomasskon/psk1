package vu.lt.services;

import vu.lt.entities.Car;
import vu.lt.entities.CarBrand;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Specializes;
import java.util.List;

@Specializes
@Alternative
@ApplicationScoped
public class SpecializedRentalService extends RentalService{
    public Car findFreeCar(List<Car> allCars) {
        var recommendedCar = new Car();
        var recommendedCarBrand = new CarBrand();
        recommendedCarBrand.setBrand("BMW");
        recommendedCar.setCarBrand(recommendedCarBrand);
        recommendedCar.setModel("E90");

        return recommendedCar;
    }
}
