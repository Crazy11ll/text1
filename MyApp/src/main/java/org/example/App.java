package org.example;

import org.example.Interface.WinDow1;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.TagException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.awt.*;
import java.io.IOException;

public class App 
{
    public static void main(String[] args) throws TagException, ReadOnlyFileException, CannotReadException, InvalidAudioFrameException, IOException {
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring.xml");
        WinDow1 winDow1 = (WinDow1) ac.getBean("winDow1");
        winDow1.run();
    }
}



