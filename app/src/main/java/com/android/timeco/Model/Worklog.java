package com.android.timeco.Model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

/**
 *  Class Worklog this class is used for save the time as work the user, also save the start and the
 *  end of work time, at last save the time of break time.
 */
@Entity(tableName = "Worklog")
public class Worklog {

    @PrimaryKey
    @NonNull
    private String id;
    @NonNull
    private String username;
    private Date dateInit;
    private Date dateEnd;
    private float restTime;
    private float workedTime;

    //BUILDER

    /**
     * Builder whit save all features and calculate the worked time
     *
     * @param dInit Date work begins
     * @param dEnd Date work ends
     * @param rTime float
     */
    public Worklog(String usern, Date dInit, Date dEnd, float rTime){
        id = UUID.randomUUID().toString();
        username = usern;
        dateInit = dInit;
        dateEnd = dEnd;
        restTime = rTime;
        CalculateWorkedTime(dInit,dEnd,rTime);
    }

    public Worklog(){
        id = UUID.randomUUID().toString();
    }

    //SETTERS

    public void setId(@NonNull String id) { this.id = id; }
    public void setUsername(@NonNull String username) { this.username = username; }
    public void setDateInit(Date dateInit) { this.dateInit = dateInit; }
    public void setDateEnd(Date dateEnd) { this.dateEnd = dateEnd; }
    public void setRestTime(float restTime) { this.restTime = restTime; }
    public void setWorkedTime(float workedTime) { this.workedTime = workedTime; }

     //GETTERS

    public String getId(){ return id; }
    public String getUsername() { return username; }
    public Date getDateInit() { return dateInit; }
    public Date getDateEnd() { return dateEnd; }
    public float getRestTime() { return restTime; }
    public float getWorkedTime() { return workedTime; }

     // METHODS

    /**
     * Calculate the worked time
     *
     * @param dInit Date work begins
     * @param dEnd Date work ends
     * @param rTime Float time of break in hours
     */
    private void CalculateWorkedTime(Date dInit, Date dEnd, float rTime) {
        float difTime = (float)(dEnd.getTime() - dInit.getTime());
        float difTimeInHours = difTime / (1000 * 60 * 60);
        workedTime = difTimeInHours - rTime;
    }

    /**
     * Convert the float into String whit format HH:mm
     *
     * HH = hours
     * mm = minutes
     *
     * @param fHours float of hours to convert
     * @return String whit hours converted
     */
    public String convertIntoHours(float fHours) {
        DecimalFormat fm = new DecimalFormat("00");
        int hours = (int) fHours;
        int minutes = (int) ((fHours - hours) * 60);
        return fm.format(hours) + ":" + fm.format(minutes);
    }
}
