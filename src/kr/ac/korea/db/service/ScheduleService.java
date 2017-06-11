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

        Date date = getDate(dateString);
        Date time = getTime(timeString);
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

        Date date = getDate(dateString);
        Date time = getTime(timeString);

        return updateSchedule(new Schedule(scheduleId, movieId, theraterId, date, time, type, null));
    }


    public List<Schedule> searchSchedule(String movieName,
                                         String type,
                                         String dateString,
                                         String timeString) {

        Date date = getDate(dateString);
        Date time = getTime(timeString);
        return scheduleDAO.searchSchedule(movieName, type, date, time);
    }


    public boolean deleteSchedule(int scheduleId) {
        return scheduleDAO.deleteSchedule(scheduleId) == 1;
    }

    private Date getDate(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    private Date getTime(String timeString) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        Date time = null;
        try {
            time = timeFormat.parse(timeString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return time;
    }
}
