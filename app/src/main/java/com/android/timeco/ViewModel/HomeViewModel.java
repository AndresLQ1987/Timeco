package com.android.timeco.ViewModel;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.android.timeco.AccessData;
import com.android.timeco.MainActivity;
import com.android.timeco.Model.User;
import com.android.timeco.Model.Worklog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Class HomeViewModel have methods to process the information gets in view home
 */
public class HomeViewModel extends ViewModel {

    AccessData ad = AccessData.get();

    public void initiateWorklogFile(Context context){
        User user = MainActivity.currentUser;
        AccessData ad = AccessData.get();
        ad.initializeWorklogsFile(context, user.getUsername() + ".bin");
    }

    public void inputWorklog(String startHours, String startMinutes, String endHours,
                             String endMinutes, String restHours, String restMinutes){

        Date dateInit = getDateFromInputs(startHours, startMinutes);
        Date dateEnd = getDateFromInputs(endHours, endMinutes);
        float restTime = formatInputToFloat(restHours, restMinutes);

        Worklog worklog = new Worklog(dateInit, dateEnd, restTime);
        User user = MainActivity.currentUser;

        ArrayList<Worklog> worklogsList = ad.getWorklogs(user);
        worklogsList.add(worklog);
        ad.saveWorklogs(user, worklogsList);
    }

    private Date getDateFromInputs(String hours, String minutes){
        int h = Integer.parseInt(hours);
        int m = Integer.parseInt(minutes);
        Date now = new Date();

        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"));
        cal.setTime(now);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        return new Date(year, month, day, h, m);
    }

    private float formatInputToFloat(String hours, String minutes){
        int h = Integer.parseInt(hours);
        int m = Integer.parseInt(minutes);

        return h + m / 60;
    }
}
