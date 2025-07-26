package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

public class Topic2_Q1 extends AppCompatActivity {
    RadioGroup Topic1Q1Obj;
    int Topic1Q1Mark = 0;
    static final String Topic1Q1Point = "Topic1Question1Marks";

    private TextView timerTextView;
    private CountDownTimer countDownTimer;
    private long timeLeftInMillis = 15000; // 15 seconds in milliseconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic2_q1);
        Topic1Q1Obj = findViewById(R.id.Topic1Q1ID);

        //id call for TimerTextView
        timerTextView = findViewById(R.id.timerTextView);

        // Start the countdown timer
        startTimer();
    }

    // Method to start the countdown timer
    private void startTimer() {
        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateCountdownText();
            }

            @Override
            public void onFinish() {
                // Automatically click the button when time is up
                Topic1Q1NextButtonFunction(findViewById(R.id.Topic1Q1NextButtonFunctionID));
            }
        }.start();
    }

    // Method to update the countdown timer text
    private void updateCountdownText() {
        int seconds = (int) (timeLeftInMillis / 1000);
        timerTextView.setText("" + seconds + " s");
    }







    public void Topic1Q1NextButtonFunction(View view)
    {


        // Stop the countdown timer
        countDownTimer.cancel();



        //Calculating for Question1 Mark
        if (Topic1Q1Obj.getCheckedRadioButtonId()==R.id.Topic1Q1bID)
        {
            Topic1Q1Mark += 2;
        }
        else
        {
            Topic1Q1Mark += 0;
        }



        // Sent intent into Q2
        Intent Topic1Q1Sent = new Intent(this, Topic2_Q2.class);
        Topic1Q1Sent.putExtra(Topic1Q1Point, Topic1Q1Mark);
        startActivity(Topic1Q1Sent);
    }
}