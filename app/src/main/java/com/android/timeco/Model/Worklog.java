package com.android.timeco.Model;

/**
 *  Class Worklog this class is used for save the time as work the user, also save the start and the
 *  end of work time, at last save the time of break time.
 */

import java.text.DecimalFormat;
import java.util.Date;

public class Worklog {

    private Date dateInit;
    private Date dateEnd;
    private float restTime;
    private float workedTime;

    /**
     * Builders
     */

    /**
     * Basic Builder
     */
    Worklog(){
        dateInit = null;
        dateEnd = null;
        restTime = 0;
        workedTime = 0;
    }

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

    /**
     * Setters
     */

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
     * @param restTime
     */
    public void setRestTime(float restTime) {
        this.restTime = restTime;
    }

    /**
     * Getters
     */

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

    /**
     * Methods
     */

    /**
     * Calculate the worked time
     *
     * @param dInit Date work begins
     * @param dEnd Date work ends
     * @param rTime Float time of break in hours
     */
    public void CalculateWorkedTime(Date dInit, Date dEnd, float rTime) {
        float time = 0;
        float difTime = (float)(dEnd.getTime() - dInit.getTime());
        float difTimeInHours = difTime / (1000 * 60 * 60);
        time = difTimeInHours - rTime;
        workedTime = time;
    }

    /**
     * Convert the float into String whit format HH:mm
     *
     * HH = hours
     * mm = minutes
     *
     * @param fHours
     * @return String whit hours converted
     */
    public String convertIntoHours(float fHours) {
        String hours = "";
        DecimalFormat fm = new DecimalFormat("00");
        int hour = (int) fHours;
        int minuts = (int) ((fHours - hour) * 60);
        hours = fm.format(hour) + ":" + fm.format(minuts);
        return hours;
    }
}
