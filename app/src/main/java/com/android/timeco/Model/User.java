package com.android.timeco.Model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.UUID;

/**
 *  User Class define the features and methods of Users
 */
@Entity(tableName = "User")
public class User {

    private enum Rol{
        owner,
        admin,
        basic
    }

    @PrimaryKey
    @NonNull
    private String id;
    private String username;
    private String password;
    private String fullname;
    private int rol;

    /**
     * Builders
     */

    /**
     * Basic Builder
     */
    public User(){
        id = UUID.randomUUID().toString();
    }

    public User(String newUsername, String newPassword, String newFullmane, int newRol){
        id = UUID.randomUUID().toString();
        username = newUsername;
        password = newPassword;
        fullname = newFullmane;
        rol =  newRol;
//        setRolWhitString(newRol);
    }

    //Getters
    @NonNull
    public String getId(){ return id; }
    public String getFullname() { return fullname; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public int getRol() { return rol; }

    //Setters

    public void setId(@NonNull String newId){ id = newId; }
    public void setFullname(String newFullname) { fullname = newFullname; }
    public void setUsername(String newUsername) { username = newUsername; }
    public void setPassword(String newPassword) { password = newPassword; }
    public void setRol(int newRol) { rol = newRol; }

//    public void setRolWhitString(String rol) {
//        if (rol.equalsIgnoreCase("owner")) {
//            this.rol = Rol.owner;
//        } else if (rol.equalsIgnoreCase("admin")) {
//            this.rol = Rol.admin;
//        } else if (rol.equalsIgnoreCase("basic")) {
//            this.rol = Rol.basic;
//        }
//    }

    //    public User(String usern, String pwd, String name, String firstsurn, String lastsurn, int rol){
//        username = usern;
//        password = pwd;
//        fullname = name + " " + firstsurn + " " + lastsurn;
//        setRol(rol);
//    }
//
//    public User(String usern, String pwd, String fulln, String rol){
//        username = usern;
//        password = pwd;
//        fullname = fulln;
//        setRolWhitString(rol);
//    }
//
//    /**
//     * Setters
//     */
//
//    /**
//     * Set the nick of User for Login
//     *
//     * @param username String
//     */
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    /**
//     * Set the password of User for Login
//     * @param password String
//     */
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    /**
//     * Set the full name of User
//     *
//     * @param name String whit name of User
//     * @param fsurn String whit first surname of User
//     * @param lsurn String whit last surname of User
//     */
//    public void setFullname(String name, String fsurn, String lsurn) {
//        this.fullname = name + " " + fsurn + " " + lsurn;
//    }
//
//    /**
//     * Set the Rol to User for give them access to other features of app
//     *
//     *  1 - Owner --> Can create or delete Admins/basics and see the worklogs of admins/basics
//     *  2 - Admin --> Can create or delete basics and see his worklog and the basics worklogs
//     *  3 - basic --> Can see his worklog.
//     *
//     * @param rol Int
//     */
//    public void setRol(int rol) {
//        switch (rol) {
//            case 1:
//                this.rol = Rol.owner;
//                break;
//
//            case 2:
//                this.rol = Rol.admin;
//                break;
//
//            case 3:
//                this.rol = Rol.basic;
//                break;
//
//            default:
//                break;
//        }
//    }
//
//    public void setRolWhitString(String rol) {
//        if (rol.equalsIgnoreCase("owner")) {
//            this.rol = Rol.owner;
//        } else if (rol.equalsIgnoreCase("admin")) {
//            this.rol = Rol.admin;
//        } else if (rol.equalsIgnoreCase("basic")) {
//            this.rol = Rol.basic;
//        }
//    }
//
//    /**
//     * Getters
//     */
//
//    /**
//     * Get the nick of User
//     *
//     * @return String
//     */
//    public String getUsername() {
//        return username;
//    }
//
//    /**
//     * Get the password of User
//     *
//     * @return String
//     */
//    public String getPassword() {
//        return password;
//    }
//
//    /**
//     * Get the full name of User
//     *
//     * @return String
//     */
//    public String getFullname() {
//        return fullname;
//    }
//
//    /**
//     * Get the Rol of User
//     *
//     * @return String
//     */
//    public Rol getRol() {
//        return rol;
//    }
}
