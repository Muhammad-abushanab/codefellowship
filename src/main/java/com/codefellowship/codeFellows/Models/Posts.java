package com.codefellowship.codeFellows.Models;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Posts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column (name = "body" , length = 2000,nullable = false)
    private String body;

    @Column(name = "createdAt")
    private LocalDate createdAt;

    @ManyToOne
    private ApplicationUser user;

    public Posts(){

    }
    public Posts(String body,ApplicationUser user){
        this.body = body;
        this.user = user;
        this.createdAt = LocalDate.now();
    }

    public long getId() {
        return id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public ApplicationUser getUser() {
        return user;
    }

    public void setUser(ApplicationUser user) {
        this.user = user;
    }

}
