package com.example.quiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    // For nav menu
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    ImageView imageMenu;
    ImageView imagebackMenuMenu;



    //For home page
    Spinner DropDownObj;
    EditText UserNameObj, UserIdObj;
    String TopicSelect, UserId, UserName;
    static final String Topic = "Topics";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // ID call for home page
        DropDownObj = findViewById(R.id.TopicSelectDropDownID);
        UserNameObj = findViewById(R.id.UserNameID);
        UserIdObj = findViewById(R.id.UserIdID);

        //Spinner or DropDown
        ArrayAdapter<CharSequence>SelectAdapter= ArrayAdapter.createFromResource
                (this,R.array.DropDownValues, android.R.layout.simple_spinner_item);
        SelectAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        DropDownObj.setAdapter(SelectAdapter);
        DropDownObj.setOnItemSelectedListener(this);




        // Navagation Drawar------------------------------
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_View);
        imageMenu = findViewById(R.id.imageMenu);

        toggle = new ActionBarDrawerToggle(HomeActivity.this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Drawar click event
        // Drawer item Click event ------
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int itemId = item.getItemId();

                if (itemId == R.id.about)
                {
                    Intent AboutIntent = new Intent(HomeActivity.this, Admin_Rayhan_Profile.class);
                    startActivity(AboutIntent);
                }
                else if (itemId == R.id.RecentResult)
                {
                    //Shared Preference Load for result name id topic result
                    SharedPreferences MySP = getSharedPreferences("MyFile", Context.MODE_PRIVATE);
                    String  Name = MySP.getString("rNameKey", "Name not found.");
                    String Id = MySP.getString("rIdKey", "ID not found.");
                    String Topic = MySP.getString("rTopicKey", "Topic not found");
                    Integer Point = MySP.getInt("rPointKey",0);

                    Toast.makeText(HomeActivity.this, "Name: "+Name+". ID: "+Id+". Topic: "+Topic+". Point: "+Point+".", Toast.LENGTH_LONG).show();
                    drawerLayout.closeDrawers();
                }
                else if (itemId == R.id.AllResult)
                {
                    Intent AllResultIntent = new Intent(HomeActivity.this, HistoryActivity.class);
                    startActivity(AllResultIntent);
                }
                else if (itemId == R.id.share)
                {
                    Toast.makeText(HomeActivity.this, "You clicked Share Section", Toast.LENGTH_SHORT).show();
                    drawerLayout.closeDrawers();
                }
                else if (itemId == R.id.RateUs)
                {
                    Toast.makeText(HomeActivity.this, "You clicked Rate Us Section", Toast.LENGTH_SHORT).show();
                    drawerLayout.closeDrawers();
                }
                else if (itemId == R.id.LogOut)
                {
                    Intent LogOutIntent = new Intent(HomeActivity.this, MainActivity.class);
                    startActivity(LogOutIntent);
                }


                return false;
            }
        });
        //------------------------------

        // ------------------------
        // App Bar Click Event for menu open
        imageMenu = findViewById(R.id.imageMenu);

        imageMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        // App Bar Click Event from menu close
        // Assuming header.xml is in the layout folder

        View headerView = navigationView.getHeaderView(0); // 0 index assumes you have only one header
        imagebackMenuMenu = headerView.findViewById(R.id.image_back_icon_menu);

        imagebackMenuMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });

    }




    ////AdapterView OnItemSelectedListener of Spinner or DropDown
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        TopicSelect = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }




    //Button Function
    public void HomePageNextButtonFunction(View view) {
        // Get user Name & Id
        UserName = UserNameObj.getText().toString();
        UserId = UserIdObj.getText().toString();

        //Shared Preference Saved for input name id
        SharedPreferences SP = getSharedPreferences("MyFile", Context.MODE_PRIVATE);
        SharedPreferences.Editor Edtr = SP.edit();
        Edtr.putString("NameKey", UserName);
        Edtr.putString("IdKey", UserId);
        Edtr.putString("TopicKey", TopicSelect);
        Edtr.apply(); //or Edtr.commit();


        // Condition for Name or Id empty and pop up a message
        if (UserName.isEmpty())
        {
            Toast.makeText(this, "Please give your Name!", Toast.LENGTH_SHORT).show();
        }
        else if (UserId.isEmpty())
        {
            Toast.makeText(this, "Please give your ID!", Toast.LENGTH_SHORT).show();
        }
        else
        {
            // Going to topic initial page
            if ((TopicSelect.equals("Data Mining")) || (TopicSelect.equals("Pervasive Computing")) || (TopicSelect.equals("Compiler Design")))
            {
                Intent TopicSent = new Intent(this, Topics.class);
                TopicSent.putExtra(Topic, TopicSelect);
                startActivity(TopicSent);
            }

            else if (TopicSelect.equals("Select"))
            {
                Toast.makeText(this, "Please select the topic!", Toast.LENGTH_SHORT).show();
            }
        }

    }
}