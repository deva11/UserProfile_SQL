package com.userprofile.deva.userprofile;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;
import android.util.Log;

import java.util.jar.Attributes;

/**
 * Created by Deva on 28-11-2016.
 */

public class SQLDBhelper extends SQLiteOpenHelper {



    public static final String MY_SQL_DB = "Userprofile.db";
    public static final String TABLE_NAME = "loginCredentials";
    public static final String COL1 = "ID";
    public static final String COL2 = "NAME";
    public static final String COL3 = "EMAILID";
    public static final String COL4 = "PASSWORD";
    public static final String COL5 = "MOBILE";
    public static final String COL6 = "ADDRESS";
    public static final String COL7 = "AOI";
    public static final String COL8 = "DOB";



    public SQLDBhelper(Context context) {
        super(context, MY_SQL_DB, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(" CREATE TABLE " +TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,EMAILID TEXT,PASSWORD TEXT,MOBILE TEXT,ADDRESS TEXT,AOI TEXT,DOB TEXT)");





    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    public boolean Insertdata(String name,String email,String password,String mobile,String address,String spindata,String dob)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues coval = new ContentValues();
        coval.put(COL2,name);
        coval.put(COL3,email);
        coval.put(COL4,password);
        coval.put(COL5,mobile);
        coval.put(COL6,address);
        coval.put(COL7,spindata);
        coval.put(COL8,dob);

        Long data = db.insert(TABLE_NAME,null,coval);
        if(data == -1)
            return false;
        else
            return true;


    }

    public boolean EDITdata(String name,String EMailID,String password,String mobile,String address,String spindata,String dob)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues coval = new ContentValues();
        coval.put(COL2,name);
        //coval.put(COL3,email);
        coval.put(COL4,password);
        coval.put(COL5,mobile);
        coval.put(COL6,address);
        coval.put(COL7,spindata);
        coval.put(COL8,dob);

        long x = db.update(TABLE_NAME,coval,COL3+" = '" +EMailID+"'" , null);


        return true;

        /*Long data = db.insert(TABLE_NAME,null,coval);


        if(data == -1)
            return false;
        else
            return true;  */



    }

    public Cursor Retrieveall()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor result = db.rawQuery("SELECT * FROM "+TABLE_NAME,null);
        return result;
    }

    public Cursor RetrieveEidandName()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor result = db.rawQuery("SELECT NAME,EMAILID FROM "+TABLE_NAME,null);
        return result;
    }

    public  Cursor particularuser(String email,String password)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery(("SELECT * FROM "+ TABLE_NAME+ " WHERE "+COL3+  " = '" +email+ "' AND "+COL4+" = '"+password+"';"),null);
        Log.e("DBCALL_",""+res);
        return  res;

    }

    public Cursor RetrieveDataForEdit(String email)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor data = db.rawQuery(("SELECT * FROM "+ TABLE_NAME + " WHERE "+COL3+ "= '"+email+"';"),null);
        return data;
    }

    public Cursor Emaichecker(String email)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor emailres = db.rawQuery(("SELECT * FROM "+ TABLE_NAME+ " WHERE "+COL3+  " = '" +email+ "';"),null);
        return  emailres;
    }





}
