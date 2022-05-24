package vu.lt.rest.contracts;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vu.lt.entities.Customer;

@Getter
@Setter
@NoArgsConstructor
public class CustomerDTO {
    private Integer id;
    private String name;
    private String lastName;
    private int age;

    public CustomerDTO(Customer customer){
        this.id = customer.getId();
        this.name = customer.getName();
        this.lastName = customer.getLastName();
        this.age = customer.getAge();
    }
}
