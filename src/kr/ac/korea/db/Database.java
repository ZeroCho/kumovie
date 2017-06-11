package kr.ac.korea.db;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by ffaass on 2017-06-11.
 */
public class Database {
    public static Connection getConnection() {
        Connection conn = null;
        try {
            String url = "jdbc:mysql://localhost:3306/movie_reservation_system?useSSL=false";        // 사용하려는 데이터베이스명을 포함한 URL 기술
            String id = "kumovie";                                                    // 사용자 계정
            String pw = "kumovie1!";                                                // 사용자 계정의 패스워드

            Class.forName("com.mysql.jdbc.Driver");                       // 데이터베이스와 연동하기 위해 DriverManager에 등록한다.
            conn = DriverManager.getConnection(url, id, pw);                 // 커넥션이 제대로 연결되면 수행된다.

        } catch (Exception e) {                                                    // 예외가 발생하면 예외 상황을 처리한다.
            e.printStackTrace();
        }

        return conn;
    }
}
