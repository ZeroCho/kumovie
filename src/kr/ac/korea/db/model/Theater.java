package kr.ac.korea.db.model;

/**
 * Created by ffaass on 2017-06-11.
 * 상영관을 나타내는 객체
 */
public class Theater {
    private int therterId;
    private String location;
    private int seatNum;

    public Theater(int therterId, String location, int seatNum) {
        this.therterId = therterId;
        this.location = location;
        this.seatNum = seatNum;
    }

    public int getTherterId() {
        return therterId;
    }

    public void setTherterId(int therterId) {
        this.therterId = therterId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getSeatNum() {
        return seatNum;
    }

    public void setSeatNum(int seatNum) {
        this.seatNum = seatNum;
    }
}
