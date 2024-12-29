package com.keycloak.spring_keycloak.service.impl;

import com.keycloak.spring_keycloak.service.RoleService;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RolesResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.RolesRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Value("${app.keycloak.realm}")
    private String realm;

    @Autowired
    private Keycloak keycloak;

    @Override
    public void assignRole(String userId, String roleName) {

        // get user by userid
        UsersResource usersResource=keycloak.realm(realm).users();
        UserResource userResource=usersResource.get(userId);

        // get roles
        RolesResource rolesResource=keycloak.realm(realm).roles();
        RoleRepresentation roleRepresentation=rolesResource.get(roleName).toRepresentation();

        // add roles to user
        userResource.roles().realmLevel().add(List.of(roleRepresentation));
    }


    // just change from add to remove

    @Override
    public void deleteRole(String userId, String roleName) {
        // get user by userid
        UsersResource usersResource=keycloak.realm(realm).users();
        UserResource userResource=usersResource.get(userId);

        // get roles
        RolesResource rolesResource=keycloak.realm(realm).roles();
        RoleRepresentation roleRepresentation=rolesResource.get(roleName).toRepresentation();

        // add roles to user
        userResource.roles().realmLevel().remove(List.of(roleRepresentation));

    }

    @Override
    public List<RoleRepresentation> getUserRoles(String userId) {
        UsersResource usersResource=keycloak.realm(realm).users();
        UserResource userResource=usersResource.get(userId);
        return userResource.roles().realmLevel().listAll();
    }
}
