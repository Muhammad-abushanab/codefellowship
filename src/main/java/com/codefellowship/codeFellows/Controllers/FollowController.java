package com.codefellowship.codeFellows.Controllers;

import com.codefellowship.codeFellows.Models.ApplicationUser;
import com.codefellowship.codeFellows.Repository.ApplicationUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class FollowController {
    @Autowired
    ApplicationUserRepo applicationUserRepo;

    @PostMapping("/follow/{username}")
    public ResponseEntity<String> followUser(@PathVariable String username, Principal p) {
        if (p != null) {
            ApplicationUser user = applicationUserRepo.findByUserName(username);
            ApplicationUser currentUser = applicationUserRepo.findByUserName(p.getName());
            currentUser.addFollowing(user);
            applicationUserRepo.save(currentUser);
            return ResponseEntity.ok("User followed successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Couldn't follow the user");
    }
    @PostMapping("/unfollow/{username}")
    public ResponseEntity<String> unFollowUser(@PathVariable String username,Principal p){
        if (p !=null){
            ApplicationUser currentUser = applicationUserRepo.findByUserName(p.getName());
            ApplicationUser user = applicationUserRepo.findByUserName(username);
            currentUser.getFollowing().remove(user);
            applicationUserRepo.save(currentUser);
            return ResponseEntity.ok("User Deleted Successfully");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Couldn't unfollow user");
    }
    @GetMapping("/check-follow/{username}")
    public ResponseEntity<String> checkFollow(@PathVariable String username, Principal p) {
        if (p != null) {
            String currentUserUsername = p.getName();
            boolean isFollowing = isCurrentUserFollowing(currentUserUsername, username);

            if (isFollowing) {
                return ResponseEntity.ok("following");
            } else {
                return ResponseEntity.ok("notFollowing");
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Couldn't check follow status");
    }
    public boolean isCurrentUserFollowing(String currentUserUsername, String targetUserUsername) {
        ApplicationUser currentUser = applicationUserRepo.findByUserName(currentUserUsername);
        ApplicationUser targetUser = applicationUserRepo.findByUserName(targetUserUsername);
        return currentUser.getFollowing().contains(targetUser);
    }

}
