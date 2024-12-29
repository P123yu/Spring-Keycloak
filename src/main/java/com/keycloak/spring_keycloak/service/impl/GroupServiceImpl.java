package com.keycloak.spring_keycloak.service.impl;

import com.keycloak.spring_keycloak.service.GroupService;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.GroupRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private Keycloak keycloak;

    @Value("${app.keycloak.realm}")
    private String realm;

    @Override
    public void assignGroup(String userId, String groupId) {
        UsersResource usersResource=keycloak.realm(realm).users();
        UserResource userResource=usersResource.get(userId);
        userResource.joinGroup(groupId);
    }


    // just replace joinGroup by leaveGroup

    @Override
    public void deleteGroupFromUser(String userId, String groupId) {
        UsersResource usersResource=keycloak.realm(realm).users();
        UserResource userResource=usersResource.get(userId);
        userResource.leaveGroup(groupId);
    }

    @Override
    public List<GroupRepresentation> getUserGroups(String userId) {
        UsersResource usersResource=keycloak.realm(realm).users();
        UserResource userResource=usersResource.get(userId);
        return userResource.groups();
    }

}
