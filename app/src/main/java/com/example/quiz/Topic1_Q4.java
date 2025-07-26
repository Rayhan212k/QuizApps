package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

public class Topic1_Q4 extends AppCompatActivity {
    RadioGroup Topic1Q4Obj;
    int Topic1Q4Mark = 0;
    static final String Topic1Q4Point = "Topic1Question4Marks";

    private TextView timerTextView;
    private CountDownTimer countDownTimer;
    private long timeLeftInMillis = 15000; // 15 seconds in milliseconds


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic1_q4);

        Topic1Q4Obj = findViewById(R.id.Topic1Q4ID);

        // Get intent from Q3
        Intent Topic1Q4Receive = getIntent();
        Topic1Q4Mark = Topic1Q4Receive.getIntExtra(Topic1_Q3.Topic1Q3Point,0);


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
                Topic1Q4NextButtonFunction(findViewById(R.id.Topic1Q4NextButtonFunctionID));
            }
        }.start();
    }

    // Method to update the countdown timer text
    private void updateCountdownText() {
        int seconds = (int) (timeLeftInMillis / 1000);
        timerTextView.setText("" + seconds + " s");
    }









    public void Topic1Q4NextButtonFunction(View view)
    {



        // Stop the countdown timer
        countDownTimer.cancel();


        //Calculating for Question1 & Question2 & Question3 & Question3 Mark
        if (Topic1Q4Obj.getCheckedRadioButtonId()==R.id.Topic1Q4cID)
        {
            Topic1Q4Mark += 2;
        }
        else
        {
            Topic1Q4Mark += 0;
        }

        // Sent intent into Q5
        Intent Topic1Q4Sent = new Intent(this, Topic1_Q5.class);
        Topic1Q4Sent.putExtra(Topic1Q4Point, Topic1Q4Mark);
        startActivity(Topic1Q4Sent);
    }
}