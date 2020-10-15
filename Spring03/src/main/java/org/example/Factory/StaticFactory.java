package org.example.Factory;

import org.example.dao.TypeDao;

//静态工厂实例化
public class StaticFactory {
    public static TypeDao createTypeDao(){
        return new TypeDao();
    }
}
