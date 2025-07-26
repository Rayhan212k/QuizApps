package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

public class Topic2_Q3 extends AppCompatActivity {
    RadioGroup Topic1Q3Obj;
    int Topic1Q3Mark = 0;
    static final String Topic1Q3Point = "Topic1Question3Marks";

    private TextView timerTextView;
    private CountDownTimer countDownTimer;
    private long timeLeftInMillis = 15000; // 15 seconds in milliseconds


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic2_q3);

        Topic1Q3Obj = findViewById(R.id.Topic1Q3ID);

        // Get intent from Q2
        Intent Topic1Q3Receive = getIntent();
        Topic1Q3Mark = Topic1Q3Receive.getIntExtra(Topic1_Q2.Topic1Q2Point,0);
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
                Topic1Q3NextButtonFunction(findViewById(R.id.Topic1Q3NextButtonFunctionID));
            }
        }.start();
    }

    // Method to update the countdown timer text
    private void updateCountdownText() {
        int seconds = (int) (timeLeftInMillis / 1000);
        timerTextView.setText("" + seconds + " s");
    }














    public void Topic1Q3NextButtonFunction(View view)
    {




        // Stop the countdown timer
        countDownTimer.cancel();


        //Calculating for Question1 & Question2 & Question3 Mark
        if (Topic1Q3Obj.getCheckedRadioButtonId()==R.id.Topic1Q3cID)
        {
            Topic1Q3Mark += 2;
        }
        else
        {
            Topic1Q3Mark += 0;
        }

        // Sent intent into Q4
        Intent Topic1Q3Sent = new Intent(this, Topic2_Q4.class);
        Topic1Q3Sent.putExtra(Topic1Q3Point, Topic1Q3Mark);
        startActivity(Topic1Q3Sent);
    }
}