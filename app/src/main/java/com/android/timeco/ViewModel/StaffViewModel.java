package com.android.timeco.ViewModel;

import androidx.lifecycle.ViewModel;

import com.android.timeco.AccessData;
import com.android.timeco.MainActivity;
import com.android.timeco.Model.User;

import java.util.ArrayList;

/**
 * Class StaffViewModel have methods to process the information gets in view staff
 */
public class StaffViewModel extends ViewModel {
    //TODO Methods to process staff add or remove workers, edit, etc.

    public boolean enterUser(String name, String surname, String position, String username,
            String password){
        try{
            User user = new User(username, password, name, surname, "", 2);
            MainActivity.accessData.saveUsers(user);

            return true;
        }
        catch(Exception ex){
            return false;
        }
    }
}
