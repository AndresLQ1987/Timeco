package com.android.timeco.ViewModel;

import androidx.lifecycle.ViewModel;

import com.android.timeco.AccessData;
import com.android.timeco.Model.User;

import java.util.ArrayList;

/**
 * Class StaffViewModel have methods to process the information gets in view staff
 */
public class StaffViewModel extends ViewModel {
    //TODO Methods to process staff add or remove workers, edit, etc.

    AccessData ad = AccessData.get();

    public boolean enterUser(String name, String surname, String position, String username,
            String password){
        try{
            User user = new User(username, password, name, surname, "", 2);
            ArrayList<User> userList = ad.getUsers() == null ? new ArrayList<User>() : ad.getUsers();
            userList.add(user);
            ad.saveUsers(userList);

            return true;
        }
        catch(Exception ex){
            return false;
        }
    }
}
