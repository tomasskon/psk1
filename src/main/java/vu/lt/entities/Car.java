package vu.lt.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "Car")
@NamedQueries({
        @NamedQuery(name = "Car.findAll", query = "select t from Car as t")
})
@Getter @Setter
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name="BRAND_ID")
    private CarBrand carBrand;

    @ManyToMany(mappedBy = "rentedCars")
    List<Customer> customerSet;

    @Size(max = 50)
    @Column(name = "CAR_MODEL")
    private String model;
}
