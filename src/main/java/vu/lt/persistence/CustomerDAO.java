package vu.lt.persistence;

import vu.lt.entities.Customer;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class CustomerDAO {
    @Inject
    private EntityManager em;

    public List<Customer> loadAll() {
        return em.createNamedQuery("Customer.findAll", Customer.class).getResultList();
    }

    public void persist(Customer customer){
        this.em.persist(customer);
    }

    public Customer findOne(Integer id){
        return em.find(Customer.class, id);
    }

    public Customer update(Customer customer){
        return em.merge(customer);
    }
}
