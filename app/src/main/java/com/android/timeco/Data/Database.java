package com.android.timeco.Data;

import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.android.timeco.Model.Converters;
import com.android.timeco.Model.User;
import com.android.timeco.Model.UserDao;
import com.android.timeco.Model.Worklog;
import com.android.timeco.Model.WorklogDao;

@androidx.room.Database(version = 1, entities = {User.class, Worklog.class})
@TypeConverters({Converters.class})
public abstract class Database extends RoomDatabase {
    abstract public UserDao getUserDao();
    abstract public WorklogDao getWorklogDao();
}
