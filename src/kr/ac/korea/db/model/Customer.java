package kr.ac.korea.db.model;

import java.util.Date;

/**
 * Created by ffaass on 2017-06-11.
 * 고객을 나타내는 객체
 */
public class Customer {
    private String customerId;
    private String password;
    private String name;
    private String phoneNum;
    private Date birthday;
    private String customerRank;

    public Customer(String customerId,
                    String password,
                    String name,
                    String phoneNum,
                    Date birthday,
                    String customerRank) {
        this.customerId = customerId;
        this.password = password;
        this.name = name;
        this.phoneNum = phoneNum;
        this.birthday = birthday;
        this.customerRank = customerRank;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getCustomerRank() {
        return customerRank;
    }

    public void setCustomerRank(String customerRank) {
        this.customerRank = customerRank;
    }
}
