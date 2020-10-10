package com.etc.dao;

import com.etc.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserDao {
    List<User> findAllUser(User user);
    User seletUserByID(int id);
    void insertUser(User user);
    void updateUser(User user);
    @Delete("delete from userinfo where id = #{uid}")
    void delUserByID(int id);

    @Select("select * from userinfo where user_name = #{name} and user_pwd = #{password}")
    @Results({
            @Result(property = "uid",  column = "id"),
            @Result(property = "name",  column = "user_name"),
            @Result(property = "password",  column = "user_pwd")
    })
    User loginQuery(@Param("name") String name,@Param("password") String password);
}
