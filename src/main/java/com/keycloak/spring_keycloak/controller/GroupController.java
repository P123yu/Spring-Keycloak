package com.keycloak.spring_keycloak.controller;

import com.keycloak.spring_keycloak.service.GroupService;
import org.keycloak.representations.idm.GroupRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/group")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @PostMapping("assign-users/{groupId}/{userId}")
    public ResponseEntity<?> assignUserToGroup(@PathVariable String userId, @PathVariable String groupId) {
        groupService.assignGroup(userId, groupId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/delete-users/{groupId}/{userId}")
    public ResponseEntity<?> unAssignGroup(@PathVariable String userId, @PathVariable String groupId) {
        groupService.deleteGroupFromUser(userId, groupId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/get-users/{userId}")
    public ResponseEntity<?> getUserGroups(@PathVariable String userId) {
        List<GroupRepresentation> groupRepresentationList=groupService.getUserGroups(userId);
        return ResponseEntity.status(HttpStatus.OK).body(groupRepresentationList);
    }

}
