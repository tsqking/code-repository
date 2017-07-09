package com.clps.tmp;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * 获取硬盘目录下某个文件夹下的所有文件名称 （用于作为参数传给解析word的方法）
 * @author Administrator
 *
 */
public class GetFileName
{
    public static String [] getFileName(String path)
    {
        File file = new File(path);
        String [] fileName = file.list();
        return fileName;
    }
    public static void getAllFileName(String path,ArrayList<String> fileName)
    {
        File file = new File(path);
        File [] files = file.listFiles();
        String [] names = file.list();
        if(names != null)
        fileName.addAll(Arrays.asList(names));
        for(File a:files)
        {
            if(a.isDirectory())
            {
                getAllFileName(a.getAbsolutePath(),fileName);
            }
        }
    }
    public static void main(String[] args)
    {
        String [] fileName = getFileName("D:\\CLPS\\Work\\FU\\系统");
        for(String name:fileName)
        {
            System.out.println(name);
        }
//        System.out.println("--------------------------------");
//        ArrayList<String> listFileName = new ArrayList<String>(); 
//        getAllFileName("D:\\CLPS\\Work\\FU\\系统",listFileName);
//        for(String name:listFileName)
//        {
//            System.out.println(name);
//        }
         
    }
}