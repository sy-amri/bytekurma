//Manas Ismail Abdylas, 22008600

package com.example.bytekurma;

import java.time.LocalDate;

public class User {
    private int id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate DOB;
    private float balance;

    public User(int id, float balance, String firstName, String lastName, String username, String email, LocalDate dob) {
        this.id = id;
        this.balance = balance;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.DOB = dob;
    }

    //Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setDOB(LocalDate DOB){
        this.DOB = DOB;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    //Getters
    public int getId() {
        return id;
    }

    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }

    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public String getEmail(){
        return email;
    }

    public LocalDate getDOB(){
        return DOB;
    }

    public float getBalance() {
        return balance;
    }
}
