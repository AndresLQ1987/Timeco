package com.android.timeco;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.room.Room;

import com.android.timeco.Model.User;
import com.android.timeco.Model.UserDao;
import com.android.timeco.Model.Worklog;
import com.android.timeco.Model.WorklogDao;


import java.util.List;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Statement;


public class AccessData {

    @SuppressLint("StaticFieldLeak")
    private static AccessData sAccessData;

    private UserDao userDao;
    private WorklogDao worklogDao;
    private Connection conn;
    private String user = "ada";
    private String pwd = "lovelace";
    private String url = "jdbc:postgresql://192.168.0.22:5432/ada";

    private AccessData (Context ctx){
        Context appContext = ctx.getApplicationContext();
        Database database = Room.databaseBuilder(appContext, Database.class, "User")
                .allowMainThreadQueries().build();
        userDao = database.getUserDao();
        worklogDao = database.getWorklogDao();
    }

    public static AccessData getAccessData(Context ctx){
        if(sAccessData == null) sAccessData = new AccessData(ctx);
        return sAccessData;
    }
    /**
     * Method for save user into SQLite
     *
     * @param user Object User
     */
    public void saveUser(User user){
        userDao.addUser(user);
    }

    /**
     * Method get all users on file users
     *
     * @return List<User>
     */
    public List<User> getUsers(){
        return userDao.getUsers();
    }

    /**
     * Method for save the work log of user
     *
     * @param user User receives the work log
     * @param worklog ArrayList<Worklog> Array list whit all work logs
     */
    public void saveWorklogs(User user, Worklog worklog){
        worklogDao.addWorklog(worklog);
    }

    /**
     * Method get all work logs of user
     *
     * @param user User user to want to get work logs
     * @return ArrayList<Worklog> with all work logs
     */
    public List<Worklog> getWorklogs(User user){
        return worklogDao.getWorklogsByUser(user.getUsername());
    }

    public void insertFirstUser(){
        if(userDao.getUsers().size() == 0)
            userDao.addUser(new User("user", "pass", "Admin User Fullname", 2));
    }

    public void deleteUser(User user){
        userDao.deleteUser(user);
    }

    public void insertInPostgres(final String id, final String username,final String pass,
                                 final String fullname,final int rol){

        Thread hiloPostgres = new Thread() {
            @Override
            public void run() {
                try {
                    Class.forName("org.postgresql.Driver");

                    conn = DriverManager.getConnection(url, user, pwd);

                    Statement st = conn.createStatement();

                    st.executeUpdate("Insert into usuario values('" + id +"','" + username + "','" +
                            pass + "','" + fullname + "'," + rol + ")");
                    conn.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        };
        hiloPostgres.start();
    }
}
