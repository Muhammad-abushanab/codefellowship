# codefellowship

This project is about making the authentication process using spring security
The user can log in and signup.

## Routes

| HTTP Method | Path                     | Description                                  |
|-------------|--------------------------|----------------------------------------------|
| GET         | /login                   | Display the login page.                      |
| GET         | /logout                  | logs the user out.                           |
| GET         | /signup                  | Display the signup page.                     |
| GET         | /                        | Display the home page with user information. |
| POST        | /signup                  | Add a new user (signup).                     |
| GET         | /profile/{username}      | Displays the user information                |
| POST        | /posts/create/{username} | Save a new post for the specified username   |
| DELETE      | /post/delete/{id}        | Delete a post by its ID                      |
| POST      | //follow/{username}        | Follow user    by his username                    |
| POST      | /unfollow/{username}        | Unfollow user by his username                      |
| GET      | /feed        | get all posts from followed users                      |

## Requirements

- PostgresSql
- IntelliJIDE
- Java 11

## How to run the application

- clone the repo into your machine
- create a database called `code_fellow`
- navigate to [Application.properties](./src/main/resources/application.properties)
- update the username and password based on your postgres credit
  run the application.
- go to your browser and type `localhost:8080`

## Features

- Authentication and authorization : users can sign up and log in into the web site
- Post creation and deletion : users can add posts and delete it 
- Profile view and editing : users can view their profile and edit it 
**NOTE users can not change their username**