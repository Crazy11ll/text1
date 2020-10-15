package org.example.bean;

import org.springframework.stereotype.Component;

@Component
public class PartFile {
    private String path;
    private int foldernum;
    private int filenum;
    private int typenum;
    private String[] type = new String[20];

    public PartFile() {
    }

    public String[] getType() {
        return type;
    }

    public void setType(String[] type) {
        this.type = type;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getFoldernum() {
        return foldernum;
    }

    public void setFoldernum(int foldernum) {
        this.foldernum = foldernum;
    }

    public int getFilenum() {
        return filenum;
    }

    public void setFilenum(int filenum) {
        this.filenum = filenum;
    }

    public int getTypenum() {
        return typenum;
    }

    public void setTypenum(int typenum) {
        this.typenum = typenum;
    }
}
