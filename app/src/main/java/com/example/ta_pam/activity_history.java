package com.example.ta_pam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.HashMap;
import java.util.ArrayList;

public class activity_history extends AppCompatActivity {

    String[] daftar;
    private ListView lv;
    protected Cursor cursor;
    DataHelper dbcenter;
    public static activity_history activityHistory;
    ArrayList<HashMap<String, String>> historyList;
    private ImageButton btnHome, btnHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        btnHome = findViewById(R.id.btnHome);
        btnHistory = findViewById(R.id.btnHistory);

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(activity_history.this, MainActivity.class);
                startActivity(i);
            }
        });


        dbcenter = new DataHelper(this);

        refreshData();
    }

    public void refreshData(){
        SQLiteDatabase db = dbcenter.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM history", null);

        historyList = new ArrayList<>();
        daftar = new String[cursor.getCount()];

        cursor.moveToFirst();
        for (int i =0; i< cursor.getCount(); i++){
            cursor.moveToPosition(i);
            HashMap<String, String> history = new HashMap<>();
            String tinggi = cursor.getString(0).toString();
            String berat = cursor.getString(1).toString();
            String kalori = cursor.getString(2).toString();
            String tanggal = cursor.getString(3).toString();
            Double BMIIndex = getBMIIndex(berat, tinggi);
            String kategori = getKategori(BMIIndex);
            System.out.println(kategori+tanggal+tinggi+berat);
            history.put("kategori", kategori);
            history.put("tanggal", tanggal);
            history.put("tinggi", tinggi);
            history.put("berat", berat);
            history.put("bmi", String.format("%.2f", BMIIndex));
            history.put("kalori", kalori);

            historyList.add(history);
        }

        lv = (ListView) findViewById(R.id.list);
        ListAdapter adapter = new SimpleAdapter(activity_history.this, historyList,
                R.layout.activity_history_cell, new String[]{ "kategori","tanggal", "tinggi", "berat", "bmi", "kalori"},
                new int[]{R.id.kategori, R.id.tanggal, R.id.tinggi, R.id.berat, R.id.bmi, R.id.kalori});
        lv.setAdapter(adapter);


    }
    private double getBMIIndex(String inputBerat, String inputTinggi){
        double bmi = Double.valueOf(inputBerat) / (Double.valueOf(inputTinggi) * Double.valueOf(inputTinggi) / 10000);
        return bmi;
    }

    private String getKategori(Double BMIIndex){
        String kategori = "IDEAL     ";
        if (BMIIndex >= 25.1) {
            kategori = "OVERWEIGHT ";
        } else if (BMIIndex < 18.5) {
            kategori = "UNDERWEIGHT";
        }

        return kategori;
    }


}