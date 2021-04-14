package com.example.ta_pam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class activity_workout_time extends AppCompatActivity {

    private Button minutes5, minutes15, minutes20, minutes25, minutes30, minutes45 ;
    private ImageButton btnHome, btnHistory;
    private TextView introText ;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_time);
        btnHistory = findViewById(R.id.btnHistory);
        btnHome = findViewById(R.id.btnHome);

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(activity_workout_time.this, MainActivity.class);
                startActivity(i);
            }
        });

        btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(activity_workout_time.this, activity_history.class);
                startActivity(i);
            }
        });


        sharedPreferences = getSharedPreferences("Setting", Context.MODE_PRIVATE);

        String weightOld = sharedPreferences.getString("weightField", "0");
        String heightOld = sharedPreferences.getString("heightField", "0");

        String kategori = userService.getKategori(userService.getBMIIndex(weightOld,heightOld));

        introText = findViewById(R.id.introText);
        introText.setText("We will match the workout for people with " + kategori);

        minutes5 = findViewById(R.id.minutes5);
        minutes15 = findViewById(R.id.minutes15);
        minutes20 = findViewById(R.id.minutes20);
        minutes25 = findViewById(R.id.minutes25);
        minutes30 = findViewById(R.id.minutes30);
        minutes45 = findViewById(R.id.minutes45);
        introText = findViewById(R.id.introText);

        minutes5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(activity_workout_time.this, activity_workout_preview.class);
                i.putExtra("minutes", "5");
                i.putExtra("kategori", kategori);
                startActivity(i);
            }
        });

        minutes15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(activity_workout_time.this, activity_workout_preview.class);
                i.putExtra("minutes", "15");
                i.putExtra("kategori", kategori);
                startActivity(i);
            }
        });

        minutes20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(activity_workout_time.this, activity_workout_preview.class);
                i.putExtra("minutes", "20");
                i.putExtra("kategori", kategori);
                startActivity(i);
            }
        });

        minutes25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(activity_workout_time.this, activity_workout_preview.class);
                i.putExtra("minutes", "25");
                i.putExtra("kategori", kategori);
                startActivity(i);
            }
        });

        minutes30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(activity_workout_time.this, activity_workout_preview.class);
                i.putExtra("minutes", "30");
                i.putExtra("kategori", kategori);
                startActivity(i);
            }
        });

        minutes45.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(activity_workout_time.this, activity_workout_preview.class);
                i.putExtra("minutes", "45");
                i.putExtra("kategori", kategori);
                startActivity(i);
            }
        });
    }


}