package com.example.springstudy.controller;

import com.example.springstudy.domain.User;
import org.springframework.web.bind.annotation.*;

@RestController
public class RESTController {
    @GetMapping("/rest")
    public User httpGet(){

        return User.builder()
                .id(1)
                .username("name")
                .password("pass")
                .email("name@gmail.com")
                .build();
    }

//    @PostMapping("/rest")
//    public String httpPost(User user){
//        return "POST 요청 처리 입력값: " + user.toString();
//    }

    @PostMapping("/rest")
    public String httpPost(@RequestBody User user){
        return "POST 요청 처리 입력값: " + user.toString();
    }

    @PutMapping("/rest")
    public String httpPut(@RequestBody User user){
        return "PUT 요청 처리 입력값: " + user.toString();
    }

    @DeleteMapping("/rest")
    public String httpDelete(@RequestParam int id){
        return "DELETE 요청 처리 입력값: " + id;
    }
}
