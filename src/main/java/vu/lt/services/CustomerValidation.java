package vu.lt.services;

import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;

@ApplicationScoped
public class CustomerValidation implements Serializable {
    public boolean CheckIfCustomerIsOldEnough(int customerAge){
        if(customerAge <= 18){
            return false;
        }

        return true;
    }
}
