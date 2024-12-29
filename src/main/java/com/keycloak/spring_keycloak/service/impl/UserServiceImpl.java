package com.keycloak.spring_keycloak.service.impl;

import com.keycloak.spring_keycloak.model.User;
import com.keycloak.spring_keycloak.service.UserService;
import jakarta.ws.rs.core.Response;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

//    @Autowired
//    private User user;

    @Value("${app.keycloak.realm}")
    private String realm;

    @Autowired
    private Keycloak keycloak;  // gives userResource

//
//
//    // create user
//    @Override
//    public Response createUser(User user) {
//
//        // for user details
//
//        UserRepresentation  userRepresentation= new UserRepresentation();
//        userRepresentation.setEnabled(true);
//        userRepresentation.setFirstName(user.getFirstName());
//        userRepresentation.setLastName(user.getLastName());
//        userRepresentation.setUsername(user.getUsername());
//        userRepresentation.setEmail(user.getUsername());
//        userRepresentation.setEmailVerified(false);
//
//        // for credentials
//        CredentialRepresentation credentialRepresentation=new CredentialRepresentation();
//        credentialRepresentation.setValue(user.getPassword());
//        credentialRepresentation.setType(CredentialRepresentation.PASSWORD);
//
//        // set password in user
//        userRepresentation.setCredentials(List.of(credentialRepresentation));
//
//        // how i set this user to keycloak
//
//        UsersResource usersResource=keycloak.realm(realm).users();
//        return usersResource.create(userRepresentation);
//
//    }





    // create user and send email for verification
    @Override
    public Response createUser(User user) {

        // for user details

        UserRepresentation  userRepresentation= new UserRepresentation();
        userRepresentation.setEnabled(true);
        userRepresentation.setFirstName(user.getFirstName());
        userRepresentation.setLastName(user.getLastName());
        userRepresentation.setUsername(user.getUsername());
        userRepresentation.setEmail(user.getUsername());
        userRepresentation.setEmailVerified(false);

        // for credentials
        CredentialRepresentation credentialRepresentation=new CredentialRepresentation();
        credentialRepresentation.setValue(user.getPassword());
        credentialRepresentation.setType(CredentialRepresentation.PASSWORD);

        // set password in user
        userRepresentation.setCredentials(List.of(credentialRepresentation));

        // set this user to keycloak

        UsersResource usersResource=keycloak.realm(realm).users();
        Response response= usersResource.create(userRepresentation);



        // send verification mail automatically after account is created  =========

        // fetch user-details by username . we get user info because every username is unique
        List<UserRepresentation> userRepresentations = usersResource.searchByUsername(user.getUsername(), true);
        // from the username fetch id and pass it to send verification email
        UserRepresentation userRepresentation1 = userRepresentations.get(0);
        sendVerificationEmail(userRepresentation1.getId());


        return response;


    }



    // send email
    @Override
    public void sendVerificationEmail(String userId) {
        UsersResource usersResource=keycloak.realm(realm).users();
        usersResource.get(userId).sendVerifyEmail();
    }

    @Override
    public void deleteUser(String userId) {
        UsersResource usersResource=keycloak.realm(realm).users();
        usersResource.delete(userId);
    }


    @Override
    public void forgotPassword(String username) {
        UsersResource usersResource = keycloak.realm(realm).users();
        List<UserRepresentation> userRepresentations = usersResource.searchByUsername(username, true);
        UserRepresentation userRepresentation1 = userRepresentations.get(0);
        // convert user representation back to user resource
        UserResource userResource = usersResource.get(userRepresentation1.getId());
        userResource.executeActionsEmail(List.of("UPDATE_PASSWORD"));
    }




}
