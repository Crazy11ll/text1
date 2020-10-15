package org.example.tool;

/**
 * 字符串工具类
 */
public class StringTool {
    public static  boolean isEmpty(String str){
        if(str == null|| "".equals(str.trim())) return  true;
        return false;
    }
}
