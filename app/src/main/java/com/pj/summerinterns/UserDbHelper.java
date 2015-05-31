package com.pj.summerinterns;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Palash on 5/30/2015.
 */
public class UserDbHelper extends SQLiteOpenHelper {

    public static final String Database_name = "CONTACTS.DB";
    public static final int Database_version = 1;
    public static final String Query = "CREATE TABLE " + UserContract.NewUserInfo.Table_Name + " ( " + UserContract.NewUserInfo.User_Name + " TEXT,"
            + UserContract.NewUserInfo.User_Contact + " TEXT," + UserContract.NewUserInfo.User_Email + " TEXT );";


    UserDbHelper(Context c)
    {
        super(c,Database_name,null,Database_version);
    }

    @Override
    public void onCreate(SQLiteDatabase o) {
        o.execSQL(Query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addInformation(SingleRow obj, SQLiteDatabase o)
    {
        ContentValues cv = new ContentValues();
        cv.put(UserContract.NewUserInfo.User_Name,obj.name);
        cv.put(UserContract.NewUserInfo.User_Contact,obj.number);
        cv.put(UserContract.NewUserInfo.User_Email, obj.email);
        o.insert(UserContract.NewUserInfo.Table_Name,null,cv);

    }

    public Cursor retrieveInformation(SQLiteDatabase o)
    {
        Cursor c;
        Log.e("INSIDE USERDBHELPER", "RetrieveInformation");
        String []projections = {UserContract.NewUserInfo.User_Name, UserContract.NewUserInfo.User_Contact, UserContract.NewUserInfo.User_Email};
        c = o.query(UserContract.NewUserInfo.Table_Name,projections,null,null,null,null,null);
        Log.e("INSIDE USERDBHELPER", "RetrieveInformation, query successful");

        return c;
    }

    public Cursor searchRetrieveInformation(String Name,SQLiteDatabase o)
    {
        Cursor c;
        String []projections = {UserContract.NewUserInfo.User_Contact, UserContract.NewUserInfo.User_Email};
        String selection = UserContract.NewUserInfo.User_Name + " LIKE ?";
        String []selection_args = {Name};
        c = o.query(UserContract.NewUserInfo.Table_Name, projections, selection, selection_args, null, null, null);
        return c;
    }

    public void deleteInformation(String Name, SQLiteDatabase o)
    {

        String selection = UserContract.NewUserInfo.User_Name + " LIKE ?";
        String []selection_args = {Name};
        o.delete(UserContract.NewUserInfo.Table_Name,selection,selection_args);
    }

    public int updateInformation(String oldName, String newName, String newNumber, String newEmail, SQLiteDatabase o)
    {
        ContentValues cv = new ContentValues();
        cv.put(UserContract.NewUserInfo.User_Name,newName);
        cv.put(UserContract.NewUserInfo.User_Contact,newNumber);
        cv.put(UserContract.NewUserInfo.User_Email, newEmail);
        Log.e("INSIDE USERDBHELPER", " nO PROBLEM WITH CONTENT VALUE");
        String selection = UserContract.NewUserInfo.User_Name + " LIKE ?";
        String []selection_args = {oldName};
        int count = o.update(UserContract.NewUserInfo.Table_Name,cv,selection,selection_args);
        Log.e("INSIDE USERDBHELPER", "NO PROBLEM WITH UPDATE");
        return count;
    }

}