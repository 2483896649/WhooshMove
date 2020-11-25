package com.StorageArea;

import java.io.File;
import java.io.IOException;

/**
 * @program: 1961179张星宇
 * @description: 创建用户信息存储的i/o文件
 * @author: 星子
 * @create: 2020-11-24 17:24
 **/
public class Storage {
    /**存储用户信息的I/O文件*/
    static File file1 = new File("File");
    public File UserInformationStorage(){
        File file=new File("File/user.txt");
        if (!file.exists()){
            try {
                file1.mkdirs();
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }
    /**存储用户消费账单的I/O文件*/
    public File storageIO(){
        File fileIO=new File("File/IO.txt");
        if (!fileIO.exists()){
            try {
                file1.mkdirs();
                fileIO.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return fileIO;
    }

}
