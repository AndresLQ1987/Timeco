package com.android.timeco.ViewModel;

import androidx.lifecycle.ViewModel;

import com.android.timeco.MainActivity;
import com.android.timeco.Model.Worklog;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Class HomeViewModel have methods to process the information gets in view home
 */
public class HomeViewModel extends ViewModel {

    public void inputWorklog(String startHours, String startMinutes, String endHours,
                             String endMinutes, String restHours, String restMinutes){

        Date dateInit = getDateFromInputs(startHours, startMinutes);
        Date dateEnd = getDateFromInputs(endHours, endMinutes);
        float restTime = formatInputToFloat(restHours, restMinutes);

        Worklog worklog = new Worklog(MainActivity.currentUser.getUsername(),dateInit, dateEnd, restTime);

        MainActivity.accessData.saveWorklogs(MainActivity.currentUser, worklog);
    }

    private Date getDateFromInputs(String hours, String minutes){
        int h = Integer.parseInt(hours);
        int m = Integer.parseInt(minutes);
        Date now = new Date();

        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"));
        cal.setTime(now);
        int year = cal.get(Calendar.YEAR) - 1900; //The constructor adds 1900 to the year
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
