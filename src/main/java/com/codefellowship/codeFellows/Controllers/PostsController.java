package com.codefellowship.codeFellows.Controllers;

import com.codefellowship.codeFellows.Models.ApplicationUser;
import com.codefellowship.codeFellows.Models.Posts;
import com.codefellowship.codeFellows.Repository.ApplicationUserRepo;
import com.codefellowship.codeFellows.Repository.PostsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
public class PostsController {
    @Autowired
    PostsRepo postsRepo;
    @Autowired
    ApplicationUserRepo applicationUserRepo;
    @PostMapping("/posts/create/{username}")
    public RedirectView savePost(@PathVariable("username") String username , @RequestParam("body") String body , HttpServletRequest request) {
        ApplicationUser user = applicationUserRepo.findByUserName(username);
        Posts post = new Posts(body,user);
        postsRepo.save(post);
        return new RedirectView("/profile/" + user.getUsername());
    }
    @DeleteMapping("/post/delete/{id}")
    public RedirectView deletePost(@PathVariable("id") long id, Principal p,HttpServletRequest request) {
        postsRepo.deleteById(id);
        return new RedirectView("/profile/" +p.getName());
    }
}
