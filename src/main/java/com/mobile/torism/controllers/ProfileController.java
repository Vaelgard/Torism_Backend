package com.mobile.torism.controllers;

import com.mobile.torism.dto.ReqRes;
import com.mobile.torism.dto.UserDTO;
import com.mobile.torism.entities.OurUsers;
import com.mobile.torism.services.UsersManagementService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/profile")
public class ProfileController {
    private UsersManagementService usersManagementService;
    @GetMapping("/getuser/{userEmail}")
    public ResponseEntity<UserDTO> getUSerByID(@PathVariable String userEmail){
        System.out.println("here000" + userEmail);
        return ResponseEntity.ok(usersManagementService.getUsersByEmail(userEmail));

    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<ReqRes> updateUser(@PathVariable Integer userId, @RequestBody OurUsers reqres){
        System.out.println("here" + userId);
        System.out.println(reqres.getName());
        return ResponseEntity.ok(usersManagementService.updateUser(userId, reqres));
    }
}
