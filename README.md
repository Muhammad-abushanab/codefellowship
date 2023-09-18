# codefellowship

This project is about making the authentication process using spring security
The user can log in and signup.

## Routes

| HTTP Method | Path                | Description                                  |
|-------------|---------------------|----------------------------------------------|
| GET         | /login              | Display the login page.                      |
| GET         | /logout             | logs the user out.                           |
| GET         | /signup             | Display the signup page.                     |
| GET         | /                   | Display the home page with user information. |
| POST        | /signup             | Add a new user (signup).                     |
| GET         | /profile/{username} | Displays the user information                |

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