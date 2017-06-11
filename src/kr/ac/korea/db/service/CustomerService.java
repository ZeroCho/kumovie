package kr.ac.korea.db.service;

import kr.ac.korea.db.dao.CustomerDAO;
import kr.ac.korea.db.model.Customer;

import java.util.List;

/**
 * Created by ffaass on 2017-06-11.
 */
public class CustomerService {
    private CustomerDAO customerDAO;

    public CustomerService() {
        customerDAO = new CustomerDAO();
    }

    public Customer getCustomer(String id, String pw) {
        Customer customer = customerDAO.getCustomer(id);
        if (!customer.getPassword().equals(pw)) {
            return null;
        }

        return customer;
    }

    public List<Customer> getCustomerList() {
        return customerDAO.getCustomerList();
    }
}
