package kr.ac.korea.db.dao;

import kr.ac.korea.db.Database;
import kr.ac.korea.db.model.Movie;
import kr.ac.korea.db.model.Schedule;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ffaass on 2017-06-11.
 */
public class ScheduleDAO {

    public List<Schedule> getScheduleList(int limit, int offset) {
        Connection conn = Database.getConnection();
        List<Schedule> scheduleList = new ArrayList<Schedule>();
        if (conn != null) {
            try {
                PreparedStatement stmt = conn.prepareStatement("SELECT * FROM schedule" +
                        " INNER JOIN movie ON schedule.movie_id = movie.movie_id" +
                        " LIMIT ? OFFSET ?");
                stmt.setInt(1, limit);
                stmt.setInt(2, offset);
                ResultSet result = stmt.executeQuery();
                while (result.next()) {
                    scheduleList.add(getScheduleFromResultSet(result));
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
        return scheduleList;
    }

    public void insertSchedule(Schedule schedule) {
        Connection conn = Database.getConnection();
        if (conn != null) {
            try {
                PreparedStatement stmt = conn.prepareStatement("INSERT INTO schedule " +
                        "(schedule_id, movie_id, theater_id, date, time, type) " +
                        "VALUES(?,?,?,?,?,?)");
                stmt.setInt(1, schedule.getScheduleId());
                stmt.setInt(2, schedule.getMovieId());
                stmt.setInt(3, schedule.getTheraterId());
                stmt.setDate(4, new java.sql.Date(schedule.getDate().getTime()));
                stmt.setTime(5, new java.sql.Time(schedule.getTime().getTime()));
                stmt.setString(6, schedule.getType());
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

    public Schedule updateSchedule(Schedule schedule) {
        Connection conn = Database.getConnection();
        if (conn != null) {
            try {
                PreparedStatement stmt = conn.prepareStatement("UPDATE schedule SET " +
                        "movie_id = ?, theater_id = ?, date = ?, time = ?, type = ?" +
                        "WHERE schedule_id = ?");
                stmt.setInt(1, schedule.getMovieId());
                stmt.setInt(2, schedule.getTheraterId());
                stmt.setDate(3, new java.sql.Date(schedule.getDate().getTime()));
                stmt.setTime(4, new java.sql.Time(schedule.getTime().getTime()));
                stmt.setString(5, schedule.getType());
                stmt.setInt(6, schedule.getScheduleId());
                stmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
                schedule = null;
            } finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    schedule = null;
                }
            }
        }
        return schedule;
    }

    public int deleteSchedule(int scheduleId) {
        Connection conn = Database.getConnection();
        if (conn != null) {
            try {
                PreparedStatement stmt = conn.prepareStatement("DELETE FROM schedule " +
                        "WHERE schedule_id = ?");
                stmt.setInt(1, scheduleId);
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

    public int getPK() {
        Connection conn = Database.getConnection();
        int pk = 0;
        if (conn != null) {
            try {
                PreparedStatement stmt = conn.prepareStatement("SELECT schedule_id FROM schedule ORDER BY schedule_id DESC LIMIT 1");
                ResultSet result = stmt.executeQuery();
                result.next();
                pk = result.getInt("schedule_id");
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

    private Schedule getScheduleFromResultSet(ResultSet result) throws SQLException {
        int scheduleId = result.getInt("schedule_id");
        int movieId = result.getInt("movie_id");
        int theaterId = result.getInt("theater_id");
        Date date = result.getDate("date");
        Date time = result.getDate("time");
        String type = result.getString("type");
        Movie movie = MovieDAO.getMovieFromResultSet(result);
        return new Schedule(scheduleId, movieId, theaterId, date, time, type, movie);
    }

}
