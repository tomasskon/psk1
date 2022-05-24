package vu.lt.services.Interfaces;

import vu.lt.entities.Car;

import java.util.List;

public interface IRentalService {
    Car findFreeCar(List<Car> allCars);
}
