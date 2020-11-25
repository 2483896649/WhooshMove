package com.MainMenuArea.Features;

import com.SecondaryMenuArea.SecondaryMenu;

import java.io.*;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * @program: 1961179张星宇
 * @description: 用户登录系统
 * @author: 星子
 * @create: 2020-11-24 17:22
 **/
public class UserLogin {
    /**
     * 判断字符串是不是数字
     */
    static Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
    public void login(File file,Scanner scanner) throws IOException {
        System.out.println("-------------");
        System.out.println("已进入用户登录系统");
        String phoneNumber=null,password=null;
        while (true){
            System.out.print("请输入手机卡号：");
            phoneNumber=scanner.nextLine().replace(" ", "");
            System.out.print("请输入密码：");
            password=scanner.nextLine().replace(" ", "");
            if(logins(scanner,file,phoneNumber,password)){
                System.out.println("登录成功！");
                /**二级菜单《嗖嗖移动用户的菜单》（包括1.本月账单查询 2.套餐余量查询 3.打印消费详单 4.套餐变更 5.办理退网（注销用户））*/
                SecondaryMenu secondaryMenu=new SecondaryMenu();
                secondaryMenu.SM(phoneNumber );
                break;
            }
            else {
                System.out.println("卡号或者密码错误！");
            }

        }
    }

    /**
     * 判断
     *  @param scanner
     * @param file
     * @param phoneNumber
     * @param password
     * */
    public boolean logins(Scanner scanner, File file, String phoneNumber, String password){
        boolean bool=false;
        /**判断输入的手机号是否为数字*/
        if(pattern.matcher(phoneNumber).matches()){
            FileReader fileReader= null;
            try {
                fileReader = new FileReader(file);
                BufferedReader br=new BufferedReader(fileReader);
                String line= br.readLine();
                while (line!=null){
                    String[] a=null;
                    a=line.split(",");
                    /**判断手机号和密码是否正确*/
                    if(phoneNumber.equals(a[0])&&password.equals(a[2])){
                        bool= true;
                        break;
                    }
                    line=br.readLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bool;
    }
}
