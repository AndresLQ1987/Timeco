package com.android.timeco.ViewModel;

import androidx.lifecycle.ViewModel;

import com.android.timeco.MainActivity;
import com.android.timeco.Model.User;

import java.util.List;

/**
 * Class LoginViewModel have methods to process the information gets in view login
 */
public class LoginViewModel extends ViewModel {

    /**
     * Returns true if the user and his password match with the Users.bin File
     * @param username
     * @param password
     * @return
     */
    public boolean checkCredentials(String username, String password){
        List<User> userList = MainActivity.accessData.getUsers();
        if (userList.size() == 0) MainActivity.accessData.insertFirstUser();
        for (User user : userList) {
            if (checkUser(user, username, password)) {
                MainActivity.currentUser = user;
                return true;
            }
        }
        return false;
    }
    //TODO cypher passwords
    private boolean checkUser(User user, String username, String password){

        return user.getUsername().equals(username) && user.getPassword().equals(password);
    }
}
