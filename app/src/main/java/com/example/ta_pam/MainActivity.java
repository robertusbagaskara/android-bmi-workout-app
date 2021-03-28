package com.example.ta_pam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private LinearLayout layoutHitung, layoutHasil;
    private ImageView btnProfile;
    private ImageButton btnHome, btnHistory;
    private EditText weightField, heightField;
    private TextView heloText, heightHasilText, weightHasilText, indexText, kategoriText, kaloriText;
    private Button btnHitung, btnUlangi, btnUpdate, btnWorkout, btnResep, btnTips;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layoutHitung = (LinearLayout) findViewById(R.id.layoutHitung);
        layoutHasil = (LinearLayout) findViewById(R.id.layoutHasil);
        btnProfile = findViewById(R.id.btnProfile);
        btnHome = findViewById(R.id.btnHome);
        btnHistory = findViewById(R.id.btnHistory);
        weightField = findViewById(R.id.weightField);
        heightField = findViewById(R.id.heightField);
        heloText = findViewById(R.id.userHalo);
        heightHasilText = findViewById(R.id.heightHasil);
        weightHasilText = findViewById(R.id.weightHasil);
        indexText = findViewById(R.id.indexText);
        kategoriText = findViewById(R.id.kategoriText);
        kaloriText = findViewById(R.id.kaloriText);
        btnHitung = findViewById(R.id.btnHitung);
        btnUlangi = findViewById(R.id.btnUlangi);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnWorkout = findViewById(R.id.btnWorkout);
        btnTips = findViewById(R.id.btnTips);
        btnResep = findViewById(R.id.btnResep);

        layoutHasil.setVisibility(View.GONE);
        sharedPreferences = getSharedPreferences("Setting", Context.MODE_PRIVATE);
        String userName = sharedPreferences.getString("nameField", "User");
        heloText.setText("Halo, " + userName);
        weightField.setText(sharedPreferences.getString("weightField", "0"));
        heightField.setText(sharedPreferences.getString("heightField", "0"));

        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, profile_page.class);
                startActivity(i);
            }
        });

        btnHitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputBerat = weightField.getText().toString().trim();
                String inputTinggi = heightField.getText().toString().trim();
                boolean isError = !isValidInput(inputBerat,inputTinggi);
                if (isError && userName.equalsIgnoreCase("User")){
                    Toast.makeText(getBaseContext(),"Mohon update profile anda terlebih dahulu",Toast.LENGTH_LONG).show();
                }
                if (!isError){
                    double BMIIndex = getBMIIndex(inputBerat,inputTinggi);
                    String kategori = "IDEAL";
                    if (BMIIndex >= 25.1) {
                        kategori = "OVERWEIGHT";
                    } else if (BMIIndex < 18.5) {
                        kategori = "UNDERWEIGHT";
                    }
                    System.out.println("BMIINDEX " + BMIIndex);
                    layoutHitung.setVisibility(View.GONE);
                    layoutHasil.setVisibility(View.VISIBLE);
                    weightHasilText.setText(inputBerat+" kg");
                    heightHasilText.setText(inputTinggi+" cm");
                    indexText.setText(String.format("%.2f", BMIIndex));
                    kategoriText.setText(kategori);
                    kaloriText.setText(String.format("%.2f", getKalori(inputBerat,inputTinggi)));
                }
            }
        });

        btnUlangi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutHasil.setVisibility(View.GONE);
                layoutHitung.setVisibility(View.VISIBLE);
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("weightField",weightField.getText().toString());
                editor.putString("heightField",heightField.getText().toString());
                editor.apply();
                Toast.makeText(getBaseContext(),"Berat Badan dan Tinggi Badan berhasil diupdate",Toast.LENGTH_LONG).show();
                layoutHasil.setVisibility(View.GONE);
                layoutHitung.setVisibility(View.VISIBLE);
            }
        });

    }

    private boolean isValidInput(String inputBerat, String inputTinggi){
        boolean isError = false;
        if (TextUtils.isEmpty(inputBerat) || inputBerat.equals("0")) {
            isError = true;
            weightField.setError("Invalid Input");
        }

        if (TextUtils.isEmpty(inputTinggi) || inputTinggi.equals("0")) {
            isError = true;
            heightField.setError("Invalid Input");
        }
        return !isError;
    }

    private double getBMIIndex(String inputBerat, String inputTinggi){
        double bmi = Double.valueOf(inputBerat) / (Double.valueOf(inputTinggi) * Double.valueOf(inputTinggi) / 10000);
        return bmi;
    }

    private Float getKalori(String inputBerat, String inputTinggi){
//        BMR Pria = 66,5 + (13,7 × berat badan) + (5 × tinggi badan) – (6,8 × usia)
//        BMR Wanita = 655 + (9,6 × berat badan) + (1,8 × tinggi badan) – (4,7 × usia)
//      kalori = bmr * 1.3

        float kalori = 0;
        String gender = sharedPreferences.getString("genderField", "Male");
        if (gender.equalsIgnoreCase("Male")) {
            kalori = (float) ((66.5 + (13.7 * Float.valueOf(inputBerat)) + (5 * Float.valueOf(inputTinggi)) - ( 6.8 * getAge()))*1.3);
        }else {
            kalori = (float) ((655 + (9.6 * Float.valueOf(inputBerat)) + (1.8 * Float.valueOf(inputTinggi)) - ( 4.7 * getAge()))*1.3);
        }

        return kalori;
    }

    private Float getAge(){
        Calendar newCalendar = Calendar.getInstance();
        String dateOfBirth = sharedPreferences.getString("bornField", "01-01-2000");
        System.out.println("DATEEE " + dateOfBirth);
        int currentYear = newCalendar.get(Calendar.YEAR);
        int currentMonth = newCalendar.get(Calendar.MONTH) + 1;
        int currentDay = newCalendar.get(Calendar.DAY_OF_MONTH);

        String[] dateOfBirthArray = dateOfBirth.split("-");
        int day = Integer.valueOf(dateOfBirthArray[0]);
        int month = Integer.valueOf(dateOfBirthArray[1]);
        int year = Integer.valueOf(dateOfBirthArray[2]);

        int age = currentYear - year - 1;
        if ((currentMonth> month) || (currentMonth==month && currentDay>=day)){
            age +=1 ;
        }
        return Float.valueOf(age);
    }


}