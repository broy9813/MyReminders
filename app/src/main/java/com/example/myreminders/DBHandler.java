package com.example.myreminders;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "myreminder.db";

    private static final String TABLE_MYREMINDER_LIST = "myreminderlist";
    private static final String COLUMN_LIST_ID = "_id";
    private static final String COLUMN_LIST_TITLE = "title";
    private static final String COLUMN_LIST_DATE = "date";
    private static final String COLUMN_LIST_TYPE = "type";


    public DBHandler (Context context, SQLiteDatabase.CursorFactory cursorFactory){
        super(context, DATABASE_NAME, cursorFactory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + TABLE_MYREMINDER_LIST + "(" +
                COLUMN_LIST_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_LIST_TITLE + " TEXT, " +
                COLUMN_LIST_DATE + " TEXT, " +
                COLUMN_LIST_TYPE + " TEXT" +
                ");";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_MYREMINDER_LIST);
        onCreate(sqLiteDatabase);
    }

    public void addMyReminderList(String title, String date, String type) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUMN_LIST_TITLE, title);
        values.put(COLUMN_LIST_DATE, date);
        values.put(COLUMN_LIST_TYPE, type);

        db.insert(TABLE_MYREMINDER_LIST, null, values);
        db.close();
    }

    public Cursor getMyReminderLists() {

        //get writeable refernce to myreminder database
        SQLiteDatabase db = getWritableDatabase();

        //select all data from myreminderlist table and return it as a cursor
        return db.rawQuery("SELECT * FROM " + TABLE_MYREMINDER_LIST, null);
    }
    public String getTableMyReminderList(int id) {

        //get writeable refernce to myreminder database
        SQLiteDatabase db = getWritableDatabase();

        String dbString = "";

        //create SQL String that will get the shopping list name
        String query = "SELECT * FROM " + TABLE_MYREMINDER_LIST +
                " WHERE " + COLUMN_LIST_ID + " = " + id;

        //execute the select statement and store result in a cursor
        Cursor cursor = db.rawQuery(query, null);

        //move to the first row in the cursor
        cursor.moveToFirst();

        //check to make sure there's a myreminder list name value
        if (cursor.getString(cursor.getColumnIndex("name")) !=null){
            //store it in the String that will be returned by the method
            dbString = cursor.getString(cursor.getColumnIndex("name"));
        }

        return dbString;
    }
}
