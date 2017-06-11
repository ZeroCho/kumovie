package kr.ac.korea.db.model;

import java.util.Date;

/**
 * Created by ffaass on 2017-06-11.
 */
public class Schedule {
    private int scheduleId;
    private int movieId;
    private int theraterId;
    private Date date;
    private Date time;
    private String type;
    private Movie movie;

    public Schedule(int scheduleId,
                    int movieId,
                    int theraterId,
                    Date date,
                    Date time,
                    String type,
                    Movie movie) {
        this.scheduleId = scheduleId;
        this.movieId = movieId;
        this.theraterId = theraterId;
        this.date = date;
        this.time = time;
        this.type = type;
        this.movie = movie;
    }

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public int getTheraterId() {
        return theraterId;
    }

    public void setTheraterId(int theraterId) {
        this.theraterId = theraterId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}
