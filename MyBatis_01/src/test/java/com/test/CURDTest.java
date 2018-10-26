package com.test;

import com.bigdata.dao.IUserDAO;
import com.bigdata.domain.User;
import com.bigdata.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 * @date 2018/9/27 16:17
 * @description
 */
public class CURDTest {
    private IUserDAO  dao;
   private  SqlSession sqlSession;
 @Before
   public void init(){
       sqlSession= MyBatisUtil.getSession();
        dao= sqlSession.getMapper(IUserDAO.class);
   }



    @Test
    public void testInsert() throws Exception {
        User user = new User(null, "JACK7", new BigDecimal(8888));
         dao.save(user);


    }


    @Test
    public void testDelete() throws Exception {
        dao.delete(3l);


    }

    @Test
    public void testUpdate() throws Exception {
        User user = new User(6l,"jery", new BigDecimal(6666));
        dao.update( user);


    }

    //1．查询单条数据
    @Test
    public void testSelectOne() throws Exception {
        User user1= dao.selectone(4l);
        sqlSession.commit();
        User user2= dao.selectone(4l);
        System.out.println(user1==user2);

    }

    //2．查询多条数据
    @Test
    public void testAll() throws Exception {
        List<User> list = dao.getAll();
        list.forEach(System.out::println);


    }
////3．查询总记录数
//@Test
//public void testgetAll() throws Exception {
//    SqlSession session = MyBatisUtil.getSession();
//   Long count= session.selectOne();
//    System.out.println(count);
//
//
//
//}
//
////4．查询结果封装到Map中
//
//    @Test
//    public void testgetMap() throws Exception {
//        SqlSession session = MyBatisUtil.getSession();
//        List<Map> list = session.selectList(this.getStatement("getMap"));
//        list.forEach(System.out::println);
//
//
//    }
//
////5．模糊查询
//
//    @Test
//    public void testfindByName() throws Exception {
//        SqlSession session = MyBatisUtil.getSession();
//       List list= session.selectList(this.getStatement("findByName"),"%k%");
//        list.forEach(System.out::println);
//
//
//    }

    @Test
    public void testfindByUser() throws Exception {
        User user = new User(null, null, null);
        List<User> list = dao.findByUser(user);
     list.forEach(System.out::println);

    }
    @Test
    public void testfindById() throws Exception {

        List<User> list = dao.findById(Arrays.asList(5l,6l,7l));
        list.forEach(System.out::println);

    }

    @Test
    public void testfindByIdArry() throws Exception {

        List<User> list = dao. findByIdArry(5l,6l,7l);
        list.forEach(System.out::println);

    }

    @Test
    public void testfindByNameAndSalary() throws Exception {
  //Map<String,Object> map=new HashMap();
  //map.put("jack1",new BigDecimal(6666));
  //      List<User> list = dao.findByNameAndSalary(map);
  //      list.forEach(System.out::println);
        List<User> list = dao.findByNameAndSalary("jack3", new BigDecimal(8888));
        list.forEach(System.out::println);
    }

    @Test
    public void testcache() throws Exception {
        SqlSession sqlSession1 = MyBatisUtil.getSession();
        IUserDAO dao1 = sqlSession1.getMapper(IUserDAO.class);
        User user1 = dao1.selectone(4L);
        sqlSession1.close();
        SqlSession sqlSession2 = MyBatisUtil.getSession();
        IUserDAO dao2 = sqlSession2.getMapper(IUserDAO.class);
        User user2 = dao2.selectone(4L);
        System.out.println(user1 == user2);
    }

    @After
  public void destory(){
       sqlSession.commit();
       sqlSession.close();

    }





}
