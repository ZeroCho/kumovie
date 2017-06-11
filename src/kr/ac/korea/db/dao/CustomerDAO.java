package kr.ac.korea.db.dao;

import kr.ac.korea.db.Database;
import kr.ac.korea.db.model.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ffaass on 2017-06-11.
 */
public class CustomerDAO {
    public Customer getCustomer(String id) {
        Connection conn = Database.getConnection();
        Customer customer = null;
        if (conn != null) {
            try {
                PreparedStatement stmt = conn.prepareStatement("SELECT * FROM customer WHERE id=?");
                stmt.setString(1, id);
                ResultSet result = stmt.executeQuery();
                // 결과가 있으면 customer 생성
                if (result.next()) {
                   customer = getCustomerFromResultSet(result);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return customer;
    }

    public List<Customer> getCustomerList(int limit, int offset) {
        Connection conn = Database.getConnection();
        List<Customer> customerList = new ArrayList<Customer>();
        if (conn != null) {
            try {
                PreparedStatement stmt = conn.prepareStatement("SELECT * FROM customer LIMIT ? OFFSET ?");
                stmt.setInt(1, limit);
                stmt.setInt(2, offset);
                ResultSet result = stmt.executeQuery();
                while (result.next()) {
                    customerList.add(getCustomerFromResultSet(result));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return customerList;
    }

    private Customer getCustomerFromResultSet(ResultSet result) throws SQLException {
        String resultId = result.getString("customer_id");
        String resultPw = result.getString("password");
        String name =  result.getString("name");
        String phoneNum =  result.getString("phone_num");
        Date birthday = result.getDate("birthday");
        String rank = result.getString("customer_rank");
        return new Customer(resultId, resultPw, name, phoneNum, birthday, rank);
    }
}

