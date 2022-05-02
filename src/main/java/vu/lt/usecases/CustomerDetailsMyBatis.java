package vu.lt.usecases;

import lombok.Getter;
import lombok.Setter;
import vu.lt.mybatis.dao.CustomerMapper;
import vu.lt.mybatis.model.Customer;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Map;

@Model
public class CustomerDetailsMyBatis {
    @Inject
    private CustomerMapper customerMapper;

    @Getter @Setter
    private Customer customerToUpdate = new Customer();

    @Transactional
    public void updateCustomer() {
        customerMapper.updateByPrimaryKey(customerToUpdate);
    }

    @PostConstruct
    private void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer playerId = Integer.parseInt(requestParameters.get("customerId"));
        this.customerToUpdate = customerMapper.selectByPrimaryKey(playerId);
    }
}
