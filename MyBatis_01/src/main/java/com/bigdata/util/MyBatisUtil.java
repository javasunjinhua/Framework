package com.bigdata.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Administrator
 * @date 2018/9/27 16:14
 * @description
 */
public class MyBatisUtil {
    private static SqlSessionFactory sqlSessionFactory = null;

    static {
        try {
            //1:加载mybatis的主配置文件
            InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
            //2:创建SqlSessionFactroy对象(相当于连接池)
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("加载配置文件失败");
        }
    }


    public static SqlSession getSession() {
        try {
            //3:获取一个SqlSession对象(相当于Connection连接对象)
            SqlSession sqlSession = sqlSessionFactory.openSession();
            return sqlSession;
        } catch (Exception e) {
            e.printStackTrace();
            //把编译时异常转换为运行时异常,让别人调用的时候更爽!
            throw new RuntimeException("获取seesion失败");
        }
    }
}
