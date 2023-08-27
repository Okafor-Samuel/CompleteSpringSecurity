package com.example.newspringsecurity.Event;

import com.example.newspringsecurity.Model.User;
import lombok.*;
import org.springframework.context.ApplicationEvent;

import java.time.Clock;

@Getter
@Setter

public class RegistrationCompleteEvent extends ApplicationEvent {
    private User user;
    private String applicationUrl;


    public RegistrationCompleteEvent(User user, String applicationUrl){
        super(user);
        this.user =user;
        this.applicationUrl = applicationUrl;
    }

}
