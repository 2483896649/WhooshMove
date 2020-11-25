package com.UserInformatioArea;

/**
 * @program: 1961179张星宇
 * @description: 用户信息的get和set
 * @author: 星子
 * @create: 2020-11-24 18:04
 **/
public class UserInfo {
    /**
     * 用户手机号
     */
    private String user_card_number;
    /**
     * 用户姓名
     */
    private String name;
    /**
     * 用户密码
     */
    private String password;
    /**
     * 用户套餐
     */
    private String setMeal;
    /**
     * 用户话费
     */
    private long PhoneBill;

    public UserInfo(String user_card_number, String name, String password, String setMeal, long phoneBill) {
        this.user_card_number = user_card_number;
        this.name = name;
        this.password = password;
        this.setMeal = setMeal;
        PhoneBill = phoneBill;
    }

    public UserInfo() {
    }

    @Override
    public String toString() {
        return user_card_number + "," + name + "," + password + "," + PhoneBill + "," + setMeal;
    }

    public String getUser_card_number() {
        return user_card_number;
    }

    public void setUser_card_number(String user_card_number) {
        this.user_card_number = user_card_number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSetMeal() {
        return setMeal;
    }

    public void setSetMeal(String setMeal) {
        this.setMeal = setMeal;
    }

    public long getPhoneBill() {
        return PhoneBill;
    }

    public void setPhoneBill(long phoneBill) {
        PhoneBill = phoneBill;
    }
}
