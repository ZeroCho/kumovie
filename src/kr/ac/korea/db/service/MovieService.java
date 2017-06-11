package kr.ac.korea.db.service;

import kr.ac.korea.db.dao.MovieDAO;
import kr.ac.korea.db.model.Movie;

import java.util.List;

/**
 * Created by ffaass on 2017-06-11.
 */
public class MovieService {
    private MovieDAO movieDAO;

    public MovieService() {
        movieDAO = new MovieDAO();
    }
    public List<Movie> getMovieList() {
        return movieDAO.getMovieList();
    }
}
