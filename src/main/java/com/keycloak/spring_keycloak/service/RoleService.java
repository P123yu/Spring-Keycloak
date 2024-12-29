package com.keycloak.spring_keycloak.service;

import org.keycloak.representations.idm.RoleRepresentation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoleService {

    // assign roles to user
    void assignRole(String userId ,String roleName);

    // delete user roles
    void deleteRole(String userId ,String roleName);

    // get list of roles
    List<RoleRepresentation> getUserRoles(String userId);
}
