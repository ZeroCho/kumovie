package kr.ac.korea.db.model;

import java.util.Date;

/**
 * Created by ffaass on 2017-06-11.
 */
public class Movie {
    private int movieId;
    private String title;
    private String director;
    private String genre;
    private int rating;
    private Date playdate;
    private int runtime;

    public Movie(int movieId,
                 String title,
                 String director,
                 String genre,
                 int rating,
                 Date playdate,
                 int runtime) {
        this.movieId = movieId;
        this.title = title;
        this.director = director;
        this.genre = genre;
        this.rating = rating;
        this.playdate = playdate;
        this.runtime = runtime;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Date getPlaydate() {
        return playdate;
    }

    public void setPlaydate(Date playdate) {
        this.playdate = playdate;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }
}
