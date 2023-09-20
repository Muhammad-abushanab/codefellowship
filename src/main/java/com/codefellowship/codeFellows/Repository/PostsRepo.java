package com.codefellowship.codeFellows.Repository;

import com.codefellowship.codeFellows.Models.Posts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepo extends JpaRepository<Posts,Long> {
}
