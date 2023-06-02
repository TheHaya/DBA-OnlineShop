/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;

/**
 *
 * @author Haya
 * 
 * Die user Klasse gibt alle benötigten Variable für die Benutzerdaten vor.
 */
public class user implements Serializable{
    private String username; // Account Username
    private String password; // Account Password
    private String email;    // User Email
    //private Date dob;        // User date of birth
    private int userID;      // User ID
    private int rights;      // Rights, 0 = admin, 1 = nobody
    
    public user (String username, String password, String email, int userID, int rights){
        this.username = username;
        this.password = password;
        this.email = email;
        //this.dob = dob;
        this.userID = userID;
        this.rights = rights;
    }
    
    public user(String username, String password, String email, int rights) {
    this.username = username;
    this.password = password;
    this.email = email;
    //this.dob = dob;
    this.rights = 2;
}
    
    // Getter und Setter
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

//    public Date getDob() {
//        return dob;
//    }
//
//    public void setDob(Date dob) {
//        this.dob = dob;
//    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getRights() {
        return rights;
    }

    public void setRights(int rights) {
        this.rights = rights;
    }
    
    
}
