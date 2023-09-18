package com.codefellowship.codeFellows.Config;

import com.codefellowship.codeFellows.Models.ApplicationUser;
import com.codefellowship.codeFellows.Repository.ApplicationUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    ApplicationUserRepo applicationUserRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ApplicationUser user = applicationUserRepo.findByUserName(username);
        if(user == null){
            System.out.println("User Not Found" + username);
            throw new UsernameNotFoundException("user" + username + "Is not in the db");
        }
        System.out.println("Found user" + username);
        return user;
    }
}
