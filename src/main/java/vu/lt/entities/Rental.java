package vu.lt.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NamedQueries({
        @NamedQuery(name = "Rental.findAll", query = "select t from Rental as t")
})
@Table(name = "Rental")
@Getter @Setter
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name="CAR_ID")
    private Car car;

    @ManyToOne(optional = false)
    @JoinColumn(name="CUSTOMER_ID")
    private Customer customer;
}
