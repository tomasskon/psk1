package vu.lt.persistence;

import vu.lt.entities.CarBrand;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class CarBrandDAO {
    @Inject
    private EntityManager em;

    public List<CarBrand> loadAll() {
        return em.createNamedQuery("CarBrand.findAll", CarBrand.class).getResultList();
    }

    public void persist(CarBrand carBrand){
        this.em.persist(carBrand);
    }

    public CarBrand findOne(Integer id){
        return em.find(CarBrand.class, id);
    }

    public CarBrand update(CarBrand carBrand){
        return em.merge(carBrand);
    }
}
