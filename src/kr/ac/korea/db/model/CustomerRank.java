package kr.ac.korea.db.model;

/**
 * Created by ffaass on 2017-06-11.
 */
public class CustomerRank {
    private String customerRank;
    private int discount;

    public CustomerRank(String customerRank, int discount) {
        this.customerRank = customerRank;
        this.discount = discount;
    }

    public String getCustomerRank() {
        return customerRank;
    }

    public void setCustomerRank(String customerRank) {
        this.customerRank = customerRank;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }
}
