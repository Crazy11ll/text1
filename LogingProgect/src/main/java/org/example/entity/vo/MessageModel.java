package org.example.entity.vo;

public class MessageModel {
    private int resultcode = 1;
    private String resultmessage;

    public int getResultcode() {
        return resultcode;
    }

    public void setResultcode(int resultcode) {
        this.resultcode = resultcode;
    }

    public String getResultmessage() {
        return resultmessage;
    }

    public void setResultmessage(String resultmessage) {
        this.resultmessage = resultmessage;
    }
}
