package com.janta.esir.megatips.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.HashMap;

/**
 * Created by esir on 10/07/2017.
 */

public class SQLiteHandler  extends SQLiteOpenHelper{

    private static final String TAG = SQLiteHandler.class.getSimpleName();

    //All static Variables
    //DB version
    private static final int DATABASE_VERSION = 1;

    //Db name
    private static final String DATABASE_NAME = "megabetting_tips";
    //Table_name
    private static final String TABLE_NAME = "user";

    //Table Columns
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PHONE = "phone";
    private static final String KEY_SUBSCRIPTION = "current_subscription";

    public SQLiteHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_LOGIN_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_PHONE + " TEXT UNIQUE," + KEY_SUBSCRIPTION + " TEXT"+")";
        db.execSQL(CREATE_LOGIN_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        // Create tables again
        onCreate(db);
    }

    public void addUser(String name, String uid, String phone, String subscription){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_NAME, name);
        contentValues.put(KEY_ID, uid);
        contentValues.put(KEY_PHONE, phone);
        contentValues.put(KEY_SUBSCRIPTION, subscription);

        long id = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        sqLiteDatabase.close();
        Log.e(TAG, "Has "+id);

    }

    //getting user data from database
    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<>();
        String Query = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(Query, null);

        //Move to first cusor
        cursor.moveToFirst();
        if (cursor.getCount() > 0){
            user.put("name", cursor.getString(1));
            user.put("id", cursor.getString(2));
            user.put("phone", cursor.getString(0));
            user.put("subscription", cursor.getString(3));
        }
        cursor.close();
        db.close();
        return user;
    }

    /**
     * Re crate database Delete all tables and create them again
     * */
    public void deleteUsers() {
        SQLiteDatabase db = this.getWritableDatabase();
        // Delete All Rows
        db.delete(TABLE_NAME, null, null);
        db.close();

        Log.d(TAG, "Deleted all user info from sqlite");
    }
}
