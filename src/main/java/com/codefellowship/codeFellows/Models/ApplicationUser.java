package com.codefellowship.codeFellows.Models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Entity
public class ApplicationUser implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "userName", unique = true, nullable = false)
    private String userName;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "firstName", nullable = false)
    private String firstName;
    @Column(name = "lastName", nullable = false)
    private String lastName;
    @Column(name = "dateOfBirth", nullable = false)
    private Date dob;

    @Column(name = "bio", length = 2000, nullable = true)
    private String bio;

    @Column(name = "localDate")
    private LocalDate localDate;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Posts> posts;

    @ManyToMany
    @JoinTable(name = "followers",
            joinColumns = {@JoinColumn(name = "userId")}, inverseJoinColumns = {@JoinColumn(name = "followerId")})
    private Set<ApplicationUser> following = new HashSet<>();

    @ManyToMany(mappedBy = "following")
    private Set<ApplicationUser> followers = new HashSet<>();

    public ApplicationUser() {

    }

    public ApplicationUser(String userName, String password, String firstName, String lastName, Date dob, String bio, LocalDate localDate) {
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.bio = bio;
        this.localDate = localDate;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    public Set<ApplicationUser> getFollowing() {
        return following;
    }

    public void setFollowing(Set<ApplicationUser> following) {
        this.following = following;
    }

    public Set<ApplicationUser> getFollowers() {
        return followers;
    }

    public void setFollowers(Set<ApplicationUser> followers) {
        this.followers = followers;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public long getId() {
        return id;
    }

    public List<Posts> getPosts() {
        return posts;
    }

    public void addFollowing(ApplicationUser user) {
        this.following.add(user);
    }

    public void addFollower(ApplicationUser user) {
        this.followers.add(user);
    }

    public void setPosts(List<Posts> posts) {
        this.posts = posts;
    }
}
