package com.android.timeco.Model;

import java.text.DecimalFormat;
import java.util.Date;

/**
 *  Class Worklog this class is used for save the time as work the user, also save the start and the
 *  end of work time, at last save the time of break time.
 */
public class Worklog {

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
    Worklog(Date dInit, Date dEnd, float rTime){
        dateInit = dInit;
        dateEnd = dEnd;
        restTime = rTime;
        CalculateWorkedTime(dInit,dEnd,rTime);
    }

    //SETTERS

    /**
     * Set the Date work begins
     *
     * @param dateInit Date
     */
    public void setDateInit(Date dateInit) {
        this.dateInit = dateInit;
    }

    /**
     * Set the Date work ends
     *
     * @param dateEnd Date
     */
    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    /**
     * Set the break time
     *
     * @param restTime float to set the rest time
     */
    public void setRestTime(float restTime) {
        this.restTime = restTime;
    }

     //GETTERS

    /**
     * Get the date when work begins
     *
     * @return Date
     */
    public Date getDateInit() {
        return dateInit;
    }

    /**
     * Get the date when work ends
     *
     * @return Date
     */
    public Date getDateEnd() {
        return dateEnd;
    }

    /**
     * Get the time of break in hours
     *
     * @return float
     */
    public float getRestTime() {
        return restTime;
    }

    /**
     * Get the total time worked in hours
     *
     * @return float
     */
    public float getWorkedTime() {
        return workedTime;
    }

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
        int hour = (int) fHours;
        int minuts = (int) ((fHours - hour) * 60);
        return fm.format(hour) + ":" + fm.format(minuts);
    }
}
