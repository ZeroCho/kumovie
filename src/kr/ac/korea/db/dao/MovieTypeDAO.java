package kr.ac.korea.db.dao;

import kr.ac.korea.db.model.MovieType;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by ffaass on 2017-06-11.
 * DB의 영화정보 테이블에 접근하는 객체이나 영화정보를 CRUD하는 기능이 없기 때문에
 * ResultSet으로부터 MovieType 객체를 반환하는 메서드만 가짐
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
