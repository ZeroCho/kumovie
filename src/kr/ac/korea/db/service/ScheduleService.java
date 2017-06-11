package kr.ac.korea.db.service;

import kr.ac.korea.db.dao.ScheduleDAO;
import kr.ac.korea.db.model.Schedule;
import kr.ac.korea.db.util.DateUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by ffaass on 2017-06-11.
 */
public class ScheduleService {
    private ScheduleDAO scheduleDAO;

    public ScheduleService() {
        scheduleDAO = new ScheduleDAO();
    }

    public List<Schedule> getScheduleList(int limit, int offset) {
        return scheduleDAO.getScheduleList(limit, offset);
    }

    public void addSchedule(int movieId,
                            int theraterId,
                            String dateString,
                            String timeString,
                            String type) {

        Date date = DateUtil.getDate(dateString);
        Date time = DateUtil.getTime(timeString);
        int scheduleId = scheduleDAO.getPK() + 1;
        Schedule schedule = new Schedule(scheduleId, movieId, theraterId, date, time, type, null);
        scheduleDAO.insertSchedule(schedule);
    }

    public Schedule updateSchedule(Schedule schedule) {
        return scheduleDAO.updateSchedule(schedule);
    }

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


    public List<Schedule> searchSchedule(String movieName,
                                         String type,
                                         String dateString,
                                         String timeString) {

        Date date = DateUtil.getDate(dateString);
        Date time = DateUtil.getTime(timeString);
        return scheduleDAO.searchSchedule(movieName, type, date, time);
    }


    public boolean deleteSchedule(int scheduleId) {
        return scheduleDAO.deleteSchedule(scheduleId) == 1;
    }
}
