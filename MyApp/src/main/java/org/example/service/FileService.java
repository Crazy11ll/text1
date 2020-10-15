package org.example.service;


import org.example.bean.Music;
import org.example.bean.PartFile;
import org.example.dao.MusicDao;
import org.example.tool.StringTool;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.TagException;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class FileService {
    @Resource
    private PartFile partFile;
//    @Resource
//    private MusicDao musicDao;
    @Resource
    private Music music;
    private String[] artist = new String[100];
    private String[] alum = new String[100];
    private String[] day = new String[100];
    private String[] length = new String[100];

    public FileService(){

    }

    /**
     * 文件类初始化
     * @param path
     */
    public void init(String path){
        partFile.setPath(path);
        partFile.setFilenum(GetFileNum());
        partFile.setType(StringTool.Different(path));
        partFile.setFoldernum(StringTool.FloderNumber(path));
    }

    /**
     * 获取文件的数量
     * @return
     */
    public int GetFileNum(){
        int filenum=0;int flag=0;
        List<File> files = recurseDirs(new File(partFile.getPath()),2);
        for(File i:files){
           // System.out.println(i);
            flag++;
            filenum++;
        }
       // System.out.println(flag);
        return filenum;
    }

    /**
     * 获取文件的类型的个数
     * @return
     */
    public String[] GetFileSuffix(){
        String[] suffix = new String[20];
        suffix = StringTool.Different(partFile.getPath());
        return suffix;
    }

    /**
     * 深度优先遍历文件+限制深度
     * @param start
     * @param count
     * @return
     */
    private List<File> recurseDirs(File start, int count){
        List<File> result = new ArrayList<File>();
        if(count==0){
            return result;
        }
        if(start.listFiles()==null){
            System.out.println("这不是一个有效的目录");
            return result;
        }
        for(File file:start.listFiles()){
            if(file.isDirectory()){
                result.add(file);
                result.addAll(recurseDirs(file,count-1));
            }
            else{
                result.add(file);
            }
        }
        return result;
    }

    public void CopyFile(String start,String end){
        copy(start, end);
    }

    /**
     * 创建文件夹
     * @param str
     * @param path
     */
    public void CreateFilefolder(String[] str,String path){
        int num = 0;
        while(str[num]!=null){
            String s=str[num].substring(0);
            File file = new File(path+"\\"+s);
            if(!file.exists()) file.mkdir();
            num++;
        }
    }

    /**
     * 创建文件夹
     * @param str
     * @param path
     */
    public void CreateFilefolder1(String[] str,String path){
        int num = 0;
        while(str[num]!=null){
            String s=str[num].substring(1);
            File file = new File(path+"\\"+s);
            if(!file.exists()) file.mkdir();
            num++;
        }
    }

    /**
     * 复制文件
     * @param srcPathStr
     * @param desPathStr
     */
    private void copy(String srcPathStr, String desPathStr)
    {
        //获取源文件的名称
        String newFileName = srcPathStr.substring(srcPathStr.lastIndexOf("\\")+1); //目标文件地址
        //System.out.println("源文件:"+newFileName);
        desPathStr = desPathStr+ File.separator + newFileName; //源文件地址
        //System.out.println("目标文件地址:"+desPathStr);
        try
        {
            FileInputStream fis = new FileInputStream(srcPathStr);//创建输入流对象
            FileOutputStream fos = new FileOutputStream(desPathStr); //创建输出流对象
            byte datas[] = new byte[1024*8];//创建搬运工具
            int len = 0;//创建长度
            while((len = fis.read(datas))!=-1)//循环读取数据
            {
                fos.write(datas,0,len);
            }
            fis.close();//释放资源
            fis.close();//释放资源
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 通过文件的类型分类
     */
    public void TypePart(){
       String path=partFile.getPath();
       String[] type = partFile.getType();
       this.CreateFilefolder1(type,path);
       List<File> list = recurseDirs(new File(partFile.getPath()),2);
       for(File i:list){
           String s;
           if(i.isFile()){
               s=StringTool.FileType(i);
              // System.out.println(s);
               copy(i.getPath(),path+"\\"+s);
               try{
                   StringTool.deleteFile(i.getPath());
               }catch (Exception e){
                   System.out.println(i.getPath()+"文件删除失败");
                   e.printStackTrace();
               }
           }
           else continue;
        }
    }

    /**
     * 通过文件的属性分类
     */
    public void AttributePart(int flag) throws ReadOnlyFileException, IOException, TagException, InvalidAudioFrameException, CannotReadException {
        List<File> list = recurseDirs(new File(partFile.getPath()),2);
        for(File i:list){
            if(i.isFile()){
                String s=StringTool.FileType(i);
                if(s.equals("flac"))
                music = MusicDao.FlacRead(i.getPath());
                else music = MusicDao.GetMP3Info(i.getPath());
                if(music.getSongName().equals("")) continue;
                if(flag==1) ArtistSplit(i);
                else if(flag==2) AlumSplit(i);
                else if(flag==3) LengthSplit(i);
                else if(flag==4) DaySplit(i);
            }
            else continue;
        }

    }

    /**
     * 通过作者属性分类
     * @param file
     */
    private void ArtistSplit(File file){
        if(StringTool.Lookfor(artist,music.getAtrtist())){
            copy(file.getPath(),partFile.getPath()+"\\"+music.getAtrtist());
            try{
                StringTool.deleteFile(file.getPath());
            }catch (Exception e){
                System.out.println(file.getPath()+"文件删除失败");
                e.printStackTrace();
            }
        }
        else{
            artist = StringTool.Add(artist,music.getAtrtist());
            CreateFilefolder(artist,partFile.getPath());
            copy(file.getPath(),partFile.getPath()+"\\"+music.getAtrtist());
            try{
                StringTool.deleteFile(file.getPath());
            }catch (Exception e){
                System.out.println(file.getPath()+"文件删除失败");
                e.printStackTrace();
            }
        }
    }

    /**
     * 通过专辑分类
     * @param file
     */
    private void AlumSplit(File file){
        if(StringTool.Lookfor(alum,music.getAtrtist())){
            copy(file.getPath(),partFile.getPath()+"\\"+music.getAlbum());
            try{
                StringTool.deleteFile(file.getPath());
            }catch (Exception e){
                System.out.println(file.getPath()+"文件删除失败");
                e.printStackTrace();
            }
        }
        else{
            alum = StringTool.Add(alum,music.getAlbum());
            CreateFilefolder(alum,partFile.getPath());
            copy(file.getPath(),partFile.getPath()+"\\"+music.getAlbum());
            try{
                StringTool.deleteFile(file.getPath());
            }catch (Exception e){
                System.out.println(file.getPath()+"文件删除失败");
                e.printStackTrace();
            }
        }
    }

    /**
     * 通过时长分类
     * @param file
     */
    private void LengthSplit(File file) {
        if(StringTool.Lookfor(length,music.getAtrtist())){
            copy(file.getPath(),partFile.getPath()+"\\"+music.getLength());
            try{
                StringTool.deleteFile(file.getPath());
            }catch (Exception e){
                System.out.println(file.getPath()+"文件删除失败");
                e.printStackTrace();
            }
        }
        else{
            length = StringTool.Add(length,music.getLength());
            CreateFilefolder(length,partFile.getPath());
            copy(file.getPath(),partFile.getPath()+"\\"+music.getLength());
            try{
                StringTool.deleteFile(file.getPath());
            }catch (Exception e){
                System.out.println(file.getPath()+"文件删除失败");
                e.printStackTrace();
            }
        }
    }

    /**
     * 通过日期分类
     * @param file
     */
    private void DaySplit(File file){
        if(StringTool.Lookfor(day,music.getAtrtist())){
            copy(file.getPath(),partFile.getPath()+"\\"+music.getDay());
            try{
                StringTool.deleteFile(file.getPath());
            }catch (Exception e){
                System.out.println(file.getPath()+"文件删除失败");
                e.printStackTrace();
            }
        }
        else{
            day = StringTool.Add(day,music.getDay());
            CreateFilefolder(day,partFile.getPath());
            copy(file.getPath(),partFile.getPath()+"\\"+music.getDay());
            try{
                StringTool.deleteFile(file.getPath());
            }catch (Exception e){
                System.out.println(file.getPath()+"文件删除失败");
                e.printStackTrace();
            }
        }
    }
}
