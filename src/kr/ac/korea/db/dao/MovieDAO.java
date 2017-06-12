package kr.ac.korea.db.dao;

import kr.ac.korea.db.model.Movie;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * Created by ffaass on 2017-06-11.
 * DB의 영화 테이블에 접근하는 객체이나 영화을 CRUD하는 기능이 없기 때문에
 * ResultSet으로부터 Movie 객체를 반환하는 메서드만 가짐
 */
public class MovieDAO {

    public static Movie getMovieFromResultSet(ResultSet result) throws SQLException {
        int movieId = result.getInt("movie_id");
        String title = result.getString("title");
        String director = result.getString("director");
        String genre = result.getString("genre");
        int rating = result.getInt("rating");
        Date playdate = result.getDate("playdate");
        int runtime = result.getInt("runtime");
        return new Movie(movieId, title, director, genre, rating, playdate, runtime);
    }
}
