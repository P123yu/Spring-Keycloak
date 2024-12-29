package com.keycloak.spring_keycloak.model;
//
//public record User(String username,String password,String firstName,String lastName) {
//}

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User{
    private String username;
    private String password;
    private String firstName;
    private String lastName;
}