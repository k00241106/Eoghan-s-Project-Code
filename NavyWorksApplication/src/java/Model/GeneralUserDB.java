/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Eoghan
 */
public class GeneralUserDB {

    private int generalUserID;
    private String username;
    private String firstName;
    private String surName;
    private String email;
    private String password;

    public GeneralUserDB() {
    }

    public GeneralUserDB(String username, String firstName, String surName, String email, String password) {
        this.username = username;
        this.firstName = firstName;
        this.surName = surName;
        this.email = email;
        this.password = password;
    }

    public GeneralUserDB(int generalUserID, String username, String firstName, String surName, String email, String password) {
        this.generalUserID = generalUserID;
        this.username = username;
        this.firstName = firstName;
        this.surName = surName;
        this.email = email;
        this.password = password;
    }

    public int getGeneralUserID() {
        return generalUserID;
    }

    public void setGeneralUserID(int generalUserID) {
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

    public void print() {
        System.out.println("General UserID: " + generalUserID);
        System.out.println("Username: " + username);
        System.out.println("First Name: " + firstName);
        System.out.println("Surname: " + surName);
        System.out.println("Email: " + email);
        System.out.println("Password: " + password);
    }

    public boolean createGeneralUser() {
        Connection c = DatabaseHelper.getConnection();
        String template = "INSERT INTO Generaluser (Username, FirstName, Surname, Email, Password) VALUES (?,?,?,?,?)";
        if (c != null) {
            try {
                PreparedStatement inserter = c.prepareStatement(template);
                inserter.setString(1, this.username);
                inserter.setString(2, this.firstName);
                inserter.setString(3, this.surName);
                inserter.setString(4, this.email);
                inserter.setString(5, this.password);

                int i = inserter.executeUpdate();
                return true;
            } catch (SQLException ex) {
                System.out.println("Error on find all " + ex);
                return false;
            }
        }
        return true;
    }

    public boolean updateGeneralUser() {
        //   boolean inserted = false;

        Connection c = DatabaseHelper.getConnection();
        System.out.println("GeneralUserDB class:");

        String template = "UPDATE GeneralUser SET Username = ?, FirstName = ?, Surname = ?, Email = ?, Password = ? WHERE GeneralUserID = ?";
        if (c != null) {
            try {
                PreparedStatement inserter = c.prepareStatement(template);
                inserter.setString(1, this.username);
                inserter.setString(2, this.firstName);
                inserter.setString(3, this.surName);
                inserter.setString(4, this.email);
                inserter.setString(5, this.password);
                inserter.setInt(6, this.generalUserID);

                System.out.println(inserter);
                int i = inserter.executeUpdate();
                return true;
            } catch (SQLException ex) {
                System.out.println("Error on update " + ex);
                return false;
            }

        }
        return true;
    }

    public boolean deleteGeneralUserByID(int generalUserID) {
        //   boolean inserted = false;

        Connection c = DatabaseHelper.getConnection();
        String template = "DELETE FROM GeneralUser WHERE GeneralUserID = ?";
        if (c != null) {
            try {
                PreparedStatement inserter = c.prepareStatement(template);
                inserter.setInt(1, generalUserID);
                int i = inserter.executeUpdate();
                return true;
            } catch (SQLException ex) {
                System.out.println("Error on find all " + ex);
                return false;
            }

        }
        return true;
    }

    public ArrayList<GeneralUser> findAllGeneralUsers() {

        System.out.println(" find all General Users");
        ArrayList<GeneralUser> allGeneralUsers = new ArrayList<GeneralUser>();

        Connection c = DatabaseHelper.getConnection();

        String template = "SELECT * FROM GeneralUser;";

        if (c != null) {
            try {
                PreparedStatement inserter = c.prepareStatement(template);
                ResultSet resultSet = inserter.executeQuery();

                while (resultSet.next()) {
                    GeneralUser g = new GeneralUser();
                    g.setGeneralUserID(resultSet.getInt("GeneralUserID"));
                    g.setUsername(resultSet.getString("Username"));
                    g.setFirstName(resultSet.getString("FirstName"));
                    g.setSurName(resultSet.getString("Surname"));
                    g.setEmail(resultSet.getString("Email"));
                    g.setPassword(resultSet.getString("Password"));

                    allGeneralUsers.add(g);

                }

                System.out.println(inserter);
                inserter.close();
                c.close();
            } catch (SQLException ex) {
                System.out.println("Error on find all " + ex);
            }

        }
        return allGeneralUsers;
    }

    public GeneralUser findGeneralUserByID(int generalUserID) {

        GeneralUser generalUser = null;
        System.out.println(" find all general user's by ID");
        Connection c = DatabaseHelper.getConnection();

        String template = "SELECT * FROM GeneralUser where GeneralUserID = ?";

        if (c != null) {
            try {
                PreparedStatement inserter = c.prepareStatement(template);
                inserter.setInt(1, generalUserID);
                ResultSet resultSet = inserter.executeQuery();
                System.out.println(inserter);
                while (resultSet.next()) {
                    generalUser = new GeneralUser();
                    generalUser.setGeneralUserID(resultSet.getInt("GeneralUserID"));
                    generalUser.setUsername(resultSet.getString("Username"));
                    generalUser.setFirstName(resultSet.getString("FirstName"));
                    generalUser.setSurName(resultSet.getString("Surname"));
                    generalUser.setEmail(resultSet.getString("Email"));
                    generalUser.setPassword(resultSet.getString("Password"));

                }
                inserter.close();
                c.close();
            } catch (SQLException ex) {
                System.out.println("Error on find all " + ex);
            }

        }
        return generalUser;
    }

}
