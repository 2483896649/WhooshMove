package com.SecondaryMenuArea.Features;

import java.io.*;

/**
 * @program: 1961179张星宇
 * @description: 办理退网（注销用户）
 * @author: 星子
 * @create: 2020-11-25 17:35
 **/
public class LogOutUser {
    public void logout(String phoneNumber, File file){
        System.out.println("------办理退网------");

        String[]  file_tetx_arr=null;
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
                file_tetx_arr = file_text1.split(",");
                if(file_tetx_arr[0].equals(phoneNumber)){
                    System.out.println("卡号"+phoneNumber+"办理退网成功！");
                    System.out.println("谢谢使用！");
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
