package kr.ac.korea.db.dao;

import kr.ac.korea.db.Database;
import kr.ac.korea.db.model.Reservation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by ffaass on 2017-06-11.
 * JDBC를 이용하여 DB의 예약 테이블에 접근하는 객체. 예약 추가만 구현되어 있음.
 */
public class ReservationDAO {

    public void insertReservation(Reservation reservation) {
        Connection conn = Database.getConnection();
        if (conn != null) {
            try {
                //테이블에 새로운 예약을 insert하는 쿼리
                PreparedStatement stmt = conn.prepareStatement("INSERT INTO reservation " +
                        "(schedule_id, customer_id, reservation_order) " +
                        "VALUES(?,?,?)");
                stmt.setInt(1, reservation.getScheduleId());
                stmt.setString(2, reservation.getCustomerId());
                stmt.setInt(3, reservation.getReservationOrder());
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

    /*
     * 고객이 스케줄을 예약 시, 예약한 순서대로 예약 순서를 부여해야 함
     * 다음에 부여될 예약 순서를 계산해서 반환
     */
    public int getNextReservationOrder(int scheduleId) {
        Connection conn = Database.getConnection();
        int count = 0;
        if (conn != null) {
            try {
                // 해당 스케줄의 예약 갯수를 반환하는 쿼리
                PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(1) FROM reservation" +
                        " WHERE schedule_id = ?");
                stmt.setInt(1, scheduleId);
                ResultSet result = stmt.executeQuery();

                if (result.next()) {
                    //다음 예약 순서는 현재 최대 예약 순서 + 1
                    count = result.getInt(1) + 1;
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
        return count;
    }
}
