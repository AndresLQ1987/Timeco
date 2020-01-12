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
        newUser.put("ID",0);
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
        ArrayList<User> wusers = null;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor fila = db.rawQuery("SELECT Username, Password, FullName, Rol FROM Users",null);
        do {
            User readUser = new User(fila.getString(0), fila.getString(1),
                                    fila.getString(2), fila.getString(4));
            wusers.add(readUser);
            fila.moveToNext();
        } while (!fila.isLast());
        return wusers;
    }

    /**
     * Method for save the work log of user
     *
     * @param user User receives the work log
     * @param worklog ArrayList<Worklog> Array list whit all work logs
     */
    public void saveWorklogs(User user,Worklog worklog){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues newWorklog =  new ContentValues();
        newWorklog.put("ID",0);
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
        ArrayList<Worklog> wlogs = null;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor fila = db.rawQuery("SELECT Username, DateInit, DateEnd, RestTime FROM Worklogs " +
                        "WHERE Username = '" + user.getUsername() + "'",null);
        do {
            Date dInit = null;
            Date dEnd = null;
            try{
                dInit = new SimpleDateFormat("dd/MM/yyyy").parse(fila.getString(1));
                dEnd = new SimpleDateFormat("dd/MM/yyyy").parse(fila.getString(2));
            } catch( Exception e){
                e.printStackTrace();
            }
            Worklog log = new Worklog(dInit, dEnd, fila.getFloat(3));
            wlogs.add(log);
            fila.moveToNext();
        } while (!fila.isLast());
        return wlogs;
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

        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM Users", null);
        if(cursor.getString(0).equals("0")){
            db.execSQL("INSERT INTO Users (Username, Password, Fullname, Rol) VALUES ('user', 'pass', 'USERNAME ADMIN', 'owner')");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
