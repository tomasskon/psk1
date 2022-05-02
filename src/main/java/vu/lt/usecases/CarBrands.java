package vu.lt.usecases;

import lombok.Getter;
import lombok.Setter;
import vu.lt.entities.CarBrand;
import vu.lt.persistence.CarBrandDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class CarBrands {
    @Inject
    private CarBrandDAO carBrandDAO;

    @Getter @Setter
    CarBrand carBrandToCreate = new CarBrand();

    @Getter
    private List<CarBrand> allCarBrands;

    @PostConstruct
    public void init(){
        loadAllCarBrands();
    }

    @Transactional
    public String createCardBrand(){
        this.carBrandDAO.persist(carBrandToCreate);
        return "carBrands?faces-redirect=true";
    }

    private void loadAllCarBrands(){
        this.allCarBrands = carBrandDAO.loadAll();
    }
}
