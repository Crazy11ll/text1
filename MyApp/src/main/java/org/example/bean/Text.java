package org.example.bean;

import org.springframework.stereotype.Component;

@Component
public class Text {
    String filename;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
