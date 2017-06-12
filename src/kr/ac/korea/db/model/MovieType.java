package kr.ac.korea.db.model;

/**
 * Created by ffaass on 2017-06-11.
 * 영화종류를 나타내는 객체
 */
public class MovieType {
    private String type;
    private int price;

    public MovieType(String type, int price) {
        this.type = type;
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
