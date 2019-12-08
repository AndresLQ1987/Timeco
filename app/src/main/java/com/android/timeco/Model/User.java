package com.android.timeco.Model;

import java.io.Serializable;

/**
 *  User Class define the features and methods of Users
 */

public class User implements Serializable {

    private enum Rol{
        owner,
        admin,
        basic
    }

    private String username;
    private String password;
    private String fullname;
    private Rol rol;

    /**
     * Builders
     */

    /**
     * Basic Builder
     */
    public User(){
        username = null;
        password = null;
        fullname = null;
        rol = null;
    }

    /**
     * Builder initiate all features of User
     *
     * @param usern String whit nick for Login User
     * @param pwd String whit password of User
     * @param name String whit name of the User
     * @param firstsurn String whit the first surname of User
     * @param lastsurn String whit last surname of User
     * @param rol Int Define the rol of user (1=owner, 2=admin, 3=basic)
     */
    public User(String usern, String pwd, String name, String firstsurn, String lastsurn, int rol){
        username = usern;
        password = pwd;
        fullname = name + " " + firstsurn + " " + lastsurn;
        setRol(rol);
    }

    /**
     * Setters
     */

    /**
     * Set the nick of User for Login
     *
     * @param username String
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Set the password of User for Login
     * @param password String
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Set the full name of User
     *
     * @param name String whit name of User
     * @param fsurn String whit first surname of User
     * @param lsurn String whit last surname of User
     */
    public void setFullname(String name, String fsurn, String lsurn) {
        this.fullname = name + " " + fsurn + " " + lsurn;
    }

    /**
     * Set the Rol to User for give them access to other features of app
     *
     *  1 - Owner --> Can create or delete Admins/basics and see the worklogs of admins/basics
     *  2 - Admin --> Can create or delete basics and see his worklog and the basics worklogs
     *  3 - basic --> Can see his worklog.
     *
     * @param rol Int
     */
    public void setRol(int rol) {
        switch (rol) {
            case 1:
                this.rol = Rol.owner;
                break;

            case 2:
                this.rol = Rol.admin;
                break;

            case 3:
                this.rol = Rol.basic;
                break;

            default:
                break;
        }
    }

    /**
     * Getters
     */

    /**
     * Get the nick of User
     *
     * @return String
     */
    public String getUsername() {
        return username;
    }

    /**
     * Get the password of User
     *
     * @return String
     */
    public String getPassword() {
        return password;
    }

    /**
     * Get the full name of User
     *
     * @return String
     */
    public String getFullname() {
        return fullname;
    }

    /**
     * Get the Rol of User
     *
     * @return String
     */
    public Rol getRol() {
        return rol;
    }
}
