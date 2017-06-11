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
    public void insertMovie(Movie movie) {
        Connection conn = Database.getConnection();
        if (conn != null) {
            try {
                PreparedStatement stmt = conn.prepareStatement("INSERT INTO movie " +
                        "(movie_id, title, director, genre, rating, playdate, runtime) " +
                        "VALUES(?,?,?,?,?,?,?)");
                stmt.setInt(1, movie.getMovieId());
                stmt.setString(2, movie.getTitle());
                stmt.setString(3, movie.getDirector());
                stmt.setString(4, movie.getGenre());
                stmt.setInt(5, movie.getRating());
                stmt.setDate(6, new java.sql.Date(movie.getPlaydate().getTime()));
                stmt.setInt(7, movie.getRuntime());
                stmt.executeUpdate();
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
    }

    public Movie updateMovie(Movie movie) {
        Connection conn = Database.getConnection();
        if (conn != null) {
            try {
                PreparedStatement stmt = conn.prepareStatement("UPDATE movie SET " +
                        "title = ?, director = ?, genre = ?, rating = ?, playdate = ?, runtime = ? " +
                        "WHERE movie_id = ?");
                stmt.setString(1, movie.getTitle());
                stmt.setString(2, movie.getDirector());
                stmt.setString(3, movie.getGenre());
                stmt.setInt(4, movie.getRating());
                stmt.setDate(5, new java.sql.Date(movie.getPlaydate().getTime()));
                stmt.setInt(6, movie.getRuntime());
                stmt.setInt(7, movie.getMovieId());
                stmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
                movie = null;
            } finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    movie = null;
                }
            }
        }
        return movie;
    }

    public int deleteMovie(int movieId) {

        Connection conn = Database.getConnection();
        if (conn != null) {
            try {
                PreparedStatement stmt = conn.prepareStatement("DELETE FROM movie " +
                        "WHERE movie_id = ?");
                stmt.setInt(1, movieId);
                return stmt.executeUpdate();
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
        return 0;
    }

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

    public int getPK() {
        Connection conn = Database.getConnection();
        int pk = 0;
        if (conn != null) {
            try {
                PreparedStatement stmt = conn.prepareStatement("SELECT movie_id FROM movie ORDER BY movie_id DESC LIMIT 1");

                ResultSet result = stmt.executeQuery();
                result.next();
                pk = result.getInt("movie_id");
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
        return pk;
    }

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
