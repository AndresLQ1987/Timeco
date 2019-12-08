package com.android.timeco.ViewModel;

import androidx.lifecycle.ViewModel;

import com.android.timeco.Model.Worklog;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

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

        for (int i=0; i<10 ; i++) {
            Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
            SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm");
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            int month = calendar.get(Calendar.MONTH);
            int year = calendar.get(Calendar.YEAR);
            Date initWork = null;
            Date endWork = null;
            try {
                initWork = sdf.parse(day + "-" + month + "-" + year + " " + "08:30");
                endWork = sdf.parse(day + "-" + month + "-" + year + " " + "20:30");
            } catch (ParseException e) {
                e.printStackTrace();
            }

            Worklog wl1 = new Worklog(initWork, endWork, 1.5f);
            listWorklogs.add(wl1);
        }

        return listWorklogs;
    }
}
