package kr.ac.korea.db.service;

import kr.ac.korea.db.dao.ScheduleDAO;
import kr.ac.korea.db.model.Schedule;

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

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        try {

            Date date = dateFormat.parse(dateString);
            Date time = timeFormat.parse(timeString);
            // DB에 auto increment 안걸려있어서 직접 PK 구함
            int scheduleId = scheduleDAO.getPK() + 1;
            Schedule schedule = new Schedule(scheduleId, movieId, theraterId, date, time, type, null);
            scheduleDAO.insertSchedule(schedule);
        } catch (ParseException e) {
            e.printStackTrace();
        }
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
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        Date date = null;
        Date time = null;
        try {
            date = dateFormat.parse(dateString);
            time = timeFormat.parse(timeString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return updateSchedule(new Schedule(scheduleId, movieId, theraterId, date, time, type, null));
    }

    public boolean deleteSchedule(int scheduleId) {
        return scheduleDAO.deleteSchedule(scheduleId) == 1;
    }
}
