package vu.lt.services;

import vu.lt.entities.Car;
import vu.lt.services.Interfaces.IRentalService;

import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import java.util.List;


@ApplicationScoped
public class RentalService implements IRentalService, Serializable {
    public Car findFreeCar(List<Car> allCars){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        java.util.Random random = new java.util.Random();
        int randomCar  = random.nextInt(allCars.size());

        return allCars.get(randomCar);
    }
}
