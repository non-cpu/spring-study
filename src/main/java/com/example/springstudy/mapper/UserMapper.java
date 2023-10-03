package com.example.springstudy.mapper;

import com.example.springstudy.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    void insertUser(User user);

    void updateUser(User user);

    void deleteUser(User user);

    User getUser(String username);

    User getUserByID(int id);

    List<User> getUserList();
}

//@Mapper
//public interface UserMapper {
//    @Insert("INSERT INTO USERS(ID, USERNAME, PASSWORD, EMAIL) " +
//            "VALUES((SELECT NVL(MAX(ID), 0) + 1 FROM USERS), #{username}, #{password}, #{email})")
//    @Options(useGeneratedKeys = true, keyProperty = "id")
//    void insertUser(User user);
//
//    @Update("UPDATE USERS SET PASSWORD = #{password}, EMAIL = #{email} WHERE ID = #{id}")
//    void updateUser(User user);
//
//    @Delete("DELETE FROM USERS WHERE ID = #{id}")
//    void deleteUser(User user);
//
//    @Select("SELECT * FROM USERS WHERE USERNAME = #{username} LIMIT 1")
//    User getUser(String username);
//
//    @Select("SELECT * FROM USERS WHERE ID = #{id}")
//    User getUserByID(int id);
//
//    @Select("SELECT * FROM USERS ORDER BY USERNAME DESC")
//    List<User> getUserList();
//}
