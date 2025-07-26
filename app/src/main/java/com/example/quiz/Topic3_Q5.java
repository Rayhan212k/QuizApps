package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

public class Topic3_Q5 extends AppCompatActivity {
    RadioGroup Topic1Q5Obj;
    int Topic1Q5Mark = 0;
    //Variable declare for keeping Topic1Name
    String Topic1sent = "Data Mining";
    static final String Topic1Q5Point = "Topic1Question5Marks";
    final static String TopicNmae = "Topic Name";

    private TextView timerTextView;
    private CountDownTimer countDownTimer;
    private long timeLeftInMillis = 15000; // 15 seconds in milliseconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic3_q5);

        Topic1Q5Obj = findViewById(R.id.Topic1Q5ID);

        // Get intent from Q4
        Intent Topic1Q5Receive = getIntent();
        Topic1Q5Mark = Topic1Q5Receive.getIntExtra(Topic1_Q4.Topic1Q4Point,0);



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
                Topic1Q5NextButtonFunction(findViewById(R.id.Topic1Q5NextButtonFunctionID));
            }
        }.start();
    }

    // Method to update the countdown timer text
    private void updateCountdownText() {
        int seconds = (int) (timeLeftInMillis / 1000);
        timerTextView.setText("" + seconds + " s");
    }




    public void Topic1Q5NextButtonFunction(View view)
    {



        // Stop the countdown timer
        countDownTimer.cancel();


        //Calculating for Question1 & Question2 & Question3 & Question3 & Question5 Mark
        if (Topic1Q5Obj.getCheckedRadioButtonId()==R.id.Topic1Q5aID)
        {
            Topic1Q5Mark += 2;
        }
        else
        {
            Topic1Q5Mark += 0;
        }

        // Sent intent into Result
        Intent Topic1Q5Sent = new Intent(this, Result.class);
        Topic1Q5Sent.putExtra(Topic1Q5Point, Topic1Q5Mark);
        Topic1Q5Sent.putExtra(TopicNmae, Topic1sent);
        startActivity(Topic1Q5Sent);
    }
}