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
 * JDBC를 이용하여 DB의 스케줄 테이블에 접근하는 객체. 스케줄의 CRUD를 담당함.
 */
public class ScheduleDAO {

    public void insertSchedule(Schedule schedule) {
        Connection conn = Database.getConnection();
        if (conn != null) {
            try {
                //테이블에 새로운 스케줄을 insert하는 쿼리
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
                    //Connection은 쿼리 실행에 문제가 생겨도 닫아줘야 함
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
                //schedule_id가 파라미터로 받은 scheduleId와 같은 row의 attribute들을 수정하는 쿼리
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
                    //Connection은 쿼리 실행에 문제가 생겨도 닫아줘야 함
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    schedule = null;
                }
            }
        }
        //update가 제대로 끝났다면 수정된 스케줄 정보를, 아니라면 null을 반환
        return schedule;
    }

    public int deleteSchedule(int scheduleId) {
        Connection conn = Database.getConnection();
        if (conn != null) {
            try {
                //schedule_id가 파라미터로 받은 scheduleId와 같은 row를 삭제하는 쿼리
                PreparedStatement stmt = conn.prepareStatement("DELETE FROM schedule " +
                        "WHERE schedule_id = ?");
                stmt.setInt(1, scheduleId);
                return stmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    //Connection은 쿼리 실행에 문제가 생겨도 닫아줘야 함
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return 0;
    }

    public List<Schedule> searchSchedule(String movieName,
                                         String type,
                                         Date date,
                                         Date time) {
        Connection conn = Database.getConnection();
        List<Schedule> scheduleList = new ArrayList<Schedule>();
        if (conn != null) {
            try {
                /*
                 * 영화의 이름 검색을 위해 movie 테이블과 JOIN하고,
                 * 영화의 종류와 날짜, 시간이 일치하는 row를 schedule_id 오름차순으로 반환하는 쿼리
                 * theater, movie_type 테이블의 정보를 jsp에서 사용하기 때문에 JOIN했음
                 */
                PreparedStatement stmt = conn.prepareStatement("SELECT * FROM schedule" +
                        " NATURAL JOIN movie" +
                        " NATURAL JOIN theater" +
                        " NATURAL JOIN movie_type" +
                        " WHERE movie.title = ? " +
                        " AND schedule.type = ?" +
                        " AND schedule.date = ?" +
                        " AND schedule.time = ?" +
                        " ORDER BY schedule_id ASC");
                stmt.setString(1, movieName);
                stmt.setString(2, type);
                stmt.setDate(3, new java.sql.Date(date.getTime()));
                stmt.setTime(4, new java.sql.Time(time.getTime()));

                ResultSet result = stmt.executeQuery();
                while (result.next()) {
                    scheduleList.add(getScheduleFromResultSet(result));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    //Connection은 쿼리 실행에 문제가 생겨도 닫아줘야 함
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return scheduleList;
    }

    //PK에 auto_increment가 걸려있지 않아 다음 PK를 계산할 때 사용하는 메서드
    //현재 테이블에 있는 schedule_id 중 가장 큰 schedule_id를 반환
    public int getPK() {
        Connection conn = Database.getConnection();
        int pk = 0;
        if (conn != null) {
            try {
                //schedule_id가 가장 큰 스케줄의 schedule_id를 반환하는 쿼리
                PreparedStatement stmt = conn.prepareStatement("SELECT schedule_id FROM schedule ORDER BY schedule_id DESC LIMIT 1");
                ResultSet result = stmt.executeQuery();
                result.next();
                pk = result.getInt("schedule_id");
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    //Connection은 쿼리 실행에 문제가 생겨도 닫아줘야 함
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return pk;
    }

    //ResultSet으로부터 스케줄 객체를 생성
    public static Schedule getScheduleFromResultSet(ResultSet result) throws SQLException {
        int scheduleId = result.getInt("schedule_id");
        int movieId = result.getInt("movie_id");
        int theaterId = result.getInt("theater_id");
        Date date = result.getDate("date");
        Date time = new Date(result.getTime("time").getTime());
        String type = result.getString("type");
        Movie movie = MovieDAO.getMovieFromResultSet(result);
        Schedule schedule = new Schedule(scheduleId, movieId, theaterId, date, time, type, movie);
        //JOIN된 상영관과 영화종류 객체도 생성해서 추가
        //만약 JOIN되지 않았다면 null이 반환되어 저장됨
        schedule.setTheater(TheaterDAO.getTheaterFromResultSet(result));
        schedule.setMovieType(MovieTypeDAO.getMovieTypeFromResultSet(result));
        return schedule;
    }

}
