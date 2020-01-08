package com.android.timeco;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.android.timeco.Model.User;
import com.android.timeco.Model.Worklog;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AccessData extends SQLiteOpenHelper {

    private Context c;
    private File UsersFile;
    private File WorklogsFile;

    /**
     * Singleton
     */
    private static AccessData accessdata;

    /**
     * Basic Builder
     */
    private AccessData (Context ctx, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(ctx, name, factory, version);
    }

    public static AccessData get(Context ctx, String name, SQLiteDatabase.CursorFactory factory, int version) {

        if (accessdata == null) {
            accessdata = new AccessData(ctx, name, factory, version);
        }
        return accessdata;
    }

    public void initializeFile(Context context, String fileName){
        c = context;
        UsersFile = new File(c.getFilesDir(), fileName);

        try{
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(c.getFilesDir().getPath() + "/" + fileName));
        }
        catch (FileNotFoundException e){
            try {
                File UsersFile = new File(c.getFilesDir(), fileName);
                UsersFile.createNewFile();
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(!fileExists()) {
            User user = new User();
            ArrayList<User> newList = new ArrayList<>();
            newList.add(user);
            saveUsers(newList);
        }
    }

    private boolean fileExists(){
        try{
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(UsersFile));
            List<User> Users =  (ArrayList<User>) ois.readObject();
            ois.close();
        } catch (FileNotFoundException e) {
            return false;
        } catch (IOException e) {
            return false;
        } catch (ClassNotFoundException e) {
            return false;
        }
        return true;
    }

    /**
     * Method for save users into file of users
     *
     * @param users Object User
     */
    public void saveUsers(ArrayList<User> users){
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(UsersFile));
            oos.writeObject(users);
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method get all users on file users
     *
     * @return ArrayList<User>
     */
    public ArrayList<User> getUsers(){
        ArrayList<User> Users = null;
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(UsersFile));
            Users =  (ArrayList<User>) ois.readObject();
            ois.close();
        }  catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return Users;
    }



    public void initializeWorklogsFile(Context context, String fileName){
        c = context;
        WorklogsFile = new File(c.getFilesDir(), fileName);

        try{
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(c.getFilesDir().getPath() + "/" + fileName));
        }
        catch (FileNotFoundException e){
            try {
                File worklogsFile = new File(c.getFilesDir(), fileName);
                worklogsFile.createNewFile();
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(!wFileExists()) {
            Worklog worklog = new Worklog(new Date(), new Date(), 0);
            ArrayList<Worklog> newList = new ArrayList<>();
            newList.add(worklog);
            saveWorklogs(MainActivity.currentUser, newList);
        }
    }

    private boolean wFileExists(){
        try{
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(WorklogsFile));
            List<Worklog> worklogs =  (ArrayList<Worklog>) ois.readObject();
            ois.close();
        } catch (FileNotFoundException e) {
            return false;
        } catch (IOException e) {
            return false;
        } catch (ClassNotFoundException e) {
            return false;
        }
        return true;
    }



    /**
     * Method for save the work log of user
     *
     * @param user User receives the work log
     * @param worklogs ArrayList<Worklog> Array list whit all work logs
     */
    public void saveWorklogs(User user,ArrayList<Worklog> worklogs){
        try {
            ObjectOutputStream  oos = new ObjectOutputStream(new FileOutputStream(WorklogsFile));
            oos.writeObject(worklogs);
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method get all work logs of user
     *
     * @param user User user to want to get work logs
     * @return ArrayList<Worklog> with all work logs
     */
    public ArrayList<Worklog> getWorklogs(User user){
        ArrayList<Worklog> wlogs = null;
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(WorklogsFile));
            wlogs =  (ArrayList<Worklog>) ois.readObject();
            ois.close();
        }  catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
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
