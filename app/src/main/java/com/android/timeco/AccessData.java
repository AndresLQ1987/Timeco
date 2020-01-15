package com.android.timeco;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.android.timeco.Model.User;
import com.android.timeco.Model.Worklog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class AccessData extends SQLiteOpenHelper {

    /**
     * Basic Builder
     */
    public AccessData (Context ctx, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(ctx, name, factory, version);
    }

    /**
     * Method for save user into SQLite
     *
     * @param user Object User
     */
    public void saveUsers(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues newUser =  new ContentValues();
        newUser.put("Username",user.getUsername());
        newUser.put("Password", user.getPassword());
        newUser.put("FullName",user.getFullname());
        newUser.put("Rol",String.valueOf(user.getRol()));
        db.insert("Users", null,newUser);
        db.close();
    }

    /**
     * Method get all users on file users
     *
     * @return ArrayList<User>
     */
    public ArrayList<User> getUsers(){
        ArrayList<User> wusers = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor fila = db.rawQuery("SELECT Username, Password, FullName, Rol FROM Users",null);
        if (fila.moveToFirst()) {
            for (int i = 0; i < fila.getCount(); i++) {
                fila.moveToPosition(i);
                User readUser = new User(fila.getString(0), fila.getString(1),
                        fila.getString(2), fila.getString(3));
                wusers.add(readUser);
            }
        }
        db.close();
        return wusers;
    }

    /**
     * Method for save the work log of user
     *
     * @param user User receives the work log
     * @param worklog ArrayList<Worklog> Array list whit all work logs
     */
    public void saveWorklogs(User user, Worklog worklog){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues newWorklog =  new ContentValues();
        newWorklog.put("Username", user.getUsername());
        newWorklog.put("DateInit", worklog.getDateInit().toString());
        newWorklog.put("DateEnd", worklog.getDateEnd().toString());
        newWorklog.put("RestTime", worklog.getRestTime());
        db.insert("Worklogs", null,newWorklog);
        db.close();
    }

    /**
     * Method get all work logs of user
     *
     * @param user User user to want to get work logs
     * @return ArrayList<Worklog> with all work logs
     */
    public ArrayList<Worklog> getWorklogs(User user){
        ArrayList<Worklog> wlogs = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor fila = db.rawQuery("SELECT Username, DateInit, DateEnd, RestTime FROM Worklogs " +
                        "WHERE Username = '" + user.getUsername() + "'",null);
        if (fila.moveToFirst()) {
            for (int i = 0; i < fila.getCount(); i++) {
                fila.moveToPosition(i);
                try {
                    Date dateInit = new SimpleDateFormat("EE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH).parse(fila.getString(1));
                    Date dateEnd = new SimpleDateFormat("EE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH).parse(fila.getString(2));
                    Worklog log = new Worklog(dateInit, dateEnd, fila.getFloat(3));
                    wlogs.add(log);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        db.close();
        return wlogs;
    }

    public void insertAdmin(){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM Users WHERE Username = 'user' AND " +
                "Password = 'pass' AND Rol = 'owner'", null);
        if (!cursor.moveToFirst()) {
            User admin = new User("user", "pass", "Admin User Fullname", "owner");
            saveUsers(admin);
        }
        db.close();
    }

    public void deleteUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("Users","Username = '" + user.getUsername() + "'",null);
        db.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS Users (" +
                "ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                "Username TEXT NOT NULL," +
                "Password TEXT NOT NULL," +
                "FullName TEXT NOT NULL," +
                "Rol TEXT)");

        db.execSQL("CREATE TABLE IF NOT EXISTS Worklogs (" +
                "ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                "Username TEXT NOT NULL," +
                "DateInit TEXT NOT NULL," +
                "DateEnd TEXT NOT NULL," +
                "RestTime FLOAT DEFAULT 0)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
