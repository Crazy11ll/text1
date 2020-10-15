package org.example.spring;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.XPath;
import org.dom4j.io.SAXReader;

import java.awt.*;
import java.lang.reflect.Constructor;
import java.net.URL;
import java.util.*;
import java.util.List;

public class MyclassPathXmlApplicationContext implements MyFactory{

    private List<Mybean> beanlist; //存放从配置文件中获取到的标签的信息
    private Map<String,Object> beanMap = new HashMap<>();//存放实例化好的对象  //存放实例化好的对象，通过id获取对应的对象
    public MyclassPathXmlApplicationContext(String filename) {
        //通过dom4j解析配置文件，得到list集合
        this.parseXml(filename);
        //获取实例化对象
        this.instanceBean();

    }

    private void parseXml(String filename)  {
        //获取解析器
        SAXReader saxReader = new SAXReader();
        //获取配置文件的url
        URL url = this.getClass().getClassLoader().getResource(filename);
        try{
            Document document = saxReader.read(url);
            //通过xpath语法解析，获取beans标签下所有的bean标签
            XPath xPath = document.createXPath("beans/bean");
            //通过指定的解析语法解析文档对象，返回元素集合
            List<Element> elementList = xPath.selectNodes(document);
            //判断元素集合是否为空
            if(elementList != null && elementList.size()>0){
                //实例化beanlist
                beanlist = new ArrayList<>();
                for (Element el: elementList){
                    //获取bean标签元素的属性值
                    String id = el.attributeValue("id"); //id属性值
                    String clazz = el.attributeValue("class"); //class属性值
                    //获取mybean对象，将id和calss属性值设置到对象中，再将对象设置到Mybean对象中
                    Mybean mybean = new Mybean(id,clazz);
                    beanlist.add(mybean);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    private void instanceBean() {
        if (beanlist !=null &&beanlist.size()>0){
            for(Mybean bean: beanlist){
                String id = bean.getId();
                String clazz = bean.getClazz();
                try{
                    //通过类的全路径名 反射 得到实例化对象
                    Class c = Class.forName(clazz);
                    Constructor z = c.getDeclaredConstructor();
                    Object object = z.newInstance();
                    //  Object obj = Class.forName(clazz).getDeclaredConstructor().newInstance();
                    beanMap.put(id,object);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
    @Override
    public Object getBean(String id) {
        Object object = beanMap.get(id);
        return object;
    }
}
