package vu.lt.usecases;

import lombok.Getter;
import lombok.Setter;
import vu.lt.entities.Car;
import vu.lt.entities.Customer;
import vu.lt.entities.Rental;
import vu.lt.persistence.RentalDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class Rentals {
    @Inject
    private RentalDAO rentalDAO;

    @Getter @Setter
    Rental rentalToCreate = new Rental();

    @Getter
    private List<Rental> allRentals;

    @PostConstruct
    public void init(){
        loadAllCars();
        rentalToCreate.setCar(new Car());
        rentalToCreate.setCustomer(new Customer());
    }

    @Transactional
    public String createRental(){
        this.rentalDAO.persist(rentalToCreate);
        return "rentals?faces-redirect=true";
    }

    private void loadAllCars(){
        this.allRentals = rentalDAO.loadAll();
    }
}
