package com.MainMenuArea.Features;

import com.UserInformatioArea.UserInfo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @program: 1961179张星宇
 * @description: 用户注册
 * @author: 星子
 * @create: 2020-11-24 17:31
 **/
public class UserRegistration {
    /**用户注册
     * @param file
     * @param enter
     * @param scanner*/
    /**
     * 判断字符串是不是为汉字
     */
    static Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
    /**
     * 判断字符串是不是数字
     */
    static Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");

    public void registered(File file, String enter, Scanner scanner) {
        /**用户手机号*/
        String user_card_number = null;
        /**用户姓名*/
        String name = null;
        /**用户密码*/
        String password;
        /**用户套餐*/
        String setMeal;
        /**用户话费*/
        long PhoneBill;

        System.out.println("-------------");
        System.out.println("已进入用户注册系统");

        /**用户开卡*/
        user_card_number = UserPhoneNumber(scanner, enter,file);
        /**用户套餐*/
        /**存储套餐信息的数组*/
        List list= userPlan(scanner, enter);
        setMeal = (String) list.get(0);
        /**用户姓名*/
        name = username(scanner, enter);
        /**用户密码*/
        password = userPassword(scanner, enter);
        /**用户话费*/
        PhoneBill = UserCallCharges(enter, scanner, setMeal);

        /**将用户信息传入到I/O文件中*/
        UserInfo userInfo = new UserInfo(user_card_number, name, password, setMeal, PhoneBill);
        try {
            FileWriter fileWriter = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fileWriter);
            bw.write(userInfo.toString() + "," +list.get(1)+"," +list.get(2)+"," +list.get(3)+"\n");
            bw.flush();
            bw.close();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        /**返回注册成功后的信息*/
        registrationSuccess(userInfo);

    }

    /**
     * 用户开卡
     *  @param scanner
     * @param enter
     * @param file
     */
    public String UserPhoneNumber(Scanner scanner, String enter, File file) {
        List list = CardNumber(file);
        while (true) {
            System.out.println("---可选择的卡号---");
            System.out.println("\t1." + list.get(0) + "\t2." + list.get(1) + "\t3." + list.get(2));
            System.out.println("\t4." + list.get(3) + "\t5." + list.get(4) + "\t6." + list.get(5));
            System.out.println("\t7." + list.get(6) + "\t8." + list.get(7) + "\t9." + list.get(8));
            System.out.print("请选择卡号《1~9》：");
            enter = scanner.nextLine().replace(" ", "");
            switch (enter) {
                case "1":
                    return String.valueOf(list.get(0));

                case "2":
                    return String.valueOf(list.get(1));

                case "3":
                    return String.valueOf(list.get(2));

                case "4":
                    return String.valueOf(list.get(3));

                case "5":
                    return String.valueOf(list.get(4));

                case "6":
                    return String.valueOf(list.get(5));

                case "7":
                    return String.valueOf(list.get(6));

                case "8":

                    return String.valueOf(list.get(7));

                case "9":
                    return String.valueOf(list.get(8));

                default:
                    System.out.println("输入错误!");
            }

        }

    }

    /**
     * 随机生成9个卡号
     * @param file
     */
    public List CardNumber(File file) {
        List list = new ArrayList();
        int max = 99999999, min = 10000000;
        String card_number = "139";
        for (int i = 0, a = 10, b=0; i < a; i++) {
            int ran = (int) (Math.random() * (max - min) + min);
            card_number += ran;


            for (int j = 0; j < list.size(); j++) {
                if (list.get(j).equals(card_number)) {
                    b=i--;
                    break;
                }
            }

            if(doesTheCardNumberExist(card_number,file)){
                list.add(card_number);
                card_number = "139";
            }
            else {
                if(b!=i){
                    i--;
                }

            }


        }
        return list;
    }


