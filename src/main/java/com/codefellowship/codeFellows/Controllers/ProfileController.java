package com.codefellowship.codeFellows.Controllers;

import com.codefellowship.codeFellows.Models.ApplicationUser;
import com.codefellowship.codeFellows.Repository.ApplicationUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ProfileController {
    @Autowired
    ApplicationUserRepo applicationUserRepo;
    @GetMapping("/profile/{username}")
    public String getUserProfile(@PathVariable("username") String username , Model profielModel){
        ApplicationUser user = applicationUserRepo.findByUserName(username);
        profielModel.addAttribute("username",username);
        profielModel.addAttribute("dateOfBirth",user.getDob());
        profielModel.addAttribute("firstName",user.getFirstName());
        profielModel.addAttribute("lastName",user.getLastName());
        profielModel.addAttribute("bio",user.getBio());
        return "profile";
    }
}
