package kr.ac.korea.db.service;

import kr.ac.korea.db.dao.ScheduleDAO;
import kr.ac.korea.db.model.Schedule;
import kr.ac.korea.db.util.DateUtil;

import java.util.Date;
import java.util.List;

/**
 * Created by ffaass on 2017-06-11.
 * 스케줄의 CRUD를 위한 인터페이스를 제공하는 클래스
 */
public class ScheduleService {
    private ScheduleDAO scheduleDAO;

    public ScheduleService() {
        scheduleDAO = new ScheduleDAO();
    }

    //스케줄을 추가하는 메서드
    public void addSchedule(int movieId,
                            int theraterId,
                            String dateString,
                            String timeString,
                            String type) {

        Date date = DateUtil.getDate(dateString);
        Date time = DateUtil.getTime(timeString);
        // DB의 PK에 auto_increment가 걸려있지 않아 직접 PK를 계산
        int scheduleId = scheduleDAO.getPK() + 1;
        Schedule schedule = new Schedule(scheduleId, movieId, theraterId, date, time, type, null);
        scheduleDAO.insertSchedule(schedule);
    }

    public Schedule updateSchedule(Schedule schedule) {
        return scheduleDAO.updateSchedule(schedule);
    }

    //schedule_id가 scheduleId와 같은 스케줄을 찾아 나머지 정보를 업데이트
    public Schedule updateSchedule(int scheduleId,
                                   int movieId,
                                   int theraterId,
                                   String dateString,
                                   String timeString,
                                   String type) {

        Date date = DateUtil.getDate(dateString);
        Date time = DateUtil.getTime(timeString);

        return updateSchedule(new Schedule(scheduleId, movieId, theraterId, date, time, type, null));
    }



    //영화의 이름, 종류, 날짜, 시간 기준으로 검색해서 4개가 모두 일치하는 스케줄을 반환
    public List<Schedule> searchSchedule(String movieName,
                                         String type,
                                         String dateString,
                                         String timeString) {

        Date date = DateUtil.getDate(dateString);
        Date time = DateUtil.getTime(timeString);
        return scheduleDAO.searchSchedule(movieName, type, date, time);
    }

    //스케줄을 삭제하는 메서드
    public boolean deleteSchedule(int scheduleId) {
        return scheduleDAO.deleteSchedule(scheduleId) == 1;
    }
}
