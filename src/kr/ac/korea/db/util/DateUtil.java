package kr.ac.korea.db.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ffaass on 2017-06-11.
 */
public class DateUtil {

    public static Date getDate(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static Date getTime(String timeString) {
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
