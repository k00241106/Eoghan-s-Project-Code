/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.util.ArrayList;
import sun.security.util.Password;

/**
 *
 * @author Eoghan
 */
public class GeneralUser implements Serializable{

    private String generalUserID;
    private String username;
    private String firstName;
    private String surName;
    private String email;
    private String password;

    public GeneralUser() {
    }

    public GeneralUser(String username, String firstName, String surName, String email, String password) {
        this.username = username;
        this.firstName = firstName;
        this.surName = surName;
        this.email = email;
        this.password = password;
    }

    public GeneralUser(String generalUserID, String username, String firstName, String surName, String email, String password) {
        this.generalUserID = generalUserID;
        this.username = username;
        this.firstName = firstName;
        this.surName = surName;
        this.email = email;
        this.password = password;
    }

    public String getGeneralUserID() {
        return generalUserID;
    }

    public void setGeneralUserID(String generalUserID) {
        this.generalUserID = generalUserID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean createGeneralUser() {
        GeneralUserDB generalUserdb = new GeneralUserDB(username, firstName, surName, email, password);
        return generalUserdb.createGeneralUser();
    }

    public boolean updateGeneralUser() {
        System.out.println("General User:update");
        this.print();

        GeneralUserDB generalUserdb = new GeneralUserDB(generalUserID, username, firstName, surName, email, password);
        return generalUserdb.updateGeneralUser();
    }

    public boolean deleteGeneralUserByID(String generalUserID) {

        GeneralUserDB generalUserdb = new GeneralUserDB();
        return generalUserdb.deleteGeneralUserByID(generalUserID);
    }

    public ArrayList<GeneralUser> findAllGeneralUsers() {

        GeneralUserDB generalUserdb = new GeneralUserDB();
        return generalUserdb.findAllGeneralUsers();

    }

    public GeneralUser findGeneralUserByID(int generalUserID) {
        GeneralUserDB generalUser = new GeneralUserDB();
        return generalUser.findGeneralUserByID(generalUserID);
    }

    public void print() {
        System.out.println("General UserID: " + generalUserID);
        System.out.println("Username: " + username);
        System.out.println("First Name: " + firstName);
        System.out.println("Surname: " + surName);
        System.out.println("Email: " + email);
        System.out.println("Password: " + password);
    }

}
