package com.example.newspringsecurity.Service.ServiceInterface;

import com.example.newspringsecurity.Model.User;
import com.example.newspringsecurity.Resgistration.RegistrationRequest;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getUsers();
    User registerUser(RegistrationRequest registrationRequest);
    Optional<User> findByEmail(String email);

}
