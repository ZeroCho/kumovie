package kr.ac.korea.db.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ffaass on 2017-06-11.
 */
public class DateUtil {

    // yyyy-MM-dd 형식의 String을 Date 클래스로 변환하는 메서드
    // DB의 Date 타입의 값으로 변환할 필요가 있을 때 사용
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

    // HH:mm 형식의 String을 Date 클래스로 변환하는 메서드
    // DB의 Time 타입의 값으로 변환할 필요가 있을 때 사용
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

    //Date 객체에서 시간만 추출해 String으로 변환하는 메서드
    public static String getTimeString(Date date) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        return timeFormat.format(date);
    }
}
