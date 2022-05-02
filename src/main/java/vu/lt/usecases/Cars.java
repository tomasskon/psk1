package vu.lt.usecases;

import lombok.Getter;
import lombok.Setter;
import vu.lt.entities.Car;
import vu.lt.entities.CarBrand;
import vu.lt.persistence.CarDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class Cars {
    @Inject
    private CarDAO carDAO;

    @Getter @Setter
    Car carToCreate = new Car();

    @Getter
    private List<Car> allCars;

    @PostConstruct
    public void init(){
        loadAllCars();
        carToCreate.setCarBrand(new CarBrand());
    }

    @Transactional
    public String createCar(){
        this.carDAO.persist(carToCreate);
        return "cars?faces-redirect=true";
    }

    private void loadAllCars(){
        this.allCars = carDAO.loadAll();
    }
}
