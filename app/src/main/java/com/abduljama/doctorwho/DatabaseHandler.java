package com.abduljama.doctorwho;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abduljama on 6/28/17.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "appointmentManager";

    // Contacts table name
    private static final String TABLE_CONTACTS = "appointment";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_DOC= "doctor";
    private static final String KEY_HOS = "hospital";
    private static final String KEY_DATE = "date";
    private static final String KEY_TIME = "time";



    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_DOC + " TEXT,"
                + KEY_HOS  + " TEXT,"
                + KEY_DATE  + " TEXT,"
                + KEY_TIME + " TEXT"
                + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);

        // Create tables again
        onCreate(db);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding new contact
    void addContact(AppointmentsModel contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_DOC, contact.getDoctor());
        values.put(KEY_HOS, contact.getHospital());
        values.put(KEY_DATE, contact.getDate());
        values.put(KEY_TIME, contact.getTime());
        // Contact Phone

        // Inserting Row
        db.insert(TABLE_CONTACTS, null, values);
        db.close(); // Closing database connection
    }



    // Getting All Contacts
    public List<AppointmentsModel> getAllContacts() {
        List<AppointmentsModel> contactList = new ArrayList<AppointmentsModel>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
               AppointmentsModel contact = new AppointmentsModel();

                contact.setDoctor(cursor.getString(1));
                contact.setHospital(cursor.getString(2));
                contact.setDate(cursor.getString(3));
                contact.setTime(cursor.getString(4));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }

}
