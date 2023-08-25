package com.example.springstudy.persistence;

import com.example.springstudy.domain.User;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAO {
    private final SqlSessionTemplate mybatis;

    @Autowired
    public UserDAO(SqlSessionTemplate mybatis) {
        this.mybatis = mybatis;
    }

    public User insertUser(User user){
        mybatis.insert("insertUser", user);
        return user;
    }

    public void updateUser(User user){
        mybatis.update("updateUser", user);
    }

    public void deleteUser(User user){
        mybatis.delete("deleteUser", user);
    }

    public User getUser(String username){
        return mybatis.selectOne("getUser", username);
    }

    public User getUserByID(int id) {
        return mybatis.selectOne("getUserByID", id);
    }

    public List<User> getUserList(){
        return mybatis.selectList("getUserList");
    }
}
