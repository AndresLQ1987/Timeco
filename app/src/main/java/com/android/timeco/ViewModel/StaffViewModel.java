package com.android.timeco.ViewModel;

import androidx.lifecycle.ViewModel;

import com.android.timeco.MainActivity;
import com.android.timeco.Model.User;

/**
 * Class StaffViewModel have methods to process the information gets in view staff
 */
public class StaffViewModel extends ViewModel {
    //TODO Methods to process staff add or remove workers, edit, etc.

    public boolean enterUser(String name, String surname, String position, String username,
            String password){
        try{
            User user = new User(username, password, "", 2);
            MainActivity.accessData.saveUser(user);
            MainActivity.accessData.insertInPostgres(user.getId(),user.getUsername(),user.getPassword(),
                    user.getFullname(),user.getRol());

            return true;
        }
        catch(Exception ex){
            return false;
        }
    }
}
