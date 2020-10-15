package org.example.bean;

import org.springframework.stereotype.Component;

@Component
public class Music {
    private String SongName;
    private String Atrtist;
    private String Album;
    private String length;
    private String day;

    public Music() {

    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getSongName() {
        return SongName;
    }

    public void setSongName(String songName) {
        SongName = songName;
    }

    public String getAtrtist() {
        return Atrtist;
    }

    public void setAtrtist(String atrtist) {
        Atrtist = atrtist;
    }

    public String getAlbum() {
        return Album;
    }

    public void setAlbum(String album) {
        Album = album;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }
}