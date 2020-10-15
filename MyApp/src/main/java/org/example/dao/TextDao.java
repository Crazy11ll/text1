package org.example.dao;

import org.example.bean.Text;
import org.springframework.stereotype.Repository;

import java.awt.*;
import java.io.File;

@Repository
public class TextDao {
    public Text GetTxetFile(String path){
        File file = new File(path);
        Text text = new Text();
        text.setFilename(file.getName());
        return text;
    }
}
