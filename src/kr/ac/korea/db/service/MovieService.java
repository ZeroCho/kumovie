package kr.ac.korea.db.service;

import kr.ac.korea.db.dao.MovieDAO;
import kr.ac.korea.db.model.Movie;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by ffaass on 2017-06-11.
 */
public class MovieService {
    private MovieDAO movieDAO;

    public MovieService() {
        movieDAO = new MovieDAO();
    }
    public List<Movie> getMovieList(int limit, int offset) {
        return movieDAO.getMovieList(limit, offset);
    }
    public void addMovie(String title,
                         String director,
                         String genre,
                         int rating,
                         String dateString,
                         int runtime) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {

            Date date = dateFormat.parse(dateString);
            // DB에 auto increment 안걸려있어서 직접 PK 구함
            int movieId = movieDAO.getPK() + 1;
            Movie movie = new Movie(movieId, title, director, genre, rating, date, runtime);
            movieDAO.insertMovie(movie);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
