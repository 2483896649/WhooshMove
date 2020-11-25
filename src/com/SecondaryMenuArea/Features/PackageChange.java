package com.SecondaryMenuArea.Features;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @program: 1961179张星宇
 * @description: 套餐变更
 * @author: 星子
 * @create: 2020-11-25 16:34
 **/
public class PackageChange {
    public void Package_Change(String phoneNumber, File file) {
        Scanner scanner = new Scanner(System.in);
        String enter = null;
        List list = new ArrayList();
        while (true) {
            System.out.println("------套餐变更------");
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
                    /**套餐费用*/
                    list.add(58);
                    break;
                case "2":
                    list.add("网虫套餐");
                    /**通话时间*/
                    list.add(0);
                    /**互联网流量*/
                    list.add(3);
                    /**短信数量*/
                    list.add(0);
                    /**套餐费用*/
                    list.add(68);
                    break;
                case "3":
                    list.add("超人套餐");
                    /**通话时间*/
                    list.add(200);
                    /**互联网流量*/
                    list.add(1);
                    /**短信数量*/
                    list.add(50);
                    /**套餐费用*/
                    list.add(78);
                    break;
                default:
                    System.out.println("----------");
                    System.out.println("输入错误！请重新输入！");
            }

            doesTheCardNumberExist(phoneNumber, file, list);
            break;
        }
    }


    /**
     * 判断用户卡号在文件中是否存在
     *
     * @param phoneNumber
     * @param file
     * @param list
     * @return
     */
    public void doesTheCardNumberExist(String phoneNumber, File file, List list) {


        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader br = new BufferedReader(fileReader);
            String line = br.readLine();
            while (line != null) {
                String[] a = null;
                a = line.split(",");
                if (phoneNumber.equals(a[0])) {
                    double d = Double.parseDouble(a[3]);
                    int lis = (int) list.get(4);
                    String lisString= String.valueOf(list.get(0));
                    if (a[4].equals(lisString)) {
                        System.out.println("对不起！您已经是该套餐用户，无需更换套餐");
                    }
                    else {
                        if (d - lis < 0) {
                            System.out.println("对不起，您的余额不足以支付新套餐本月资费，请充值后在办理更换套餐业务！");
                        } else {
                            double c = d - lis;
                            list.add(c);
                            /**修改用户信息*/
                            amend(file, list, phoneNumber);

                        }
                    }
                    break;
                }
                line = br.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    /**
     * 修改用户信息
     *
     * @param file
     * @param list
     * @param phoneNumber
     */

    public void amend(File file, List list, String phoneNumber) {
        String[] arr = null;
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String file_text = bufferedReader.readLine();
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            PrintWriter printWriter = new PrintWriter(bufferedWriter);

            while (file_text != null) {
                String file_text1 = file_text;
                arr = file_text1.split(",");
                if (arr[0].equals(phoneNumber)) {
                        printWriter.println(arr[0] + "," + arr[1] + "," + arr[2] + "," + list.get(5) + "," + list.get(0) + "," + list.get(1) + "," + list.get(2) + "," + list.get(3));
                        System.out.println("更换套餐成功！" + list.get(5) + ":通话时长为：" + list.get(1) + "分钟/月，短信条数为" + list.get(3) + "条/月，上网流量为" + list.get(2) + "GB/月，资费为" + list.get(4) + "元/月。");
                } else {
                    printWriter.println(file_text);
                }
                file_text = bufferedReader.readLine();
            }
            bufferedReader.close();
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
