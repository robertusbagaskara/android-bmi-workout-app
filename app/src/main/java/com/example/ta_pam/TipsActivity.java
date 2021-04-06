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


        if (kategori.equals("OVERWEIGHT")) {
            tipsLine1 = "1. Olahraga seminggu 3 kali, setiap kali minimal 30 menit. Olahraga dapat membantu memperlancar metabolisme";
            tipsLine2 = "2. Diet rendah lemak seperti goreng-gorengan, perbanyak makan makanan yang rebus atau dibakar.";
            tipsLine3 = "3. Diet yang dianjurkan adalah diet rendah lemak, tinggi serat (banyak sayur dan buah), tinggi karbohidrat kompleks, dengan protein.";
            tipsLine4 = "4. Batasi makan malam sampai jam 7 malam, apabila lebih dari jam tersebut, anda dapat mengonsumsi buah atau sayur";
            tipsLine5 = "5. Perbanyak buah dan sayur, kurangi jenis karbohidrat simpleks seperti nasi putih, dan roti putih. Anda dapat mengonsumsi beras merah atau roti gandum sebagai pengganti karbohidrat simpleks";
            tipsLine6 = "6. Jangan makan makanan instan seperti sosis, sarden, kornet, dll";
            tipsLine7 = "7. Perbanyak minum air putih (minimal 8 gelas sehari)";
        } else if(kategori.equals("UNDERWEIGHT")){
            tipsLine1 = "1. Makan lebih sering, makan dengan porsi sedikit namun dengan frekuensi yang lebih banyak";
            tipsLine2 = "2. Minum minuman yang berkalori, cobalah minum seperti susu, jus, atau smoothiest. Minuman ini akan memberikan asupan kalori sekaligus nutrisi.";
            tipsLine3 = "3. Jauhi junk food. Junk food memang jadi salah satu makanan penyebab kegemukan. Tapi mengandung kalori ‘kosong’ dengan tambahan gula, garam dan lemak jahat yang buruk bagi kesehatan.";
            tipsLine4 = "4. Konsumsi keju. Salah satu pilihan produk dairy yang enak, kaya kalori, vitamin dan nutrisi yang dibutuhkan tubuh adalah keju.";
            tipsLine5 = "5. Konsumsi banyak protein. Beberapa studi membuktikan bahwa diet tinggi protein yang dilakukan bisa membantu mengubah ekstra kalori jadi otot, bukan lemak.";
            tipsLine6 = "6. Olahraga, olahraga bisa membantu menambah bobot dengan otot yang terbentuk, juga bisa membuat lapar untuk menstimulasi selera makan.";
            tipsLine7 = "7. Beristirahat yang cukup. Istirahat cukup diketahui berperan dalam mendukung proses pembentukan jaringan tubuh. Idealnya, orang dewasa butuh tidur selama 7 – 9 jam tiap hari.";
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
