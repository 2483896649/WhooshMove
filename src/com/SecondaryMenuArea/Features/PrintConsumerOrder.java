package com.SecondaryMenuArea.Features;

import com.StorageArea.Storage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: 1961179张星宇
 * @description: 打印消费订单
 * @author: 星子
 * @create: 2020-11-25 10:37
 **/
public class PrintConsumerOrder {
    public void Print_Consumer_Order(String phoneNumber) {
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
                    String lis = arr[1] + "," + arr[2];
                    list.add(lis);
                }
                line = br.readLine();
            }
            removal(list, phoneNumber);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 遍历hashMap并输出
     *
     * @param list
     * @param phoneNumber
     */
    public void removal(List list, String phoneNumber) {
        if(list.isEmpty()){
            System.out.println("对不起，不存在此号码的消费记录，不能打印");
        }
        else {
            System.out.println("------" + phoneNumber + "消费记录------");
            System.out.println("序号" + "\t\t" + "类型" + "\t\t" + "数据（通话《分钟》/上网《MB》/短信《条》）");
            for (int i = 0; i < list.size(); i++) {
                String lis = String.valueOf(list.get(i));
                String[] a = lis.split(",");
                int as = i;
                as++;
                System.out.println(as + "\t\t" + a[0] + "\t" + a[1]);

            }
        }
    }
}
