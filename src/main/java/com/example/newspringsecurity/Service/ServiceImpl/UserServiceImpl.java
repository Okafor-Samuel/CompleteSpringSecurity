package com.example.newspringsecurity.Service.ServiceImpl;

import com.example.newspringsecurity.Exception.UserAlreadyExistsException;
import com.example.newspringsecurity.Model.User;
import com.example.newspringsecurity.Repository.UserRepository;
import com.example.newspringsecurity.Resgistration.RegistrationRequest;
import com.example.newspringsecurity.Service.ServiceInterface.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User registerUser(RegistrationRequest registrationRequest) {
        Optional<User> user = userRepository.findByEmail(registrationRequest.email());
        if(user.isPresent()){
            throw new UserAlreadyExistsException("User with "+ registrationRequest.email()+" already exists");
        }
        var newUser = new User();
        newUser.setFirstName(registrationRequest.firstName());
        newUser.setLastName(registrationRequest.lastName());
        newUser.setEmail(registrationRequest.email());
        newUser.setPassword(passwordEncoder.encode(registrationRequest.password()));
        newUser.setRole(registrationRequest.role());
        return userRepository.save(newUser);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
