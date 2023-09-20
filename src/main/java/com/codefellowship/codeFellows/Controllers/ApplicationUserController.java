package com.codefellowship.codeFellows.Controllers;

import com.codefellowship.codeFellows.Models.ApplicationUser;
import com.codefellowship.codeFellows.Repository.ApplicationUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

@Controller
public class ApplicationUserController {
    @Autowired
    ApplicationUserRepo applicationUserRepo;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/login")
    public String getLoginPage() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken)) {
            return "redirect:/";
        } else {
            return "login";
        }
    }

    @GetMapping("/logout")
    public String getLogoutPage() {
        return "index.html";
    }

    @GetMapping("/signup")
    public String getSignupPage() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken)) {
            return "redirect:/";
        } else {
            return "signup";
        }
    }

    @GetMapping("/")
    public String getHomePage(Principal p, Model m) {

        if (p != null) {
            String username = p.getName();
            ApplicationUser user = applicationUserRepo.findByUserName(username);

            m.addAttribute("username", username);
            m.addAttribute("createdDate", user.getLocalDate());
        }

        return "index.html";
    }

    @PostMapping("/signup")
    public RedirectView addUser(@RequestParam("userName") String userName,
                                @RequestParam("password") String password,
                                @RequestParam("bio") String bio,
                                @RequestParam("firstName") String firstName,
                                @RequestParam("lastName") String lastName,
                                @RequestParam("dateOfBirth") String dateOfBirth, RedirectAttributes rdr) throws ParseException {
        ApplicationUser existingUser = applicationUserRepo.findByUserName(userName);
        if (existingUser == null) {
            ApplicationUser user = new ApplicationUser();
            user.setUserName(userName);
            user.setFirstName(firstName);
            user.setBio(bio);
            user.setLastName(lastName);
            user.setPassword(passwordEncoder.encode(password));
            user.setLocalDate(LocalDate.now());
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            System.out.println(dateFormat);
            Date date = dateFormat.parse(dateOfBirth);
            user.setDob(date);
            applicationUserRepo.save(user);
            authWithHttpServletRequest(userName, password);
            return new RedirectView("/");
        } else {
            rdr.addFlashAttribute("userExist", "Username Already Exist");
            return new RedirectView("/signup");
        }


    }

    public void authWithHttpServletRequest(String username, String password) {

        try {
            request.login(username, password);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }
}
