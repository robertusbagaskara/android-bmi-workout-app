package com.example.ta_pam;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class profile_page extends AppCompatActivity {

    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    private SimpleDateFormat dateFormatter;
    private EditText nameField, bornField, reminderField, weightField, heightField;
    private Spinner genderField ;
    private Button btnCancel, btnUpdate;
    private ImageButton btnHome, btnHistory;
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        nameField = findViewById(R.id.nameField);
        genderField = findViewById(R.id.genderField);
        bornField = findViewById(R.id.bornField);
        reminderField = findViewById(R.id.reminderField);
        weightField = findViewById(R.id.weightField);
        heightField = findViewById(R.id.heightField);
        btnCancel = findViewById(R.id.btnCancel);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnHistory = findViewById(R.id.btnHistory);
        btnHome = findViewById(R.id.btnHome);

        sharedPreferences = getSharedPreferences("Setting", Context.MODE_PRIVATE);
        loadContent();

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

        bornField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog();
            }
        });

        reminderField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimeDialog();
            }
        });


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("nameField",nameField.getText().toString());
                editor.putString("genderField",genderField.getSelectedItem().toString());
                editor.putString("bornField",bornField.getText().toString());
                editor.putString("reminderField",reminderField.getText().toString());
                editor.putString("weightField",weightField.getText().toString());
                editor.putString("heightField",heightField.getText().toString());
                editor.apply();
                Toast.makeText(getBaseContext(),"Profil berhasil diupdate",Toast.LENGTH_LONG).show();
                Intent i = new Intent(profile_page.this, MainActivity.class);
                startActivity(i);
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadContent();
            }
        });

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(profile_page.this, MainActivity.class);
                startActivity(i);
            }
        });

//        btnHistory.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(profile_page.this, history_page.class);
//                startActivity(i);
//            }
//        });

    }

    private void loadContent(){
        nameField.setText(sharedPreferences.getString("nameField", "User"));
        String gender = sharedPreferences.getString("genderField", "Male");
        int index = gender.equalsIgnoreCase("Male") ? 0 : 1;
        genderField.setSelection(index);
        bornField.setText(sharedPreferences.getString("bornField", "01-01-2000"));
        reminderField.setText(sharedPreferences.getString("reminderField", "15:00"));
        weightField.setText(sharedPreferences.getString("weightField", "0"));
        heightField.setText(sharedPreferences.getString("heightField", "0"));
    }

    private void showDateDialog(){
        String date = sharedPreferences.getString("bornField", "01-01-2000");
        String[] dateArray= date.split("-");
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                bornField.setText(dateFormatter.format(newDate.getTime()));
            }
        } , Integer.valueOf(dateArray[2]), Integer.valueOf(dateArray[1])-1, Integer.valueOf(dateArray[0]));

        datePickerDialog.show();
    }

    private void showTimeDialog() {

        String time = sharedPreferences.getString("reminderField", "15:00");
        String[] timearray= time.split(":");
        timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute){
                String minute_modified = String.valueOf(minute);
                if (minute==0){
                    minute_modified="00";
                }
                System.out.println("MINUTE "+ minute);
                System.out.println("MINUTE MODIFIED " + minute_modified);
                reminderField.setText(hourOfDay+":"+minute_modified);
            }
        }, Integer.valueOf(timearray[0]), Integer.valueOf(timearray[1]), DateFormat.is24HourFormat(this));

        timePickerDialog.show();
    }

}