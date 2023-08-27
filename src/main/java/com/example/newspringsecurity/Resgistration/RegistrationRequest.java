package com.example.newspringsecurity.Resgistration;

import lombok.Data;
import org.hibernate.annotations.NaturalId;


public record RegistrationRequest(
        String firstName,
        String lastName,
        String email,
        String password,
        String role) {

}
