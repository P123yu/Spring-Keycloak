package com.keycloak.spring_keycloak.service;


import org.keycloak.representations.idm.GroupRepresentation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GroupService {
    void assignGroup(String userId ,String groupId);
    void deleteGroupFromUser(String userId ,String groupId);

    List<GroupRepresentation> getUserGroups(String userId);

}
