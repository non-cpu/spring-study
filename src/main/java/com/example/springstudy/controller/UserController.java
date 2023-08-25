package com.example.springstudy.controller;

import com.example.springstudy.domain.RoleType;
import com.example.springstudy.domain.User;
import com.example.springstudy.dto.ResponseDTO;
import com.example.springstudy.exception.StudyException;
import com.example.springstudy.persistence.UserRepository;
import com.example.springstudy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Supplier;

@Controller
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @PostMapping("/user")
    public @ResponseBody String insertUser(@RequestBody User user) {
        user.setRole(RoleType.USER);
        userRepository.save(user);
        return user.getUsername() + " 회원가입 성공";
    }

    @GetMapping("/user/get/{id}")
    public @ResponseBody User getUser(@PathVariable int id) {
        return userRepository.findById(id).orElseThrow(new Supplier<StudyException>() {
            @Override
            public StudyException get() {
                return new StudyException(id + "번 회원이 없습니다.");
            }
        });
    }

    @Transactional
    @PutMapping("/user")
    public @ResponseBody String updateUser(@RequestBody User user) {
        User findUser = userRepository.findById(user.getId()).orElseThrow(() -> new StudyException(user.getId() + "번 회원이 없습니다."));
        findUser.setUsername(user.getUsername());
        findUser.setPassword(user.getPassword());
        findUser.setEmail(user.getEmail());

//        userRepository.save(findUser); // <-> @Transactional
        return "회원 수정 성공";
    }

    @DeleteMapping("/user/{id}")
    public @ResponseBody String deleteUser(@PathVariable int id) {
        userRepository.deleteById(id);
        return "회원 삭제 성공";
    }

    @GetMapping("/user/list")
    public @ResponseBody List<User> getUserList() {
        return userRepository.findAll();
    }

    @GetMapping("/user/page") // ?page=1&size=1
    public @ResponseBody Page<User> getUserListPaging(
            @PageableDefault(size = 2, direction = Sort.Direction.DESC, sort = {"id", "username"}) Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @GetMapping({"", "/"})
    public String getPostList() {
        return "index";
    }

    @GetMapping("/auth/insertUser")
    public String insertUser() {
        return "user/insertUser";
    }

    @PostMapping("/auth/insertUser")
    public @ResponseBody ResponseDTO<?> authInsertUser(@RequestBody User user) {
        User findUser = userService.getUser(user.getUsername());

        if (findUser.getUsername() == null) {
            userService.insertUser(user);
            return new ResponseDTO<>(HttpStatus.OK.value(), user.getUsername() + " 가입 성공.");
        } else {
            return new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(), user.getUsername() + " 님은 이미 회원입니다.");
        }
    }
}
