package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Result extends AppCompatActivity {
    TextView NameObj, IdObj, TopicObj, PointObj;
    String Name, Id, Topic;
    int Point;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        //ID call in object
        NameObj = findViewById(R.id.NameID);
        IdObj = findViewById(R.id.IdID);
        TopicObj = findViewById(R.id.TopicID);
        PointObj = findViewById(R.id.PointID);


        //Shared Preference Load for input name id
        SharedPreferences SP = getSharedPreferences("MyFile", Context.MODE_PRIVATE);
        Name = SP.getString("NameKey", "Name not found.");
        Id = SP.getString("IdKey", "ID not found.");
        Topic = SP.getString("TopicKey", "Topic not found.");



        //Get intent for Topic, Point
        if (Topic.equals("Data Mining"))
        {
            Intent point = getIntent();
            Point = point.getIntExtra(Topic1_Q5.Topic1Q5Point, 0);
        }

        else if (Topic.equals("Pervasive Computing"))
        {
            Intent point = getIntent();
            Point = point.getIntExtra(Topic2_Q5.Topic1Q5Point, 0);
        }
        else if (Topic.equals("Compiler Design"))
        {
            Intent point = getIntent();
            Point = point.getIntExtra(Topic3_Q5.Topic1Q5Point, 0);
        }




        //set the value
        NameObj.setText("Welcome! "+Name);
        IdObj.setText("ID: "+Id);
        TopicObj.setText(""+Topic);
        PointObj.setText("Point: "+Point+" out of 10");
    }

    public void HomeButtonFunction(View view)
    {

        //Shared Preference Saved for Recent Result
        SharedPreferences MySP = getSharedPreferences("MyFile", Context.MODE_PRIVATE);
        SharedPreferences.Editor Edtr = MySP.edit();
        Edtr.putString("rNameKey", Name);
        Edtr.putString("rIdKey", Id);
        Edtr.putString("rTopicKey", Topic);
        Edtr.putInt("rPointKey", Point);
        Edtr.apply(); //or Edtr.commit();



        Intent GoHome = new Intent(this, HomeActivity.class);
        startActivity(GoHome);



        // Inside Result class ( Create instance DatabaseHelper)
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        //Get permission for write (insert, update, delete) data in the database
        SQLiteDatabase db = dbHelper.getWritableDatabase();


        // Insert data into the database (Hold data in a variable)
        ContentValues values = new ContentValues();
        values.put("name", Name);
        values.put("student_id", Id);
        values.put("topic", Topic);
        values.put("point", Point);

        // Insert the data into the 'quiz_results' table (by holding variable)
        long newRowId = db.insert("quiz_results", null, values);

        // Invoke the deleteOldEntries method
        dbHelper.deleteOldEntries();

        // Close the database connection
        db.close();




    }
}