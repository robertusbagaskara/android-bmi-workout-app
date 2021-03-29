package com.example.ta_pam;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ta_pam.MainActivity;
import com.example.ta_pam.R;

public class TipsActivity extends AppCompatActivity {

    private TextView tvTipsHeader, tvLine1, tvLine2, tvLine3, tvLine4, tvLine5, tvLine6, tvLine7, backtocalculator;
    private View background;
    public String tema;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips);

        String tipsLine1 = "0", tipsLine2 = "0",
                tipsLine3 = "0", tipsLine4 = "0", tipsLine5 = "0", tipsLine6 = "0", tipsLine7 = "0";

        String kategori = getIntent().getStringExtra("kategori");

        backtocalculator = findViewById(R.id.backtocalculator);

        if (kategori.equals("OVERWEIGHT")) {
            tipsLine1 = "Jangan lupa minum air sebelum makan";
            tipsLine2 = "Jangan lupa sarapan";
            tipsLine3 = "Makanlah dalam porsi kecil";
            tipsLine4 = "Makan secara perlahan";
            tipsLine5 = "Tidak perlu menghindari makanan tertentu";
            tipsLine6 = "Tidurlah yang cukup";
            tipsLine7 = "Bergerak aktif";
        } else if(kategori.equals("UNDERWEIGHT")){
            tipsLine1 = "Mengkonsumsi makanan lebih sering";
            tipsLine2 = "Memilih makanan dengan lebih cermat";
            tipsLine3 = "Konsumsi makanan berlemak baik";
            tipsLine4 = "Memperbanyak minum susu";
            tipsLine5 = "Berolahraga secara rutin";
            tipsLine6 = "Tidak merokok";
            tipsLine7 = "Beristirahat yang cukup";
        }

        tvTipsHeader = findViewById(R.id.tv_tips_header);
        tvLine1 = findViewById(R.id.tv_tips_line1);
        tvLine2 = findViewById(R.id.tv_tips_line2);
        tvLine3 = findViewById(R.id.tv_tips_line3);
        tvLine4 = findViewById(R.id.tv_tips_line4);
        tvLine5 = findViewById(R.id.tv_tips_line5);
        tvLine6 = findViewById(R.id.tv_tips_line6);
        tvLine7 = findViewById(R.id.tv_tips_line7);

        tvTipsHeader.setText("Saran bagi Anda yang " + kategori);
        tvLine1.setText(tipsLine1);
        tvLine2.setText(tipsLine2);
        tvLine3.setText(tipsLine3);
        tvLine4.setText(tipsLine4);
        tvLine5.setText(tipsLine5);
        tvLine6.setText(tipsLine6);
        tvLine7.setText(tipsLine7);

        backtocalculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TipsActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
}
