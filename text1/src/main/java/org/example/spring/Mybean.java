package org.example.spring;

public class Mybean {
    private String id; //bean标签的id属性值
    private String clazz; //bean标签的class属性值

    public Mybean() {
    }

    public Mybean(String id, String clazz) {
        this.clazz = clazz;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getClazz() {
        return clazz;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }
}
