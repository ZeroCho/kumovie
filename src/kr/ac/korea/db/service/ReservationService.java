package kr.ac.korea.db.service;

import kr.ac.korea.db.dao.ReservationDAO;
import kr.ac.korea.db.model.Customer;
import kr.ac.korea.db.model.Reservation;

/**
 * Created by ffaass on 2017-06-11.
 */
public class ReservationService {

    private ReservationDAO reservationDAO;

    public ReservationService() {
        reservationDAO = new ReservationDAO();
    }

    //새로운 예약을 추가하는 메서드
    public void addReservation(String customerId,
                               String password,
                               int scheduleId) {
        CustomerService customerService = new CustomerService();
        //입력된 id, password로 유저를 찾아 유저가 없으면 예약 실패
        Customer customer = customerService.getCustomer(customerId, password);
        if (customer == null)
            return;

        //다음 예약 순서를 구함
        int reservationOrder = reservationDAO.getNextReservationOrder(scheduleId);
        Reservation reservation = new Reservation(scheduleId, customerId,  reservationOrder);
        reservationDAO.insertReservation(reservation);
    }
}
