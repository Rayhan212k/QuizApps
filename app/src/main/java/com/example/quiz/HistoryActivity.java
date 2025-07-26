package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

// Inside HistoryActivity class
public class HistoryActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    TextView backResulticonObj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        dbHelper = new DatabaseHelper(this);

        // Retrieve and display the last 10 entries
        displayHistory();

        // Initialize backHomeiconObj
        backResulticonObj = findViewById(R.id.backResulticonID);


        // Set an OnClickListener for the backHomeiconObj
        backResulticonObj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigate to HomeActivity when the TextView is clicked
                Intent home1Intent = new Intent(HistoryActivity.this, HomeActivity.class);
                startActivity(home1Intent);
            }
        });
    }

    private void displayHistory() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // Query the database for the last 10 entries
        String query = "SELECT * FROM quiz_results ORDER BY id DESC LIMIT 10";
        Cursor cursor = db.rawQuery(query, null);

        LinearLayout historyContainer = findViewById(R.id.historyContainerID);
        // Clear existing views
        historyContainer.removeAllViews();

        // Iterate through the cursor to retrieve and display entries
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
            String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
            String studentId = cursor.getString(cursor.getColumnIndexOrThrow("student_id"));
            String topic = cursor.getString(cursor.getColumnIndexOrThrow("topic"));
            int point = cursor.getInt(cursor.getColumnIndexOrThrow("point"));

            // Create a TextView for each entry and set history
            TextView entryTextView = new TextView(this);
            entryTextView.setText("" + id + "\nName: " + name + "\nID: " + studentId + "\nTopic: " + topic + "\nPoint: " + point + "\n");
            entryTextView.setTextSize(16);

            // Add the TextView to the LinearLayout
            historyContainer.addView(entryTextView);
        }

        cursor.close();
        db.close();
    }
}
