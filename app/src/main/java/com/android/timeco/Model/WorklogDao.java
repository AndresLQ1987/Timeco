package com.android.timeco.Model;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

/**
 *  Class Worklog this class is used for save the time as work the user, also save the start and the
 *  end of work time, at last save the time of break time.
 */
@Dao
public interface WorklogDao {

    @Query("SELECT * FROM Worklog")
    List<Worklog> getWorklogs();

    @Query("SELECT * FROM Worklog WHERE id LIKE :uuid")
    Worklog getWorklog(String uuid);

    @Query("SELECT * FROM Worklog WHERE username LIKE :user")
    List<Worklog> getWorklogsByUser(String user);

    @Insert
    void addWorklog(Worklog worklog);

    @Delete
    void deleteWorklog(Worklog worklog);

    @Update
    void updateWorklog(Worklog worklog);
}