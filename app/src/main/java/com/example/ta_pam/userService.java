package com.example.ta_pam;

import android.content.Context;
import android.content.SharedPreferences;

import java.sql.SQLOutput;
import java.util.ResourceBundle;

public class userService {

    public static double getBMIIndex(String inputBerat, String inputTinggi){
        double bmi = Double.valueOf(inputBerat) / (Double.valueOf(inputTinggi) * Double.valueOf(inputTinggi) / 10000);
        return bmi;
    }

    public static String getKategori(Double BMIIndex){
        String kategori = "IDEAL";
        if (BMIIndex >= 25.1) {
            kategori = "OVERWEIGHT";
        } else if (BMIIndex < 18.5) {
            kategori = "UNDERWEIGHT";
        }
        System.out.println("AAAAAAAA");
        return kategori;
    }
}
