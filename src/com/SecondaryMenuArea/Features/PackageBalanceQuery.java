package com.SecondaryMenuArea.Features;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * @program: 1961179张星宇
 * @description: 套餐余额查询
 * @author: 星子
 * @create: 2020-11-25 10:14
 **/
public class PackageBalanceQuery {
    public void Package_Balance_Query(String phoneNumber, File file){

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


        System.out.println("------套餐余量查询------");
        System.out.println("您的卡号："+phoneNumber+",套餐内剩余：");
        System.out.println("通话时长："+b[5]+"分钟");
        System.out.println("短信条数："+b[7]+"条");
        double b6= Double.parseDouble(((b[6])));
//        b6=b6/1024;
//        b6=(double) Math.round(b6 * 100) / 100;
        System.out.println("上网流量："+b[6]+"GB");


    }
}
