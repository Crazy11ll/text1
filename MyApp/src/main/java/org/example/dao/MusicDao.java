package org.example.dao;

import org.example.bean.Music;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.audio.flac.FlacFileReader;
import org.jaudiotagger.audio.mp3.MP3AudioHeader;
import org.jaudiotagger.audio.mp3.MP3File;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;
import org.jaudiotagger.tag.datatype.Artwork;
import org.jaudiotagger.tag.id3.AbstractID3v2Frame;
import org.jaudiotagger.tag.id3.AbstractID3v2Tag;
import org.jaudiotagger.tag.id3.framebody.FrameBodyAPIC;
import org.springframework.stereotype.Repository;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Repository
public class MusicDao {

        /**
         * 获取MP3文件信息
         * @param path
         * @return
         * @throws ReadOnlyFileException
         * @throws TagException
         * @throws InvalidAudioFrameException
         * @throws IOException
         */
    public static Music Mp3Read(String path) throws ReadOnlyFileException, TagException, InvalidAudioFrameException, IOException {
        MP3File file;
        file = new MP3File(path);
        String songname = file.getID3v2Tag().frameMap.get("TIT2").toString();
        String artist = file.getID3v2Tag().frameMap.get("TPE1").toString();
        String album = file.getID3v2Tag().frameMap.get("TALB").toString();
        String length = file.getMP3AudioHeader().getTrackLengthAsString();
        songname = songname.substring(6,songname.length()-3);
        artist = artist.substring(6,artist.length()-3);
        album = album.substring(6,album.length()-3);
        Music music = new Music();
        music.setSongName(songname);
        music.setAlbum(album);
        music.setAtrtist(artist);
        music.setLength(length);
        return music;
    }

    /**
     * 获取MP3图片
     * @param path
     * @return
     * @throws ReadOnlyFileException
     * @throws TagException
     * @throws InvalidAudioFrameException
     * @throws IOException
     */
    public static Image getMp3Picture(String path) throws ReadOnlyFileException, TagException, InvalidAudioFrameException, IOException {
        Image image;
        String url = path;
        File sourceFile = new File(url);
        MP3File mp3File = new MP3File(sourceFile);
        AbstractID3v2Tag tag = mp3File.getID3v2Tag();
        AbstractID3v2Frame frame = (AbstractID3v2Frame) tag.getFrame("APIC");
        FrameBodyAPIC body = (FrameBodyAPIC) frame.getBody();
        byte[] imageData = body.getImageData();
        image = Toolkit.getDefaultToolkit().createImage(imageData,0,imageData.length);
        ImageIcon icon = new ImageIcon(image);
        String spath = path;
        spath = spath.substring(0,spath.length()-3);
        spath+="jpg";
        FileOutputStream fos = new FileOutputStream(spath);
        fos.write(imageData);
        fos.close();
        return image;
    }
    /**
     * 获取Flac文件信息
     * @param path
     * @return
     * @throws TagException
     * @throws ReadOnlyFileException
     * @throws CannotReadException
     * @throws InvalidAudioFrameException
     * @throws IOException
     */
    public static Music FlacRead(String path) throws TagException, ReadOnlyFileException, CannotReadException, InvalidAudioFrameException, IOException {
        FlacFileReader fileReader = new FlacFileReader();
        AudioFile read = fileReader.read(new File(path));
        Tag tag = read.getTag();
        String songname = tag.getFirst(FieldKey.TITLE);
        String artist = tag.getFirst(FieldKey.ARTIST);
        String Album = tag.getFirst(FieldKey.ALBUM);
        int tracklength = read.getAudioHeader().getTrackLength();
        int min = tracklength/60;
        int second = tracklength%60;
        String length = min+":"+second;
        Music music1 = new Music();
        music1.setLength(length);
        music1.setAtrtist(artist);
        music1.setSongName(songname);
        music1.setAlbum(Album);
        return music1;
    }

    /**
     * 获取Flac文件的图片
     * @param path
     * @return
     * @throws TagException
     * @throws ReadOnlyFileException
     * @throws CannotReadException
     * @throws InvalidAudioFrameException
     * @throws IOException
     */
    public static Image GetFlacPicture(String path) throws TagException, ReadOnlyFileException, CannotReadException, InvalidAudioFrameException, IOException {
        FlacFileReader fileReader = new FlacFileReader();
        AudioFile read = fileReader.read(new File(path));
        Tag tag = read.getTag();
        Artwork first = tag.getFirstArtwork();
        byte[] imagedata = first.getBinaryData();
        Image image = Toolkit.getDefaultToolkit().createImage(imagedata,0,imagedata.length);
        ImageIcon icon = new ImageIcon(image);
        String spath = path;
        spath = spath.substring(0,spath.length()-4);
        spath+="jpg";
        System.out.println(spath);
        FileOutputStream fos = new FileOutputStream(spath);
        fos.write(imagedata);
        fos.close();
        return image;
    }

    /**
     * 获取MP3文件信息
     * @param filePath
     * @return
     */
    public static Music GetMP3Info(String filePath) {

        Music music = null;

        try {
            MP3File mp3File = (MP3File) AudioFileIO.read(new File(filePath));
            MP3AudioHeader audioHeader = (MP3AudioHeader) mp3File.getAudioHeader();
            String songname = mp3File.getID3v2Tag().frameMap.get("TIT2").toString();//歌名
            String artist = mp3File.getID3v2Tag().frameMap.get("TPE1").toString();//歌手
            String album = mp3File.getID3v2Tag().frameMap.get("TALB").toString();//专辑
            int duration = audioHeader.getTrackLength();//时长
            int min = duration/60;
            int second = duration%60;
            String length = min+":"+second;
            music = new Music();
            music.setAlbum(album);
            music.setSongName(songname);
            music.setAtrtist(artist);
            music.setLength(length);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return music;
    }
}