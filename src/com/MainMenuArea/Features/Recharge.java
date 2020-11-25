package com.MainMenuArea.Features;

import java.io.*;
import java.util.Scanner;

/**
 * @program: 1961179张星宇
 * @description: 话费充值
 * @author: 星子
 * @create: 2020-11-25 17:51
 **/
public class Recharge {
    public void  recipe(File file, String enter, Scanner scanner){
        System.out.print("请输入充值卡号：");
        String cardNumber=null;
        enter=scanner.nextLine().replace(" ", "");
        cardNumber=enter;
        if(judgment(file,enter)){
            System.out.println("请输入充值金额：");
            enter=scanner.nextLine().replace(" ", "");
            double a= Double.parseDouble(enter);
            rec(a,file,cardNumber);
        }
        else {
            System.out.println("无此用户！");
        }
    }
    /**判断是否有此卡号
     * @param file
     * @param enter*/
    public boolean judgment(File file, String enter){
        boolean bool=false;
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader br=new BufferedReader(fileReader);
            String line= br.readLine();
            while (line!=null){
                String[] a=null;
                a=line.split(",");
                if(enter.equals(a[0])){
                    bool=true;
                    break;
                }
                line=br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    return bool;
    }

    /**充值
     * @param enter
     * @param file
     * @param cardNumber */
    public void rec(double enter, File file, String cardNumber){
        String[]  arr=null;
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String file_text = bufferedReader.readLine();
            FileWriter fileWriter=new FileWriter(file);
            BufferedWriter bufferedWriter=new BufferedWriter(fileWriter);
            PrintWriter printWriter = new PrintWriter(bufferedWriter);

            while (file_text != null) {
                String file_text1 = file_text;
                arr = file_text1.split(",");
                if(arr[0].equals(cardNumber)){
                    double a= Double.parseDouble(arr[3])+enter;
                    printWriter.println(arr[0]+","+arr[1]+","+arr[2]+","+a+","+arr[4]+","+arr[5]+","+arr[6]+","+arr[7]);
                    System.out.println("充值成功，当前话费余额为"+a+"元。");
                }
                else{
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
