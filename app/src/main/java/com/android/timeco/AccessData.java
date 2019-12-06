package com.android.timeco;

import com.android.timeco.Model.User;
import com.android.timeco.Model.Worklog;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class AccessData {

    private File UsersFile = new File("Users.bin");

    /**
     * Singleton
     */
    private static AccessData accessdata;

    /**
     * Basic Builder
     */
    private AccessData (){}

    public static AccessData get() {
        if (accessdata == null) {
            accessdata = new AccessData();
        }
        return accessdata;
    }

    /**
     * Method for save users into file of users
     *
     * @param users Object User
     */
    public void saveUsers(ArrayList<User> users){
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(UsersFile));
            oos.writeObject(users);
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method get all users on file users
     *
     * @return ArrayList<User>
     */
    public ArrayList<User> getUsers(){
        ArrayList<User> Users = null;
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(UsersFile));
            Users =  (ArrayList<User>) ois.readObject();
            ois.close();
        }  catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return Users;
    }

    /**
     * Method for save the work log of user
     *
     * @param user User receives the work log
     * @param worklogs ArrayList<Worklog> Array list whit all work logs
     */
    public void saveWorklogs(User user,ArrayList<Worklog> worklogs){
        try {
            ObjectOutputStream  oos = new ObjectOutputStream(new FileOutputStream(user.getUsername() + ".bin"));
            oos.writeObject(worklogs);
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method get all work logs of user
     *
     * @param user User user to want to get work logs
     * @return ArrayList<Worklog> with all work logs
     */
    public ArrayList<Worklog> getWorklogs(User user){
        ArrayList<Worklog> wlogs = null;
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(user.getUsername() + ".bin"));
            wlogs =  (ArrayList<Worklog>) ois.readObject();
            ois.close();
        }  catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return wlogs;
    }

}
