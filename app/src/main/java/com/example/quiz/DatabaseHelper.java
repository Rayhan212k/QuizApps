package com.example.quiz;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    //Declare Database Name and Version
    private static final String DATABASE_NAME = "quiz_database";
    private static final int DATABASE_VERSION = 1;


    // Constructor
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    // Table create method in database
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create your tables here
        String createTableQuery = "CREATE TABLE quiz_results (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT," +
                "student_id TEXT," +
                "topic TEXT," +
                "point INTEGER)";
        db.execSQL(createTableQuery);
    }


    //If I want upgrade database that handle here
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Handle database upgrades if needed
    }



    // Delete entries older than the latest 10 entries
    public void deleteOldEntries() {
        SQLiteDatabase db = this.getWritableDatabase();

        // Get the count of total entries
        String countQuery = "SELECT COUNT(*) FROM quiz_results";
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.moveToFirst();
        int totalEntries = cursor.getInt(0);
        cursor.close();

        // Delete entries if more than 10
        if (totalEntries > 10) {
            int entriesToDelete = totalEntries - 10;
            String deleteQuery = "DELETE FROM quiz_results WHERE id IN (SELECT id FROM quiz_results ORDER BY id ASC LIMIT " + entriesToDelete + ")";
            db.execSQL(deleteQuery);
        }

        db.close();
    }
}
