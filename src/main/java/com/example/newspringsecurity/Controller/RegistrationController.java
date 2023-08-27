package com.example.newspringsecurity.Controller;

import com.example.newspringsecurity.Model.User;
import com.example.newspringsecurity.Resgistration.RegistrationRequest;
import com.example.newspringsecurity.Service.ServiceImpl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/register")
public class RegistrationController {
    private final UserServiceImpl userServiceImpl;

    @PostMapping("/signup")
    public String registerUser(RegistrationRequest registrationRequest){
        User user = userServiceImpl.registerUser(registrationRequest);
        // Publish registration event

        return "Success! please, check your email to complete your registration";
    }
}
