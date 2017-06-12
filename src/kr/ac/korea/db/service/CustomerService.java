package kr.ac.korea.db.service;

import kr.ac.korea.db.dao.CustomerDAO;
import kr.ac.korea.db.model.Customer;

/**
 * Created by ffaass on 2017-06-11.
 */
public class CustomerService {
    private CustomerDAO customerDAO;

    public CustomerService() {
        customerDAO = new CustomerDAO();
    }

    //id와 비밀번호로 고객을 찾아서 반환하는 메서드
    public Customer getCustomer(String id, String pw) {
        Customer customer = customerDAO.getCustomer(id);
        //가져온 고객의 패스워드가 입력된 패스워드와 다르면 null 반환
        if (!customer.getPassword().equals(pw)) {
            return null;
        }

        return customer;
    }

}
