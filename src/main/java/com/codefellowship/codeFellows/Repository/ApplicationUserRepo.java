package com.codefellowship.codeFellows.Repository;

import com.codefellowship.codeFellows.Models.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationUserRepo extends JpaRepository<ApplicationUser,Long> {

    ApplicationUser findByUserName(String username);
}
