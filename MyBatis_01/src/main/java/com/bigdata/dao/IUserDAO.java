package com.bigdata.dao;

import com.bigdata.domain.User;
import org.apache.ibatis.annotations.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 * @date 2018/9/28 13:54
 * @description
 */
public interface IUserDAO {
    @Insert("insert into t_user(id,name,salary) values(null,#{name},#{salary});" )
    @SelectKey(
            statement="select last_insert_id()",
            before=false,
            keyColumn="id",
            resultType=long.class,
            keyProperty="id")
    void save(User user);
    @Delete("  delete from  t_user where id=#{id}")
    void delete(Long id);
    @Update("update t_user set name=#{name} ,salary=#{salary}  where id=#{id}")
    void update(User user);
    @Select("   select id, name,salary from t_user where id=#{id}")
     User selectone(Long id);
    @Select("select id, name,salary from t_user ")
     List<User>   getAll();
    List<User>   findByUser(User user);
    List<User>  findById(List<Long> ids);
    List<User>  findByIdArry(long...id);
   // List<User>  findByNameAndSalary(Map<String,Object> map);
    List<User>  findByNameAndSalary(@Param("name") String name, @Param("salary") BigDecimal salary);
}
