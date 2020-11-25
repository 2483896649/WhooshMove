package com.SecondaryMenuArea;

import com.MainMenuArea.MainMenu;
import com.SecondaryMenuArea.Features.*;
import com.StorageArea.Storage;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * @program: 1961179张星宇
 * @description: 二级菜单功能
 * @author: 星子
 * @create: 2020-11-25 08:59
 **/
public class SecondaryMenu {
    public void SM(String phoneNumber) throws IOException {
        /**
         * 用户手机号
         * phoneNumber
         * */
        Storage storage = new Storage();
        File file = storage.UserInformationStorage();
        Scanner scanner = new Scanner(System.in);
        String entered = null;
        /**主菜单类*/
        MainMenu mainMenu = new MainMenu();
        while (true) {
            System.out.println("--------------------------嗖嗖移动用户菜单--------------------------");
            System.out.println("1.本月账单查询");
            System.out.println("2.套餐余量查询");
            System.out.println("3.打印消费订单");
            System.out.println("4.套餐变更");
            System.out.println("5.办理退网");
            System.out.println("--------------------------------------------------------------------");
            System.out.print("请选择《1~6，其他键返回到主菜单》:");
            entered = scanner.nextLine().replace(" ", "");
            switch (entered) {
                case "1":
                    /**本月账单查询          √*/
                    ThisMonthBill thisMonthBill = new ThisMonthBill();
                    thisMonthBill.bill(phoneNumber, file);
                    break;
                case "2":
                    /**套餐余量查询     √*/
                    PackageBalanceQuery packageBalanceQuery = new PackageBalanceQuery();
                    packageBalanceQuery.Package_Balance_Query(phoneNumber, file);
                    break;
                case "3":
                    /**打印消费订单   √*/
                    PrintConsumerOrder pCO = new PrintConsumerOrder();
                    pCO.Print_Consumer_Order(phoneNumber);
                    break;
                case "4":
                    /**套餐变更       √*/
                    PackageChange packageChange = new PackageChange();
                    packageChange.Package_Change(phoneNumber, file);
                    break;
                case "5":
                    /**办理退网          √*/
                    LogOutUser logOutUser = new LogOutUser();
                    logOutUser.logout(phoneNumber, file);
                default:
                    /**返回主菜单         √*/
                    mainMenu.mainMenu();
                   break;
            }
        }
    }
}
