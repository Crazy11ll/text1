package org.example.Interface;

import org.example.service.FileService;
import org.example.tool.StringTool;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.TagException;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.io.IOException;
import java.util.Scanner;

@Component
public class WinDow1 {
    @Resource
    private FileService fileService;
    int filenum = 0;//文件的数量
    String[] suffix = new String[20];//文件的种类
    int typefilenum = 0;
    Scanner cin = new Scanner(System.in);
    String path = "";//目标文件的路径
    public WinDow1(){
    }

    /**
     * 主界面
     * @throws ReadOnlyFileException
     * @throws CannotReadException
     * @throws TagException
     * @throws InvalidAudioFrameException
     * @throws IOException
     */
    public void run() throws ReadOnlyFileException, CannotReadException, TagException, InvalidAudioFrameException, IOException {
        int choose=0;
        System.out.println("      ****** 欢迎使用本工具！*****");
        System.out.println("*****************************************");
        System.out.println("");
        System.out.println("请输入你想要分类的文件的路径↓");
        path = cin.next();
        fileService.init(path);
        filenum = fileService.GetFileNum();
        suffix = fileService.GetFileSuffix();
        typefilenum = StringTool.FloderNumber(path);
        System.out.println("此文件夹中的文件个数为："+filenum);
        System.out.println("文件的种类为："+typefilenum);
        System.out.println("*****************************************");
        System.out.println("1-----> 以文件的类型分类");
        System.out.println("2-----> 以其他方式分类");
        System.out.println("请输入↓");
        int n = cin.nextInt();
        switch (n){
            case 1: fileService.TypePart(); break;
            case 2: fileService.AttributePart(TypeCase()); break;
            default: break;
        }
        System.out.println("   ****分类完成****");
        System.out.println("*******谢谢使用*******");
    }

    /**
     * 辅助函数
     * @return
     */
    private int TypeCase(){
        int flag=0;
        System.out.println("可以根据一下的属性进行分类↓");
        System.out.println("1--->作者   2--->唱片集");
        System.out.println("3--->时长   4--->时间");
        int n = cin.nextInt();
        switch (n){
            case 1: flag=1; break;
            case 2: flag=2; break;
            case 3: flag=3; break;
            case 4: flag=4; break;
        }
        return flag;
    }
}
