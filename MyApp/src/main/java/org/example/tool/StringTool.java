package org.example.tool;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class StringTool {
    /**
     * 判断字符串是否为空
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        if (str == null || "".equals(str.trim())) return true;
        else return false;
    }

    /**
     * 当前文件夹下文件的数量
     *
     * @param path
     * @return
     */
    public static int FileNumber(String path) {
        File[] list = new File(path).listFiles();
        int number = 0;
        int floder = 0;
        for (File file : list) {
            if (file.isFile()) number++;
            else {
                floder++;
            }
        }
        return number;
    }

    /**
     * 当前目录下文件夹的数量
     *
     * @param path
     * @return
     */
    public static int FloderNumber(String path) {
        File[] list = new File(path).listFiles();
        int number = 0;
        int floder = 0;
        for (File file : list) {
            if (file.isFile()) number++;
            else {
                floder++;
            }
        }
        return floder;
    }

    /**
     * 判断文件的类型  (文件的后缀名)
     *
     * @param path
     * @return
     */
    public static String[] Different(String path) {
        File[] list = new File(path).listFiles();
        String[] name = new String[30];
        int num = 0;
        for (File file : list) {
            boolean flag = false;
            if (!file.isFile()) continue;
            String su = file.getName();
            int last = su.lastIndexOf(".");
            String suffix = su.substring(last);
            for (int i = 0; i < 20; i++) {
                if (suffix.equals(name[i])) flag = true;
            }
            if (flag) continue;
            else {
                name[num] = suffix;
                num++;
            }
        }
        return name;
    }

    /**
     * 计算文件中文件的数量
     *
     * @param start//开始位置
     * @param count//限制遍历深度
     * @return//一个文件链表
     */
    public static List<File> recurseDirs(File start, int count) {
        List<File> result = new ArrayList<File>();
        if (count == 0) {
            return result;
        }
        if (start.listFiles() == null) {
            //System.out.println("这不是一个有效的目录");
            return result;
        }
        for (File file : start.listFiles()) {
            if (file.isDirectory()) {
                result.add(file);
                result.addAll(recurseDirs(file, count - 1));
            } else {
                result.add(file);
            }
        }
        return result;
    }

    /**
     * 获取文件后缀名
     *
     * @param file
     * @return
     */
    public static String FileType(File file) {
        if (!file.isFile()) return "";

        String su = file.getName();
        int last = su.lastIndexOf(".");
        String suffix = su.substring(last + 1);
        return suffix;
    }

    /**
     * 删除单个文件
     *
     * @param fileName 被删除文件的文件名
     * @return 单个文件删除成功返回true, 否则返回false
     */
    public static boolean deleteFile(String fileName) {
        File file = new File(fileName);
        if (file.isFile() && file.exists()) {
            file.delete();
            //System.out.println("删除单个文件" + fileName + "成功！");
            return true;
        } else {
            //System.out.println("删除单个文件" + fileName + "失败！");
            return false;
        }
    }

    /**
     * 复制文件
     *
     * @param srcPathStr
     * @param desPathStr
     */
    public static void copy(String srcPathStr, String desPathStr) {
        //获取源文件的名称
        String newFileName = srcPathStr.substring(srcPathStr.lastIndexOf("\\") + 1); //目标文件地址
        //System.out.println("源文件:"+newFileName);
        desPathStr = desPathStr + File.separator + newFileName; //源文件地址
        //System.out.println("目标文件地址:"+desPathStr);
        try {
            FileInputStream fis = new FileInputStream(srcPathStr);//创建输入流对象
            FileOutputStream fos = new FileOutputStream(desPathStr); //创建输出流对象
            byte datas[] = new byte[1024 * 8];//创建搬运工具
            int len = 0;//创建长度
            while ((len = fis.read(datas)) != -1)//循环读取数据
            {
                fos.write(datas, 0, len);
            }
            fis.close();//释放资源
            fis.close();//释放资源
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 判断是否存在
     *
     * @param str
     * @param s
     * @return
     */
    public static boolean Lookfor(String[] str, String s) {
        boolean flag = false;
        if (str == null) return false;
        for (int n = 0; n < str.length; n++) {
            if (s == str[n]) flag = true;
        }

        return flag;
    }

    /**
     * 创建字符串
     * @param str
     * @return
     */
    public static String[] Add(String[] str,String s) {
        int flag=0;
        while(str[flag]!=null){
        flag++;
        }
        str[flag]=s;
        return str;
    }
}