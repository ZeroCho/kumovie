package kr.ac.korea.db.dao;

import kr.ac.korea.db.model.Theater;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by ffaass on 2017-06-11.
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
