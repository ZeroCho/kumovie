package kr.ac.korea.db.service;

import kr.ac.korea.db.dao.ReservationDAO;
import kr.ac.korea.db.dao.ScheduleDAO;
import kr.ac.korea.db.model.Customer;
import kr.ac.korea.db.model.Reservation;
import kr.ac.korea.db.model.Schedule;
import kr.ac.korea.db.util.DateUtil;

import java.util.Date;
import java.util.List;

/**
 * Created by ffaass on 2017-06-11.
 */
public class ReservationService {

    private ReservationDAO reservationDAO;

    public ReservationService() {
        reservationDAO = new ReservationDAO();
    }

    public void addReservation(String customerId,
                               String password,
                               int scheduleId) {
        CustomerService customerService = new CustomerService();
        Customer customer = customerService.getCustomer(customerId, password);
        if (customer == null)
            return;

        int reservationOrder = reservationDAO.getNextReservationOrder(scheduleId);
        Reservation reservation = new Reservation(scheduleId, customerId,  reservationOrder);
        reservationDAO.insertReservation(reservation);
    }
}
