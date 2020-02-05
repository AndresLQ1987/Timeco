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
}