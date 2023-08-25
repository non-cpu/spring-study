package com.example.springstudy.controller;

import com.example.springstudy.domain.User;
import com.example.springstudy.dto.ResponseDTO;
import com.example.springstudy.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {
    private final UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/auth/login")
    public @ResponseBody ResponseDTO<?> login(@RequestBody User user, HttpSession session) {
        User findUser = userService.getUser(user.getUsername());

        if (findUser.getUsername() == null) {
            return new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(), "ID가 존재하지 않아요.");
        } else {
            if (user.getPassword().equals(findUser.getPassword())) {
                session.setAttribute("principal", findUser);
                return new ResponseDTO<>(HttpStatus.OK.value(),
                        findUser.getUsername() + " 님 로그인 성공.");
            } else {
                return new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(), "비밀번호 오류!");
            }
        }
    }

    @GetMapping("/auth/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