    /**
     * 判断用户卡号在文件中是否存在
     * @param card_number
     * @param file
     * @return
     */
    public boolean doesTheCardNumberExist(String card_number, File file){
        boolean bool=true;
        try {
            FileReader fileReader=new FileReader(file);
            BufferedReader br=new BufferedReader(fileReader);
            String line= br.readLine();
            while (line!=null){
                String[] a=null;
                a=line.split(",");
                if(card_number.equals(a[0])){
                    bool=false;
                  break;
                }
                line=br.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return bool;
    }

    /**
     * 用户套餐选择
     *
     * @param scanner
     * @param enter
     */
    public List userPlan(Scanner scanner, String enter) {
        List list =new ArrayList();
        while (true) {
            System.out.println("---可选择的套餐---");
            System.out.println("\t1.话痨套餐 \t2.网虫套餐 \t3.超人套餐 ");
            System.out.print("请选择套餐《1~3》:");
            enter = scanner.nextLine().replace(" ", "");
            switch (enter) {
                case "1":
                    list.add("话痨套餐");
                    /**通话时间*/
                    list.add(500);
                    /**互联网流量*/
                    list.add(0);
                    /**短信数量*/
                    list.add(30);
                    return list;
                case "2":
                    list.add("网虫套餐");
                    /**通话时间*/
                    list.add(0);
                    /**互联网流量*/
                    list.add(3);
                    /**短信数量*/
                    list.add(0);
                    return list;
                case "3":
                    list.add("超人套餐");
                    /**通话时间*/
                    list.add(200);
                    /**互联网流量*/
                    list.add(1);
                    /**短信数量*/
                    list.add(50);
                    return list;
                default:
                    System.out.println("----------");
                    System.out.println("输入错误！请重新输入！");
            }
        }

    }


    /**
     * 用户姓名输入
     *
     * @param scanner
     * @param enter
     */
    public String username(Scanner scanner, String enter) {
        while (true) {

            System.out.print("请输入姓名：");
            enter = scanner.nextLine().replace(" ", "");
            Matcher m = p.matcher(enter);
            if (m.find()) {
                return enter;
            } else {
                System.out.println("----------");
                System.out.println("请重新输入（名字为汉字！）");
            }
        }
    }

    /**
     * 用户密码输入
     *
     * @param scanner
     * @param enter
     */
    public String userPassword(Scanner scanner, String enter) {
        while (true) {
            System.out.print("请输入密码:");
            enter = scanner.nextLine().replace(" ", "");
            Matcher m = p.matcher(enter);
            if (m.find()) {
                System.out.println("----------");
                System.out.println("请重新输入（密码不能有汉字！）");
            } else {
                return enter;
            }
        }
    }


    /**
     * 用户的话费
     *
     * @param enter
     * @param scanner
     * @param setMeal
     */
    public long UserCallCharges(String enter, Scanner scanner, String setMeal) {
        /**套餐费用*/
        long packageTariff = 0;
        /**用户预存费用*/
        long depositedFees = 0;
        while (true) {
            System.out.print("请输入预存话费的金额：");
            enter = scanner.nextLine().replace(" ", "");
            if (pattern.matcher(enter).matches()) {
                switch (setMeal) {
                    case "话痨套餐":
                        packageTariff = 58;
                        break;
                    case "网虫套餐":
                        packageTariff = 68;
                        break;
                    case "超人套餐":
                        packageTariff = 78;
                        break;
                    default:
                        System.out.println("套餐费用错误！（240行）");
                        break;
                }
                depositedFees = Long.parseLong(enter);
                if (depositedFees - packageTariff < 0) {
                    System.out.println("您预存的话费金额不足以支付本月的固定套餐，请重新充值！");
                } else {
                    /**返回减去套餐费用后的余额*/
                    return depositedFees - packageTariff;
                }
            }
        }
    }

    /**
     * 返回注册成功后的信息
     *
     * @param userInfo
     */
    public void registrationSuccess(UserInfo userInfo) {
        System.out.println("注册成功！");
        System.out.println("卡号为：" + userInfo.getUser_card_number());
        System.out.println("用户名为：" + userInfo.getName());
        System.out.println("当前余额为：" + userInfo.getPhoneBill());
        switch (userInfo.getSetMeal()) {
            case "话痨套餐":
                System.out.println("您的套餐为：话痨套餐");
                System.out.println("通话时长为500分钟，短信条数为30，无上网流量。");
                break;
            case "网虫套餐":
                System.out.println("您的套餐为：网虫套餐");
                System.out.println("无通话时长，无短信条数，上网流量为3GB。");
                break;
            case "超人套餐":
                System.out.println("您的套餐为：超人套餐");
                System.out.println("通话时长为200分钟，短信条数为50，上网流量为1GB。");
                break;
            default:
                System.out.println("套餐费用错误！（287行）");
                break;
        }
    }


}


