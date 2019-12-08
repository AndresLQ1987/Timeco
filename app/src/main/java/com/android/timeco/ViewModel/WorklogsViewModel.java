package com.android.timeco.ViewModel;

import androidx.lifecycle.ViewModel;

import com.android.timeco.Model.Worklog;

import java.util.ArrayList;

/**
 * Class LoginViewModel have methods to process the information gets in view login
 */
public class WorklogsViewModel extends ViewModel {
    //TODO Methods for view the work logs

    /**
     * Method for get the work logs of user
     * @return
     */
    public ArrayList<Worklog> getWorklogs() {
        //TODO change the code for get array of work logs from the user pass in arguments

        //Temporal Array of work logs for test Recycler View
        ArrayList<Worklog> listWorklogs = new ArrayList<>();

        Worklog wl1 = new Worklog();

        return listWorklogs;
    }
}
