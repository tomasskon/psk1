package vu.lt.decorators;

import vu.lt.entities.Car;
import vu.lt.services.Interfaces.IRentalService;
import vu.lt.services.RentalService;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;
import java.util.List;

@Decorator
public class RentalServiceDecorator implements IRentalService {
    @Delegate
    @Any
    @Inject
    public RentalService rentalService;

    @Override
    public Car findFreeCar(List<Car> allCars) {
        if(allCars.isEmpty())
            throw new IllegalArgumentException("There are no cars available");

        return rentalService.findFreeCar(allCars);
    }
}
