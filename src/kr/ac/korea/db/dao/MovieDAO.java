package kr.ac.korea.db.dao;

import kr.ac.korea.db.Database;
import kr.ac.korea.db.model.Movie;

import java.sql.*;
import java.util.*;
import java.util.Date;

/**
 * Created by ffaass on 2017-06-11.
 */
public class MovieDAO {

    public List<Movie> getMovieList(int limit, int offset) {
        Connection conn = Database.getConnection();
        List<Movie> movieList = new ArrayList<Movie>();
        if (conn != null) {
            try {
                PreparedStatement stmt = conn.prepareStatement("SELECT * FROM movie LIMIT ? OFFSET ?");
                stmt.setInt(1, limit);
                stmt.setInt(2, offset);
                ResultSet result = stmt.executeQuery();
                while (result.next()) {
                    movieList.add(getMovieFromResultSet(result));
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
        return movieList;
    }

    private Movie getMovieFromResultSet(ResultSet result) throws SQLException {
        int movieId = result.getInt("movie_id");
        String title = result.getString("title");
        String director = result.getString("director");;
        String genre = result.getString("genre");;
        int rating = result.getInt("rating");
        Date playdate = result.getDate("playdate");;
        int runtime = result.getInt("runtime");
        return new Movie(movieId, title, director, genre, rating, playdate, runtime);
    }
}
