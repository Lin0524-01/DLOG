package com.moon.dlog.utils;

import java.io.*;

/**
 * @version 1.0
 * @Description
 * @Author linyl
 * @Date 2023-01-28 22:05
 **/
public class TestRunPython {
    public static void main(String[] args) {
        try {
            Process process = Runtime.getRuntime().exec("python D:\\MyWorkSpace\\Python\\teamData.py");
            InputStream inputStream = process.getInputStream();
            // 转换流解决中文乱码问题
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "gbk"));
//            Stream<String> lines = bufferedReader.lines();
//            lines.forEach(System.out::println);
            String line;
            while ((line = bufferedReader.readLine()) != null){
                String[] spiltLine = line.split("    ");
                for (String s : spiltLine) {
                    System.out.println(s);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
