package com.codefellowship.codeFellows.Controllers;

import com.codefellowship.codeFellows.Models.ApplicationUser;
import com.codefellowship.codeFellows.Repository.ApplicationUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.time.LocalDate;
import java.util.Date;

@Controller
public class ProfileController {
    @Autowired
    ApplicationUserRepo applicationUserRepo;


    @GetMapping("/profile/{username}")
    public String getUserProfile(@PathVariable("username") String username, Model profielModel,Principal p) {
            ApplicationUser user = applicationUserRepo.findByUserName(username);
            if (user != null) {
                profielModel.addAttribute("username", username);
                profielModel.addAttribute("dateOfBirth", user.getDob());
                profielModel.addAttribute("firstName", user.getFirstName());
                profielModel.addAttribute("lastName", user.getLastName());
                profielModel.addAttribute("bio", user.getBio());
                profielModel.addAttribute("posts", user.getPosts());
                System.out.println(user.getPosts());
                return "profile";
            }
            return "redirect:/profile/" + p.getName();

    }

    @PutMapping("/profile/{username}/edit")
    public RedirectView editUserProfile(@PathVariable("username") String username,
                                        @RequestParam("bio") String bio,
                                        @RequestParam("firstName") String firstName,
                                        @RequestParam("lastName") String lastName,
                                        @RequestParam("dateOfBirth") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateOfBirth
            , Principal p, RedirectAttributes redir) {
        if (p != null && p.getName().equals(username)) {
            ApplicationUser user = applicationUserRepo.findByUserName(username);
            System.out.println("in put mapping");
            System.out.println(bio);
            user.setDob(dateOfBirth);
            user.setBio(bio);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            applicationUserRepo.save(user);
            return new RedirectView("/profile/" + user.getUsername());
        } else {
            redir.addFlashAttribute("Error", "UnAuthorized endpoint");
            return new RedirectView("/profile/" + p.getName());
        }

    }
}
