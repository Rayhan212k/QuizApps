package com.example.quiz;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity {
    EditText GmailAddressObj, PasswordObj;
    String GmailAddress, Password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GmailAddressObj = findViewById(R.id.GmailAddressID);
        PasswordObj = findViewById(R.id.PasswordID);
    }
    public void LoginButtonFunction(View view) {
        GmailAddress = GmailAddressObj.getText().toString();
        Password = PasswordObj.getText().toString();
        if (GmailAddress.equals("rayhan@gmail.com") & Password.equals("rt666MR") || GmailAddress.equals("01941869434") & Password.equals("rt666MR"))
        {
            Intent login = new Intent(this, HomeActivity.class);
            startActivity(login);
        }
        else
        {
            if (GmailAddress.isEmpty())
            {
                Toast.makeText(this, "Please enter Gmail or Phone.", Toast.LENGTH_LONG).show();
            }
            else if (Password.isEmpty())
            {
                Toast.makeText(this, "Please enter Password.", Toast.LENGTH_LONG).show();
            }
            else if (!(GmailAddress.equals("rayhan@gmail.com") || GmailAddress.equals("01941869434")))
            {
                Toast.makeText(this, "Wrong Gmail address or Phone. Please enter valid Gmail or Phone.", Toast.LENGTH_LONG).show();
            }
            else
            {
                Toast.makeText(this, "Wrong Password. Please enter valid Password.", Toast.LENGTH_LONG).show();
            }
        }
    }


    public void openSignUpActivity(View view) {
        Intent AdminIntent = new Intent(MainActivity.this, Admin_Rayhan_Profile.class);
        startActivity(AdminIntent);
    }
}