package com.example.ta_pam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class activity_workout_preview extends AppCompatActivity {

    private ImageButton btnHome, btnHistory, btnBack;
    private TextView totalKalori, jumlahWorkout, waktuWorkout, workoutOneTitle, workoutOneTime, workoutTwoTitle, workoutTwoTime, workoutThreeTitle, workoutThreeTime,
            workoutFourTitle, workoutFourTime, workoutFiveTitle, workoutFiveTime, workoutOneKalori, workoutTwoKalori, workoutThreeKalori, workoutFourKalori, workoutFiveKalori;
    private ImageView workoutOneImage, workoutTwoImage, workoutThreeImage, workoutFourImage, workoutFiveImage,
            workoutOneLine, workoutTwoLine, workoutThreeLine, workoutFourLine, workoutFiveLine;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_preview);
        btnHistory = findViewById(R.id.btnHistory);
        btnHome = findViewById(R.id.btnHome);
        jumlahWorkout = findViewById(R.id.jumlahWorkout);
        waktuWorkout = findViewById(R.id.waktuWorkout);
        workoutOneTitle = findViewById(R.id.workoutOneTitle);
        workoutOneTime = findViewById(R.id.workoutOneTime);
        workoutTwoTitle = findViewById(R.id.workoutTwoTitle);
        workoutTwoTime = findViewById(R.id.workoutTwoTime);
        workoutThreeTitle = findViewById(R.id.workoutThreeTitle);
        workoutThreeTime = findViewById(R.id.workoutThreeTime);
        workoutFourTitle = findViewById(R.id.workoutFourTitle);
        workoutFourTime = findViewById(R.id.workoutFourTime);
        workoutFiveTitle = findViewById(R.id.workoutFiveTitle);
        workoutFiveTime = findViewById(R.id.workoutFiveTime);
        workoutOneImage = findViewById(R.id.workoutOneImage);
        workoutTwoImage = findViewById(R.id.workoutTwoImage);
        workoutThreeImage = findViewById(R.id.workoutThreeImage);
        workoutFourImage = findViewById(R.id.workoutFourImage);
        workoutFiveImage = findViewById(R.id.workoutFiveImage);
        workoutOneLine = findViewById(R.id.workoutOneLine);
        workoutTwoLine = findViewById(R.id.workoutTwoLine);
        workoutThreeLine = findViewById(R.id.workoutThreeLine);
        workoutFourLine = findViewById(R.id.workoutFourLine);
        workoutFiveLine = findViewById(R.id.workoutFiveLine);
        workoutOneKalori = findViewById(R.id.workoutOneKalori);
        workoutTwoKalori = findViewById(R.id.workoutTwoKalori);
        workoutThreeKalori = findViewById(R.id.workoutThreeKalori);
        workoutFourKalori = findViewById(R.id.workoutFourKalori);
        workoutFiveKalori = findViewById(R.id.workoutFiveKalori);
        totalKalori = findViewById(R.id.totalKalori);
        btnBack = findViewById(R.id.btnBack);

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(activity_workout_preview.this, MainActivity.class);
                startActivity(i);
            }
        });

        btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(activity_workout_preview.this, activity_history.class);
                startActivity(i);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(activity_workout_preview.this, activity_workout_time.class);
                startActivity(i);
            }
        });

        sharedPreferences = getSharedPreferences("Setting", Context.MODE_PRIVATE);

        String weightOld = sharedPreferences.getString("weightField", "0");

        String kategori = getIntent().getStringExtra("kategori");
        System.out.println(kategori);
        String minute = getIntent().getStringExtra("minutes");
        int minutes = Integer.valueOf(minute);

        String[] workoutNameUnderweight = {"Jumping Rope", "Squat", "Sit Up", "Run In Place", "Lunges"};
        int[] workoutImageUnderweight = {R.drawable.jumpingrope, R.drawable.squats, R.drawable.sit_up, R.drawable.run_in_place, R.drawable.lunges};

        String[] workoutNameIdeal = {"Bridge", "Stationary Lunge", "Yoga", "Squat", "Push Up"};
        int[] workoutImageIdeal = {R.drawable.bridge, R.drawable.lunges, R.drawable.yoga, R.drawable.squats, R.drawable.push_up};


        String[] workoutNameOverweight = {"Push Up", "Sit Up", "Squat", "Lunges", "Latihan Beban"};
        int[] workoutImageOverweight = {R.drawable.push_up, R.drawable.sit_up, R.drawable.squats, R.drawable.lunges, R.drawable.angkat_beban};

        double[] multiplyUnderweight = {11.8, 5.5, 8.0, 9.8, 4.0};
        double[] multiplyIdeal = {6.0, 4.0, 3.1, 5.5, 8.0};
        double[] multiplyOverweight = {8.0, 8.0, 5.5, 4.0, 6.2};

        TextView[] workoutTitle = {workoutOneTitle, workoutTwoTitle, workoutThreeTitle, workoutFourTitle, workoutFiveTitle};
        TextView[] workoutTime = {workoutOneTime, workoutTwoTime, workoutThreeTime, workoutFourTime, workoutFiveTime};
        ImageView[] workoutImage = {workoutOneImage, workoutTwoImage, workoutThreeImage, workoutFourImage, workoutFiveImage};
        ImageView[] workoutLine = {workoutOneLine, workoutTwoLine, workoutThreeLine, workoutFourLine, workoutFiveLine};
        TextView[] workoutKalori = {workoutOneKalori, workoutTwoKalori, workoutThreeKalori, workoutFourKalori, workoutFiveKalori};

        for (int i = 0; i< 5; i++){
            workoutTitle[i].setVisibility(View.VISIBLE);
            workoutTime[i].setVisibility(View.VISIBLE);
            workoutImage[i].setVisibility(View.VISIBLE);
            workoutLine[i].setVisibility(View.VISIBLE);
            workoutKalori[i].setVisibility(View.VISIBLE);
        }

        int[] timeSplit;

        if (minutes == 5) {
            timeSplit = new int[] {2,1,2} ;
        } else if (minutes == 15) {
            timeSplit = new int[] {5,5,5} ;
        } else if (minutes == 20) {
            timeSplit = new int[] {5,5,5,5} ;
        } else if (minutes == 25) {
            timeSplit = new int[] {5,5,5,5,5} ;
        } else if (minutes == 30) {
            timeSplit = new int[] {6,6,6,6,6} ;
        } else {
            timeSplit = new int[] {9,9,9,9,9} ;
        }

        ArrayList<Integer> tempIndex = new ArrayList<Integer>();
        double burnedKalori = 0.0 ;
        double tempKalori = 0.0;

        for (int i = 0; i< timeSplit.length ; i++){

            Random rand = new Random();
            int n = rand.nextInt(5);
            while (tempIndex.contains(n)) {
                n = rand.nextInt(5);
                System.out.println(n);
                System.out.println(tempIndex);
            }
            tempIndex.add(n);

            if (kategori.equalsIgnoreCase("UNDERWEIGHT")){
                workoutTitle[i].setText(workoutNameUnderweight[n]);
                tempKalori = Double.valueOf(weightOld)* multiplyUnderweight[n] * 0.0175 * Double.valueOf(timeSplit[i]);
                workoutImage[i].setImageResource(workoutImageUnderweight[n]);
            } else if (kategori.equalsIgnoreCase("IDEAL")){
                workoutTitle[i].setText(workoutNameIdeal[n]);
                tempKalori = Double.valueOf(weightOld)* multiplyIdeal[n] * 0.0175 * Double.valueOf(timeSplit[i]);
                System.out.println("TRYYY");
                System.out.println(tempKalori);
                System.out.println(n + "   " + multiplyIdeal[n]);
                workoutImage[i].setImageResource(workoutImageIdeal[n]);
            } else {
                workoutTitle[i].setText(workoutNameOverweight[n]);
                tempKalori =  Double.valueOf(weightOld)* multiplyOverweight[n] * 0.0175 * Double.valueOf(timeSplit[i]);
                workoutImage[i].setImageResource(workoutImageOverweight[n]);
            }
            workoutTime[i].setText(String.valueOf(timeSplit[i])+ " minutes");
            workoutKalori[i].setText(String.format("%.2f",tempKalori) + " calories");
            burnedKalori += tempKalori ;

        }

        jumlahWorkout.setText(String.valueOf(timeSplit.length));
        waktuWorkout.setText(minute);
        totalKalori.setText("Total calories burned = " + String.format("%.2f",burnedKalori));

        if (timeSplit.length < 5) {
            for (int i = timeSplit.length ; i< 5; i++){
                    workoutTitle[i].setVisibility(View.GONE);
                    workoutTime[i].setVisibility(View.GONE);
                    workoutImage[i].setVisibility(View.GONE);
                    workoutLine[i].setVisibility(View.GONE);
                    workoutKalori[i].setVisibility(View.GONE);
            }
        }


    }

}