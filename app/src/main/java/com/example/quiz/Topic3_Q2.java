package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

public class Topic3_Q2 extends AppCompatActivity {
    RadioGroup Topic1Q2Obj;
    int Topic1Q2Mark = 0;
    static final String Topic1Q2Point = "Topic1Question3Marks";

    private TextView timerTextView;
    private CountDownTimer countDownTimer;
    private long timeLeftInMillis = 15000; // 15 seconds in milliseconds
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic3_q2);

        Topic1Q2Obj = findViewById(R.id.Topic1Q2ID);

        // Get intent from Q1
        Intent Topic1Q2Receive = getIntent();
        Topic1Q2Mark = Topic1Q2Receive.getIntExtra(Topic1_Q1.Topic1Q1Point,0);

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
                Topic1Q2NextButtonFunction(findViewById(R.id.Topic1Q2NextButtonFunctionID));
            }
        }.start();
    }

    // Method to update the countdown timer text
    private void updateCountdownText() {
        int seconds = (int) (timeLeftInMillis / 1000);
        timerTextView.setText("" + seconds + " s");
    }










    public void Topic1Q2NextButtonFunction(View view)
    {



        // Stop the countdown timer
        countDownTimer.cancel();


        //Calculating for Question1 & Question2 Mark
        if (Topic1Q2Obj.getCheckedRadioButtonId()==R.id.Topic1Q2dID)
        {
            Topic1Q2Mark += 2;
        }
        else
        {
            Topic1Q2Mark += 0;
        }

        // Sent intent into Q3
        Intent Topic1Q2Sent = new Intent(this, Topic3_Q3.class);
        Topic1Q2Sent.putExtra(Topic1Q2Point, Topic1Q2Mark);
        startActivity(Topic1Q2Sent);
    }
}