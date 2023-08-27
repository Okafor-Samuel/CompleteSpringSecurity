package com.example.newspringsecurity.Controller;

import com.example.newspringsecurity.Event.RegistrationCompleteEvent;
import com.example.newspringsecurity.Model.User;
import com.example.newspringsecurity.Model.VerificationToken;
import com.example.newspringsecurity.Repository.VerificationTokenRepository;
import com.example.newspringsecurity.Resgistration.RegistrationRequest;
import com.example.newspringsecurity.Service.ServiceImpl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/register")
public class RegistrationController {
    private final UserServiceImpl userServiceImpl;
    private final ApplicationEventPublisher publisher;
    private final VerificationTokenRepository verificationTokenRepository;

    @PostMapping("/signup")
    public String registerUser(@RequestBody RegistrationRequest registrationRequest, final HttpServletRequest request){
        User user = userServiceImpl.registerUser(registrationRequest);
        // Publish registration event
        publisher.publishEvent(new RegistrationCompleteEvent(user, applicationUrl(request)));

        return "Success! please, check your email to complete your registration";
    }
    @GetMapping("/verifyEmail")
    public String verifyEmail(@RequestParam("token") String token){
        VerificationToken theToken = verificationTokenRepository.findByToken(token);
        if(theToken.getUser().isEnabled()){
            return "This account has already been verified, please login";
        }
        String verificationResult = userServiceImpl.validateToken(token);
        if(verificationResult.equalsIgnoreCase("Valid")){
            return "Email verified successfully, Please login";
        }return "Invalid verification token";
    }

    public String applicationUrl(HttpServletRequest request) {
        return "http://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
    }
}
