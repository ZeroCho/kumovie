package kr.ac.korea.db.dao;

import kr.ac.korea.db.Database;
import kr.ac.korea.db.model.Reservation;
import kr.ac.korea.db.model.Schedule;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ffaass on 2017-06-11.
 */
public class ReservationDAO {

    public void insertReservation(Reservation reservation) {
        Connection conn = Database.getConnection();
        if (conn != null) {
            try {
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
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public int getNextReservationOrder(int scheduleId) {
        Connection conn = Database.getConnection();
        int count = 0;
        if (conn != null) {
            try {
                PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(1) FROM reservation" +
                        " WHERE schedule_id = ?");
                stmt.setInt(1, scheduleId);
                ResultSet result = stmt.executeQuery();

                if (result.next()) {
                    count = result.getInt(1) + 1;
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
        return count;
    }
}
