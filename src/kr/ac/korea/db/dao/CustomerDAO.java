package kr.ac.korea.db.dao;

import kr.ac.korea.db.Database;
import kr.ac.korea.db.model.Customer;

import java.sql.*;

/**
 * Created by ffaass on 2017-06-11.
 * JDBC를 이용하여 DB의 고객 테이블에 접근하는 객체
 * 필요한 기능이 고객을 찾는 것 뿐이기 때문에 id로 고객을 찾는 기능만 구현
 */
public class CustomerDAO {
    public Customer getCustomer(String id) {
        Connection conn = Database.getConnection();
        Customer customer = null;
        if (conn != null) {
            try {
                //customer 테이블에서 customer_id 필드가 파라미터로 받은 String과 같은 row를 찾는 쿼리
                //SQL injection을 막기 위해 PrepareStatement 생성
                PreparedStatement stmt = conn.prepareStatement("SELECT * FROM customer WHERE customer_id=?");
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
                    //Connection은 쿼리 실행에 문제가 생겨도 닫아줘야 함
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return customer;
    }


    // ResultSet의 내용으로 Customer 객체를 생성하는 메서드
    public static Customer getCustomerFromResultSet(ResultSet result) throws SQLException {
        String resultId = result.getString("customer_id");
        String resultPw = result.getString("password");
        String name =  result.getString("name");
        String phoneNum =  result.getString("phone_num");
        Date birthday = result.getDate("birthday");
        String rank = result.getString("customer_rank");
        return new Customer(resultId, resultPw, name, phoneNum, birthday, rank);
    }
}

