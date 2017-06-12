package kr.ac.korea.db.dao;

import kr.ac.korea.db.model.Theater;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by ffaass on 2017-06-11.
 * DB의 상영관 테이블에 접근하는 객체이나 상영관을 CRUD하는 기능이 없기 때문에
 * ResultSet으로부터 Theater 객체를 반환하는 메서드만 가짐
 */
public class TheaterDAO {
    public static Theater getTheaterFromResultSet(ResultSet result) {
        try {
            int theaterId = result.getInt("theater_id");
            String location = result.getString("location");
            int seatNum = result.getInt("seat_num");
            return new Theater(theaterId, location, seatNum);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
