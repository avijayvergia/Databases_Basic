package com.example.android.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by AKSHAY VIJAYVERGIA on 11-06-2015.
 */

public class DataBaseAdapter{

    Helper helper;

    DataBaseAdapter(Context context)
    {
        helper=new Helper(context);
    }

    public long insert(String name,String password)
    {
        SQLiteDatabase db=helper.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(helper.NAME,name);
        contentValues.put(helper.PASSWORD,password);
        long id= db.insert(helper.DATABASE_TABLE,null,contentValues);
        db.close();
        return id;
    }

    public String getAllData()
    {
        SQLiteDatabase db=helper.getWritableDatabase();

        //Here it selects the columns namely _id,name,password
        String[] columns={helper.UID,helper.NAME,helper.PASSWORD};
        StringBuffer buffer=new StringBuffer();
        Cursor cursor=db.query(helper.DATABASE_TABLE, columns, null, null, null, null, null);
        while(cursor.moveToNext())
        {
           int cid=cursor.getInt(0);
           String name=cursor.getString(1);
           String password=cursor.getString(2);
            buffer.append(cid+" "+name+" "+password+"\n");
        }
        return buffer.toString();
    }

    public String getData(String name)
    {
        SQLiteDatabase db=helper.getWritableDatabase();

        //Here it selects the columns namely name,password
        String[] columns={helper.UID,helper.NAME,helper.PASSWORD};
        StringBuffer buffer=new StringBuffer();
        Cursor cursor=db.query(helper.DATABASE_TABLE, columns, helper.NAME+"='"+name+"'", null, null, null, null);
        while(cursor.moveToNext())
        {
            int index1=cursor.getColumnIndex(helper.NAME);
            int index2=cursor.getColumnIndex(helper.PASSWORD);
            String personName=cursor.getString(index1);
            String password=cursor.getString(index2);
            buffer.append(name+" "+password+"\n");
        }
        return buffer.toString();
    }

    public String getData1(String name,String pass)
    {
        SQLiteDatabase db=helper.getWritableDatabase();
        String[] columns={helper.UID};
        String[] selectionArgs={name,pass};
        StringBuffer buffer=new StringBuffer();
        Cursor cursor=db.query(helper.DATABASE_TABLE, columns, helper.NAME+"=? AND "+helper.PASSWORD+"=?", selectionArgs, null, null, null);
        while(cursor.moveToNext())
        {
            int index1=cursor.getColumnIndex(helper.UID);
            String personID=cursor.getString(index1);
            buffer.append(personID+"\n");
        }
        return buffer.toString();
    }

    public int updateData(String oldName,String newName)
    {
        SQLiteDatabase db=helper.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(helper.NAME,newName);
        String[] whereArgs={oldName};
        //Count represents the number of rows updated
        int count=db.update(helper.DATABASE_TABLE,contentValues,helper.NAME+" =?",whereArgs);
        return count;
    }

    public int deleteData(String name)
    {
        SQLiteDatabase db=helper.getWritableDatabase();
        String[] whereArgs={name};
        int count=db.delete(helper.DATABASE_TABLE,helper.NAME+" =?",whereArgs);
        return count;
    }

    static class Helper extends SQLiteOpenHelper
    {

        private static final String DATABASE_NAME="database";
        private static final String DATABASE_TABLE="AKSHAYTABLE";
        private static final int DATABASE_VERSION=1;
        private static final String UID="_id";
        private static final String NAME="Name";
        private static final String PASSWORD="Password";
        private static final String CREATE_TABLE="CREATE TABLE "+DATABASE_TABLE+" ("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+NAME+" VARCHAR(255),"+PASSWORD+" VARCHAR(255));";
        private static final String DROP_TABLE="DROP TABLE IF EXISTS "+DATABASE_TABLE;

        public Helper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            // CREATE TABLE AKSHAYTABLE (_id INTEGER PRIMARY KEY AUTOINCREMENT, Name VARCHAR(255));
            try {
                db.execSQL(CREATE_TABLE);
        } catch (SQLException e) {
        e.printStackTrace();
    }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
                db.execSQL(DROP_TABLE);
                onCreate(db);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
