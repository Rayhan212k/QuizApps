package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Admin_Rayhan_Profile extends AppCompatActivity {

    TextView backHomeIconObj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_rayhan_profile);

        // Initialize backHomeiconObj
        backHomeIconObj = findViewById(R.id.backHomeIconID);

        // Set an OnClickListener for the backHomeIconObj
        backHomeIconObj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigate to HomeActivity when the TextView is clicked
                Intent mainIntent = new Intent(Admin_Rayhan_Profile.this, MainActivity.class);
                startActivity(mainIntent);
            }
        });
    }
}