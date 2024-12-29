package com.keycloak.spring_keycloak.controller;


import com.keycloak.spring_keycloak.service.RoleService;
import org.keycloak.representations.idm.RoleRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping("/assign/{userId}")
    public ResponseEntity<?> assignRole(@PathVariable String userId, @RequestParam String roleName) {
        roleService.assignRole(userId, roleName);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<?> deleteRole(@PathVariable String userId, @RequestParam String roleName) {
        roleService.deleteRole(userId, roleName);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/get-roles/{userId}")
    public ResponseEntity<?> getUserRoles(@PathVariable String userId) {
        List<RoleRepresentation> roleRepresentationList=roleService.getUserRoles(userId);
        return ResponseEntity.status(HttpStatus.OK).body(roleRepresentationList);
    }


    // Role based access control

    @GetMapping("/role-based")
   // @PreAuthorize("hasAnyRole('MEMBER')")
    public ResponseEntity<?> testForMergeRole() {
        System.out.println("SOMETHING ");
        return ResponseEntity.status(HttpStatus.OK).body("I HAVE ACCESS to DEVELOP1 ROLE");
    }


}
