package com.example.hospitalfinder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

import java.util.HashMap;

public class DatabaseConnection extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "HospitalFinder.db";
    private static final String TABLE_USER_APOINTMENT = "Apointment";
    private static final String USER_NAME = "name";
    private static final String USER_BLOOD = "blood";
    private static final String USER_AGE = "age";

    private static final String TABLE_USER_REVIEW = "HospitalReview";
    private static final String QUESTION_ANSWER1 = "answer1";
    private static final String QUESTION_ANSWER2 = "answer2";

    private static final String TABLE_USER_APP = "AcceptedAppoinment";
    private static final String USER_NAME_APP = "nameapp";
    private static final String USER_BLOOD_APP = "bloodapp";
    private static final String USER_AGE_APP = "ageapp";
    private static final String USER_DATE_APP = "dateapp";
    private static final String USER_TIME_APP = "timeapp";

    private static final String TABLE_DEMO = "DemoTable";
    private static final String DEMO_NAME = "demoname";
    private static final String DEMO_MOBILE = "demomobile";
    private static final String DEMO_ADD = "addressof";




    private static final String CREATE_TABLE_USER_APOINTMENT = "CREATE TABLE "
            + TABLE_USER_APOINTMENT + "(" + USER_NAME + " TEXT,"+ USER_BLOOD + " TEXT, "+ USER_AGE + " TEXT );";

    private static final String CREATE_TABLE_HOSPITAL_REVIEW = "CREATE TABLE "
            + TABLE_USER_REVIEW + "(" + QUESTION_ANSWER1 + " TEXT,"+ QUESTION_ANSWER2 + " TEXT);";

    private static final String CREATE_TABLE_USER_APP = "CREATE TABLE "
            + TABLE_USER_APP + "(" + USER_NAME_APP + " TEXT, "+ USER_BLOOD_APP + " TEXT, "+ USER_AGE_APP +" TEXT, "+ USER_DATE_APP +" TEXT, "+ USER_TIME_APP +" TEXT);";

    private static final String CREATE_TABLE_DEMO = "CREATE TABLE " +TABLE_DEMO+ "("+DEMO_NAME+" TEXT, "+DEMO_MOBILE+" TEXT, "+DEMO_ADD+" TEXT );";


    private HashMap hp;
    public DatabaseConnection(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USER_APOINTMENT);
        db.execSQL(CREATE_TABLE_HOSPITAL_REVIEW);
        db.execSQL(CREATE_TABLE_USER_APP);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS '" + CREATE_TABLE_USER_APOINTMENT + "'");
        db.execSQL("DROP TABLE IF EXISTS '" + CREATE_TABLE_HOSPITAL_REVIEW + "'");
        db.execSQL("DROP TABLE IF EXISTS '" + CREATE_TABLE_USER_APP + "'");
        onCreate(db);
    }

    public void dbinsert(String name, String blood, int age) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("blood", blood);
        contentValues.put("age", age);

        db.insert("Apointment", null, contentValues);
        db.close();
    }

    public void deleteoldappoinments(String us_me, String us_ood, String us_ge)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("Apointment","name = ? AND blood = ? AND age = ?", new String[]{us_me, us_ood, us_ge});
        db.close();
    }


    public void admininsert(String nameapp, String bloodapp, String ageapp, String dateapp, String timeapp) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nameapp", nameapp);
        contentValues.put("bloodapp", bloodapp);
        contentValues.put("ageapp", ageapp);
        contentValues.put("dateapp", dateapp);
        contentValues.put("timeapp", timeapp);
        db.insert("AcceptedAppoinment", null, contentValues);
        db.close();
    }

    public void reviewinsert(String answer1, String answer2) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("answer1", answer1);
        contentValues.put("answer2", answer2);
        db.insert("HospitalReview", null, contentValues);
        db.close();
    }

    public Cursor getData() {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor res = db.rawQuery("select * from Apointment", null);
        return res;
    }

    Cursor readAllApointments()
    {
        String query = "SELECT * FROM Apointment";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null)
        {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    Cursor readAllAppApointments()
    {
        String query = "SELECT * FROM AcceptedAppoinment";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null)
        {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    Cursor readAllreview()
    {
        String query = "SELECT * FROM HospitalReview";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null)
        {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

}