package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Topics extends AppCompatActivity {
    TextView TopicUserNameObj, TopicUserIdObj, TopicNameObj;
    TextView backHomeiconObj;
    String TopicUserName, TopicUserId, Topics;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topics);

        //ID call
        TopicUserNameObj = findViewById(R.id.TopicUserNameID);
        TopicUserIdObj = findViewById(R.id.TopicUserIdID);
        TopicNameObj = findViewById(R.id.TopicNameID);

        // Initialize backHomeiconObj
        backHomeiconObj = findViewById(R.id.backHomeiconID);

        // Set an OnClickListener for the backHomeiconObj
        backHomeiconObj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigate to HomeActivity when the TextView is clicked
                Intent homeIntent = new Intent(Topics.this, HomeActivity.class);
                startActivity(homeIntent);
            }
        });


        //Get intent for Topic
        Intent GetTopic = getIntent();
        Topics = GetTopic.getStringExtra(HomeActivity.Topic);


        //Shared Preference Load
        SharedPreferences SP = getSharedPreferences("MyFile", Context.MODE_PRIVATE);
        TopicUserName = SP.getString("NameKey", "Name not found.");
        TopicUserId = SP.getString("IdKey", "ID not found.");
        Topics = SP.getString("TopicKey", "Topic not found.");


        //Set the Name & Id & Topics
        TopicUserNameObj.setText("Hi! "+TopicUserName);
        TopicUserIdObj.setText("ID: "+TopicUserId);
        TopicNameObj.setText(""+Topics);
    }

    public void TopicStartButtonFunction(View view)
    {
        // Condition for select and going which page
        if (Topics.equals("Data Mining"))
        {
            Intent Topic1Sent = new Intent(this, Topic1_Q1.class);
            startActivity(Topic1Sent);
        }

        else if (Topics.equals("Pervasive Computing"))
        {
            Intent Topic2Sent = new Intent(this, Topic2_Q1.class);
            startActivity(Topic2Sent);
        }

        else if (Topics.equals("Compiler Design"))
        {
            Intent Topic3Sent = new Intent(this, Topic3_Q1.class);
            startActivity(Topic3Sent);
        }
    }
}