package com.MainMenuArea;

import com.MainMenuArea.Features.Recharge;
import com.MainMenuArea.Features.UseWhoosh;
import com.MainMenuArea.Features.UserLogin;
import com.MainMenuArea.Features.UserRegistration;
import com.StorageArea.Storage;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * @program: 1961179张星宇
 * @description: 实现主菜单功能
 * @author: 星子
 * @create: 2020-11-24 17:09
 **/
public class MainMenu {
    public void mainMenu() throws IOException {


        /*用户存储文件的路径*/
        Storage storage=new Storage();
        File file=storage.UserInformationStorage();


        Scanner scanner=new Scanner(System.in);
        String enter=null;
        /**while的开关*/
        boolean power_switch=true;

        while (power_switch){
            System.out.println("--------------------------欢迎使用嗖嗖移动大厅--------------------------");
            System.out.println("\t1.用户登录 \t2.用户注册 \t3.使用嗖嗖 ");
            System.out.println("\t4.话费充值 \t5.资费说明 \t6.退出系统 ");
            System.out.println("--------------------------------------------------------------------");
            System.out.print("请选择《1~6》:");
            enter=scanner.nextLine().replaceAll(" ", "");
            switch (enter){
                /**用户登录           √*/
                case "1":
                    UserLogin userLogin=new UserLogin();
                    userLogin.login(file,scanner);
                    break;
                /**用户注册          √  */
                case "2":
                    UserRegistration userRegistration=new UserRegistration();
                    userRegistration.registered(file,enter,scanner);
                    break;
                /**使用嗖嗖            √*/
                case "3":
                    UseWhoosh uw=new UseWhoosh();
                    uw.use_Whoosh(file,enter,scanner);
                    break;
                /**话费充值                √*/
                case "4":
                    Recharge recharge=new Recharge();
                    recharge.recipe(file,enter,scanner);
                    break;
                /**资费说明                √*/
                case "5":
                    System.out.println("------资费说明------");
                    System.out.println("话痨套餐：通话时长为500分钟，短信条数为30，无上网流量。");
                    System.out.println("网虫套餐:无通话时长，无短信条数，上网流量为3GB。");
                    System.out.println("超人套餐:通话时长为200分钟，短信条数为50，上网流量为1GB。");
                    break;
                /**退出系统                √*/
                case "6":
                    System.out.println("退出中。。。");
                    Thread.currentThread();
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("退出成功！");
                    System.exit(0);
                    break;
                default:
                    System.out.println("输入错误！请重新输入！");
            }
        }
    }
}
