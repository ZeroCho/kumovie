package kr.ac.korea.db.dao;

import kr.ac.korea.db.model.MovieType;
import kr.ac.korea.db.model.Theater;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by ffaass on 2017-06-11.
 */
public class MovieTypeDAO {
    public static MovieType getMovieTypeFromResultSet(ResultSet result) {
        try {
            String location = result.getString("type");
            int price = result.getInt("price");
            return new MovieType(location, price);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
