package com.SecondaryMenuArea.Features;

import com.StorageArea.Storage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: 1961179张星宇
 * @description: 本月账单查询
 * @author: 星子
 * @create: 2020-11-25 09:32
 **/
public class ThisMonthBill {
    public void bill(String phoneNumber, File file){
       String[] b =null;
       boolean bool=false;
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader br=new BufferedReader(fileReader);
            String line= br.readLine();
            while (line!=null){
                String[] a=null;
                a=line.split(",");
                if(phoneNumber.equals(a[0])){
                    b=a;
                    bool=true;
                    break;
                }
                line=br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        /**套餐名称*/
        String  packageName=b[4];
        /**套餐资费*/
        long packageTariff=0;
        /**合计费用*/

        double   total=0;
        if(bool){

            switch (packageName) {
                case "话痨套餐":
                    packageTariff=58;
                    break;
                case "网虫套餐":
                    packageTariff=68;
                    break;
                case "超人套餐":
                    packageTariff=78;
                    break;
                default:
                    System.out.println("----------");
                    System.out.println("输入错误！请重新输入！");
            }
            double exceed= overcharge(phoneNumber);
            total=packageTariff+exceed;
            System.out.println("------本月账单查询------");
            System.out.println("您的卡号："+phoneNumber+",当月账单：");
            System.out.println("套餐资费："+packageTariff);
            System.out.println("合计："+total);
            System.out.println("账户余额："+b[3]);
        }

    }
    /**获取超出的费用
     * @param phoneNumber*/
    public double overcharge(String phoneNumber){
        double a = 0;
        Storage storage = new Storage();
        File fileIO = storage.storageIO();

        List list = new ArrayList();
        String[] arr = null;
        try {
            FileReader fr = new FileReader(fileIO);
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            while (line != null) {
                arr = line.split(",");
                if (phoneNumber.equals(arr[0])) {
                    list.add(arr[3]);
                }
                line = br.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < list.size(); i++) {
            double b=  Double.parseDouble(String.valueOf(list.get(i)));
            a=a+b;
        }

        return a;
    }
}
