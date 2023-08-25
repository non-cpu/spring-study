package com.example.springstudy;

import com.example.springstudy.domain.User;
import com.example.springstudy.persistence.UserDAO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
public class UserDAOTest {
    @Autowired
    private UserDAO userDAO;

    User insertUser() {
        User user = new User();
        user.setUsername("test");
        user.setPassword("test");
        user.setEmail("test@gmail.com");

        return userDAO.insertUser(user);
    }

    @Test
    void insertUserTest() {
        User user = insertUser();

        User retrievedUser = userDAO.getUser("test");
        assertEquals("test", retrievedUser.getUsername());

        userDAO.deleteUser(user);
    }
    @Test
    void updateUserTest() {
        String updatedEmail = "updated@gmail.com";

        User user = insertUser();

        user.setEmail(updatedEmail);
        userDAO.updateUser(user);

        User updatedUser = userDAO.getUserByID(user.getId());
        assertEquals(updatedEmail, updatedUser.getEmail());

        userDAO.deleteUser(user);
    }
    @Test
    void deleteUserTest() {
        User user = insertUser();
        userDAO.deleteUser(user);

        User deletedUser = userDAO.getUserByID(user.getId());
        assertNull(deletedUser);
    }
    @Test
    void getUserTest() {
        User user = insertUser();

        User retrievedUser = userDAO.getUser(user.getUsername());
        assertEquals("test@gmail.com", retrievedUser.getEmail());

        userDAO.deleteUser(user);
    }
    @Test
    void getUserByIDTest() {
        User user = insertUser();

        User retrievedUser = userDAO.getUserByID(user.getId());
        assertEquals("test", retrievedUser.getUsername());

        userDAO.deleteUser(user);
    }
    @Test
    void getUserListTest(){
        int before = userDAO.getUserList().size();

        User user = insertUser();

        int after = userDAO.getUserList().size();

        assertEquals(before + 1, after);

        userDAO.deleteUser(user);
    }
}
