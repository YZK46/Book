package com.yzk46.book.util;

import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @program: book
 * @description: 对数据进行处理
 * @author: yzk46
 * @create: 2021-03-16 22:14
 **/
public class DataProcessUtil {

    @Test
    public void stringProcess(){
        File file = new File("C:/Users/YZK46/Desktop/毕设/data/book.txt");
        String line = "";
        List<String> lineList = new ArrayList<>();
        try {
            BufferedReader bf = new BufferedReader(new FileReader(file));
            while((line = bf.readLine()) != null){
                line = line.replace("↵","");
                System.out.println(line);
                lineList.add(line);
            }
            //写入的是同一个文件的话，bufferedWriter需要在读取后再创建
            BufferedWriter out = new BufferedWriter(new FileWriter(file));
            System.out.println(lineList.size());
            lineList.forEach(item -> {
                try {
                    System.out.println(item);
                    out.write(item);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            out.flush();
            out.close();
            bf.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testMatch(){
        String line = "<h4 class=\"title\"> 美国不平等的起源 </h4>";
        String request = "</h4>";
        int result = getStringIndex(line,request,false);
        String respone = line.substring(18,28);
        System.out.println(respone);
    }
    //获取某一字符串在目标字符串中的坐标。
    public Integer getStringIndex(String line,String request,boolean choice){
        Integer result = 0;
        char[] a = line.toCharArray();
        char[] b = request.toCharArray();
        //获取请求字符串的长度
        int length = request.length();
        //头指针
        int j = 0;
        int i = 0;
        int n = 0;
        while(n + length <= line.length()){
            i = n;
            j = 0;
            int k = j + length - 1;
            int l = i + length - 1;
            if(a[i] == b[j] && a[l] == b[k]){
                while(i < k){
                    i++;
                    j++;
                    if(a[i] == b[j]){
                        l--;
                        k--;
                        if(a[l] == b[k]){

                        } else {
                            continue;
                        }
                    } else {
                        continue;
                    }
                }
                if(i >= k){
                    if(choice){
                        result = n + length;
                    } else {
                        result = n;
                    }
                    return result;
                }
            }
            n++;
        }
        return result;
    }
}
