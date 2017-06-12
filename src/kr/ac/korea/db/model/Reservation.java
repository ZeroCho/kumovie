package kr.ac.korea.db.model;

/**
 * Created by ffaass on 2017-06-11.
 * 예약을 나타내는 객체
 */
public class Reservation {
    private int scheduleId;
    private String customerId;
    private int reservationOrder;

    public Reservation(int scheduleId, String customerId, int reservationOrder) {
        this.scheduleId = scheduleId;
        this.customerId = customerId;
        this.reservationOrder = reservationOrder;
    }

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public int getReservationOrder() {
        return reservationOrder;
    }

    public void setReservationOrder(int reservationOrder) {
        this.reservationOrder = reservationOrder;
    }
}
