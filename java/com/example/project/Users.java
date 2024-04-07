package com.example.project;
public class Users {
    String username,number,email,password;
    public Users(){}
    public Users(String username,String number,String email,String pass){
        this.username=username;
        this.number=number;
        this.email=email;
        this.password=pass;
    }
    public String getUsername(){
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
