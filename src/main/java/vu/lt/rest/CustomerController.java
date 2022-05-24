package vu.lt.rest;

import lombok.Getter;
import lombok.Setter;
import vu.lt.entities.Customer;
import vu.lt.persistence.CustomerDAO;
import vu.lt.rest.contracts.CustomerDTO;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
@Path("/customers")
public class CustomerController {

    @Inject
    @Getter
    @Setter
    private CustomerDAO customerDAO;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCustomers() {
        List<CustomerDTO> customers = customerDAO.loadAll()
                .stream()
                .map(CustomerDTO::new)
                .collect(Collectors.toList());
        return Response.ok(customers).build();
    }

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") final Integer id) {
        Customer customer = customerDAO.findOne(id);
        if (customer == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(new CustomerDTO(customer)).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response addCustomer(CustomerDTO customerDTO) {
        if (customerDTO.getName() == null || customerDTO.getLastName() == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        try {
            Customer customer = new Customer();
            customer.setName(customerDTO.getName());
            customer.setLastName(customerDTO.getLastName());
            customer.setAge(customerDTO.getAge());
            customerDAO.persist(customer);

            return Response.ok().build();
        }catch (OptimisticLockException ex){
            return Response.status(Response.Status.CONFLICT).build();
        }
    }

    @Path("/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response update(@PathParam("id") Integer id, CustomerDTO customerDTO) {
        if (customerDTO.getName() == null || customerDTO.getLastName() == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        try {
            Customer existingCustomer = customerDAO.findOne(id);

            if (existingCustomer == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }

            existingCustomer.setName(customerDTO.getName());
            existingCustomer.setLastName(customerDTO.getLastName());
            existingCustomer.setAge(customerDTO.getAge());
            customerDAO.update(existingCustomer);

            return Response.ok().build();
        } catch (OptimisticLockException exception) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }
}