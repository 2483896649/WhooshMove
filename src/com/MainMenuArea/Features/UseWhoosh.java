package com.MainMenuArea.Features;

import com.StorageArea.Storage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * @program: 1961179张星宇
 * @description: 使用嗖嗖功能
 * @author: 星子
 * @create: 2020-11-25 10:45
 **/
public class UseWhoosh {
    static Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
    public void use_Whoosh(File file, String enter, Scanner scanner) throws IOException {
        int max = 5, min = 0;
        while (true) {
            System.out.println("进入使用嗖嗖功能...");
            System.out.println("请输入手机卡号：");
            enter = scanner.nextLine().replace(" ", "");
            if (pattern.matcher(enter).matches()) {
                int ran = (int) (Math.random() * (max - min) + min);

                /**判断是否存在此手机卡号*/
                if( exist(file,enter)){
                    consumptionScene(ran,file,enter);
                }
                else{
                    System.out.println("无此用户！");
                }


            } else {
                System.out.println("输入错误！请输入正确的手机卡号！");

            }
            break;
        }
    }
/**
 * 判断是否存在此手机号
 *  @param file
 * @param enter
 */
public boolean exist(File file, String enter){
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


    /**随机消费场景
     * @param ran
     * @param file
     * @param enter
     **/
    public void consumptionScene(int ran, File file, String enter) throws IOException {
        /**通话时间*/
        long callTime=0;
        /**短信条数*/
        long numberOfSMS=0;
        /**上网流量*/
        long internetTraffic=0;
        switch (ran){
            case 0:
                System.out.println("问候客户，谁知其如此难缠，通话90分钟");
                callTime=90;
                break;
            case 1:
                System.out.println("询问妈妈身体状况，本地通话30分钟");
                callTime=30;
                break;
            case 2:
                System.out.println("参与环境保护实施方案问卷调查，发送短信5条");
                numberOfSMS=5;
                break;
            case 3:
                System.out.println("通知朋友换手机号，发送手机50条");
                numberOfSMS=50;
                break;
            case 4:
                System.out.println("和女友微信视频聊天，使用流量1GB");
                internetTraffic=1;
                break;
            case 5:
                System.out.println("晚上手机在线看韩剧，不留神睡着了！使用流量2GB");
                internetTraffic=2;
                break;
            default:
        }
        /**进行消费结算*/
        consumptionSettlement(callTime,numberOfSMS,internetTraffic,file,enter);
    }

    /**进行消费结算
     * 通话时间
     * @param callTime
     * 短信条数
     * @param numberOfSMS
     * 上网流量
     * @param internetTraffic
     * @param file
     * @param enter   */
    public void consumptionSettlement(long callTime, long numberOfSMS, long internetTraffic, File file, String enter) throws IOException {
        /**读取文件*/
        FileReader fileReader = new FileReader(file);
        BufferedReader br = new BufferedReader(fileReader);
        String file_text = br.readLine();
       /**存储文件内容的数组*/
       String[] arr=null;

        /**便写文件*/
        FileWriter fileWriter=new FileWriter(file);
        BufferedWriter bw=new BufferedWriter(fileWriter);
        /**导出文件并替换*/
        PrintWriter printWriter = new PrintWriter(bw);


        while (file_text != null) {
            String file_text1 = file_text;
            arr = file_text1.split(",");
            if(arr[0].equals(enter)){
                /**结算（2）*/
              List list= settleAccounts(arr,callTime,numberOfSMS,internetTraffic,enter);
                /**类型*/
                String a1= String.valueOf(list.get(0));

                if (a1.equals("通话时间")) {
                    printWriter.println(arr[0]+","+arr[1]+","+arr[2]+","+list.get(1)+","+arr[4]+","+list.get(3)+","+arr[6]+","+arr[7]);

                }
                 else if (a1.equals("上网流量")) {
                    printWriter.println(arr[0]+","+arr[1]+","+arr[2]+","+list.get(1)+","+arr[4]+","+arr[5]+","+list.get(3)+","+arr[7]);

                }
                else if (a1.equals("短信条数")) {
                    printWriter.println(arr[0]+","+arr[1]+","+arr[2]+","+list.get(1)+","+arr[4]+","+arr[5]+","+arr[6]+","+list.get(3));

                }
            }
            else{

                printWriter.println(file_text);
            }
            file_text = br.readLine();
        }
        printWriter.flush();
        br.close();
        printWriter.close();
    }


    /**结算（2）
     * @param arr
     * @param callTime
     * @param numberOfSMS
     * @param internetTraffic
     * @param enter  */
    public List settleAccounts(String[] arr, long callTime, long numberOfSMS, long internetTraffic, String enter){

        String a1="";
        if(callTime>0){
            /**通话时间*/
            a1="通话时间";
        }
        else if(numberOfSMS>0){
            /**短信条数*/
            a1="短信条数";
        }
        else if(internetTraffic>0){
            /**上网流量*/
            a1="上网流量";
        }
        List list=new ArrayList();
        /**通话时间*/
        double callTime1= Double.parseDouble(arr[5]);
        double callTime2=callTime1-callTime;
        /**上网流量*/
        double internetTraffic1= Double.parseDouble(arr[6]);
        double internetTraffic2=internetTraffic1-internetTraffic;
        /**短信条数*/
        double numberOfSMS1= Double.parseDouble(arr[7]);
        double numberOfSMS2=numberOfSMS1-numberOfSMS;

        /**用户余额*/
      double over= Double.parseDouble(arr[3]);
        /**类型*/
        double called=0;
        switch (a1){
            case "通话时间":
                if(callTime2<=0){
                    /**转为正数*/
                    callTime2=Math.abs(callTime2);
                    /**计算需要扣多少钱*/
                    double b= callTime2*0.2;

                    if(over-b<=0){
                        double c= over;
                         called=(double)(over/0.2);
                        over=0;
                        /**类型*/
                        list.add(a1);
                        /**用户余额*/
                        list.add(over);
                        /**已经通话时长*/
                        list.add(called);
                        /**余额不足时的提示*/
                        System.out.println("本次已通话"+called+"分钟，您的余额不足，请充值后在使用！");
                        /**用户剩余通话时长*/
                        list.add(0);
                        /**扣费金额*/
                        list.add((double)c);
                    }
                    else {
                        /**类型*/
                        list.add(a1);
                        /**用户余额*/
                        double c=over-b;
                        c=(double) Math.round(c * 100) / 100;
                        list.add(c);
                        /**已经通话时长*/
                        list.add((double)callTime);
                        /**用户剩余通话时长*/
                        list.add(0);
                        /**扣费金额*/
                        list.add((double)b);

                    }
                }
                else {
                    /**类型*/
                    list.add(a1);
                    /**用户余额*/
                    list.add(over);
                    /**已经通话时长*/
                    list.add((double)callTime);
                    /**用户剩余通话时长*/
                    list.add(callTime2);
                    /**扣费金额*/
                    list.add((double)0);

                }
                break;
            case "上网流量":
                if(internetTraffic2<=0){
                    /**转为正数*/
                    internetTraffic2=Math.abs(internetTraffic2);
                    /**计算需要扣多少钱*/
                    internetTraffic2*=1024;
                    double b= internetTraffic2*0.1;
                    if(over-b<=0){
                        double c= over;
                        called= (double)(over/0.1);
                        over=0;
                        /**类型*/
                        list.add(a1);
                        /**用户余额*/
                        list.add(over);
                        /**已经使用的流量*/
                        called/=1024;
                        called=(double) Math.round(called * 100) / 100;
                        list.add((double)called);
                        /**余额不足时的提示*/
                        System.out.println("本次已使用上网流量"+called+"GB，您的余额不足，请充值后在使用！");
                        /**用户剩余流量*/
                        list.add(0);
                        /**扣费金额*/
                        list.add((double)c);
                    }
                    else {
                        /**类型*/
                        list.add(a1);
                        /**用户余额*/
                        double c=over-b;
                        list.add(c);
                        /**已经使用的流量*/
                        list.add((double)callTime);
                        /**用户剩余流量*/
                        list.add(0);
                        /**扣费金额*/
                        list.add((double)b);

                    }
                }
                else {
                    /**类型*/
                    list.add(a1);
                    /**用户余额*/
                    list.add(over);
                    /**已经使用的流量*/
                    list.add((double)internetTraffic);
                    /**用户剩余流量*/
                    list.add(internetTraffic2);
                    /**扣费金额*/
                    list.add((double)0);

                }
                break;
            case "短信条数":
                if(numberOfSMS2<=0){
                    /**转为正数*/
                    numberOfSMS2=Math.abs(numberOfSMS2);

                    /**计算需要扣多少钱*/
                    double b= numberOfSMS2*0.1;

                    if(over-b<=0){
                        double c= over;
                         called=(double) (over/0.1);
                        over=0;
                        /**类型*/
                        list.add(a1);
                        /**用户余额*/
                        list.add(over);
                        /**已经使用的短信条数*/
                        list.add((double)called);
                        /**余额不足时的提示*/
                        System.out.println("本次已发送"+called+"条短信，您的余额不足，请充值后在使用！");
                        /**用户剩余短信条数*/
                        list.add(0);
                        /**扣费金额*/
                        list.add((double)c);
                    }
                    else {
                        /**类型*/
                        list.add(a1);
                        /**用户余额*/
                        double c=over-b;
                        c=(double) Math.round(c * 100) / 100;
                        list.add(c);
                        /**已经使用的短信条数*/
                        list.add((double)numberOfSMS);
                        /**用户剩余短信条数*/
                        list.add(0);
                        /**扣费金额*/
                        list.add((double)b);

                    }

                }
                else {
                    /**类型*/
                    list.add(a1);
                    /**用户余额*/
                    list.add(over);
                    /**已经使用的短信条数*/
                    list.add((double)numberOfSMS);
                    /**用户剩余短信条数*/
                    list.add(numberOfSMS2);
                    /**扣费金额*/
                    list.add((double)0);

                }
                break;
            default:
        }
        ExpensesRecord(list,enter);
        return list;
    }


    /**存储消费记录
     * @param list
     * @param enter*/
    public void ExpensesRecord(List list, String enter){
        Storage storage=new Storage();
        File fileIO=storage.storageIO();

        FileWriter fw= null;
        try {
            fw = new FileWriter(fileIO,true);
            BufferedWriter bw=new BufferedWriter(fw);
            String a= String.valueOf(list.get(0));
            double b= (double) list.get(2);
            double g4= (double) list.get(4);
            double g4_1=Math.abs(g4);
            if(b>0){
                System.out.println("已添加一条消费记录");
                bw.write(enter+","+a+","+list.get(2)+","+g4_1+"\n");
            }
            bw.flush();
            bw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
