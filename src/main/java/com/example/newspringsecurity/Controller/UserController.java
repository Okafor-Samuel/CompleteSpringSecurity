package com.example.newspringsecurity.Controller;

import com.example.newspringsecurity.Model.User;
import com.example.newspringsecurity.Service.ServiceImpl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserServiceImpl userServiceImpl;

    @GetMapping("/get-all-users")
    public List<User> getUsers(){
        return userServiceImpl.getUsers();
    }
}
