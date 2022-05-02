package vu.lt.persistence;

import vu.lt.entities.Rental;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class RentalDAO {
    @Inject
    private EntityManager em;

    public List<Rental> loadAll() {
        return em.createNamedQuery("Rental.findAll", Rental.class).getResultList();
    }

    public void persist(Rental rental){
        this.em.persist(rental);
    }

    public Rental findOne(Integer id){
        return em.find(Rental.class, id);
    }

    public Rental update(Rental rental){
        return em.merge(rental);
    }
}
