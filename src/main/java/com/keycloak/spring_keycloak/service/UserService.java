package com.keycloak.spring_keycloak.service;

import com.keycloak.spring_keycloak.model.User;
import jakarta.ws.rs.core.Response;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    // register user
    Response createUser(User user);

    // verify email
    void sendVerificationEmail(String userId);

    // delete user
    void deleteUser(String userId);

    // forgot password  ( we take username as args because user-email acts as username
    void forgotPassword(String username);


}
