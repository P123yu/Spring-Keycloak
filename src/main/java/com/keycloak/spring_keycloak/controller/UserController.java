package com.keycloak.spring_keycloak.controller;

import com.keycloak.spring_keycloak.model.User;
import com.keycloak.spring_keycloak.service.UserService;
import jakarta.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<?>createUser(@RequestBody User user){
        Response response=userService.createUser(user);
        if(response.getStatus()==201){
            return ResponseEntity.status(201).build();
        }
        else{
            return ResponseEntity.badRequest().build();
        }
    }



    @PostMapping("/send-verification-email/{userId}")
    public ResponseEntity<?>sendVerificationEmail(@PathVariable String userId){
        userService.sendVerificationEmail(userId);
        return ResponseEntity.status(201).build();
    }



    @DeleteMapping("/delete-user/{userId}")
    public ResponseEntity<?>deleteUser(@PathVariable String userId){
        userService.deleteUser(userId);
        return ResponseEntity.status(204).build();  // no content
    }


    @PutMapping("/forgot-password/{username}")
    public ResponseEntity<?>forgotPassword(@PathVariable String username){
        userService.forgotPassword(username);
        return ResponseEntity.status(204).build();  // no content
    }



}
