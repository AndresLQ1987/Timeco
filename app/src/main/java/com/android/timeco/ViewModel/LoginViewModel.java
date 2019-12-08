package com.android.timeco.ViewModel;

import androidx.lifecycle.ViewModel;

import com.android.timeco.AccessData;
import com.android.timeco.Model.User;

import java.util.ArrayList;

/**
 * Class LoginViewModel have methods to process the information gets in view login
 */
public class LoginViewModel extends ViewModel {

    AccessData ad = AccessData.get();

    /**
     * Returns true if the user and his password match with the Users.bin File
     * @param username
     * @param password
     * @return
     */
    public boolean checkCredentials(String username, String password){
        ArrayList<User> userList = ad.getUsers();

        if(username.equals("") && password.equals("")) return true;
        for (User user: userList
             ) {
            if(checkUser(user, username, password))
                return true;
        }
        return false;
    }
    //TODO cypher passwords
    private boolean checkUser(User user, String username, String password){

        return user.getUsername().equals(username) && user.getPassword().equals(password);
    }
}
