package com.example.juejinafter.utils;

import java.io.*;

public class JsonUtil {
    public  static String readJsonFile(String filePath){
        String jsonStr = "";
        try{
            File jsonFile = new File(filePath);
            Reader reader = new InputStreamReader(new FileInputStream(jsonFile),"utf-8");
            int ch =0;
            StringBuilder sb = new StringBuilder();
            while((ch = reader.read()) != -1){
                sb.append((char)ch);
            }
            reader.close();
            jsonStr = sb.toString();
            return  jsonStr;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

}
