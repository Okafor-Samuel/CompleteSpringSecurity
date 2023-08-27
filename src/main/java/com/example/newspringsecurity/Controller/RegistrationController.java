package com.example.newspringsecurity.Controller;

import com.example.newspringsecurity.Event.RegistrationCompleteEvent;
import com.example.newspringsecurity.Model.User;
import com.example.newspringsecurity.Resgistration.RegistrationRequest;
import com.example.newspringsecurity.Service.ServiceImpl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/register")
public class RegistrationController {
    private final UserServiceImpl userServiceImpl;
    private final ApplicationEventPublisher publisher;

    @PostMapping("/signup")
    public String registerUser(RegistrationRequest registrationRequest, final HttpServletRequest request){
        User user = userServiceImpl.registerUser(registrationRequest);
        // Publish registration event
        publisher.publishEvent(new RegistrationCompleteEvent(user, applicationUrl(request)));

        return "Success! please, check your email to complete your registration";
    }

    public String applicationUrl(HttpServletRequest request) {
        return "http://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
    }
}
