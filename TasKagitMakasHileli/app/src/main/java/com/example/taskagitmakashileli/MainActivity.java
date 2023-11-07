package com.example.taskagitmakashileli;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageView imageViewOyuncu, imageViewBilgisayar;
    TextView textViewOyuncu, textViewBilgisayar;
    Button btnTas, btnKagit, btnMakas, btnReset, btnHile;

    int[] resimler = {R.drawable.tas, R.drawable.kagit, R.drawable.makas};

    int oyuncuPuan, bilgisayarPuan;
    boolean hileAktif = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageViewOyuncu = findViewById(R.id.iv_oyuncu);
        imageViewBilgisayar = findViewById(R.id.iv_bilgisayar);
        textViewOyuncu = findViewById(R.id.tv_oyuncu_puan);
        textViewBilgisayar = findViewById(R.id.tv_bilgisayar_puan);
        btnTas = findViewById(R.id.btn_tas);
        btnKagit = findViewById(R.id.btn_kagit);
        btnMakas = findViewById(R.id.btn_makas);
        btnReset = findViewById(R.id.btn_reset);
        btnHile = findViewById(R.id.btn_hile);

        oyuncuPuan = 0;
        bilgisayarPuan = 0;

        btnTas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oyna(0);
            }
        });

        btnKagit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oyna(1);
            }
        });

        btnMakas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oyna(2);
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sifirlama();
                int a = (int) (Math.random() * 3); // Resimler random olarak aynı gelecek
                imageViewOyuncu.setImageResource(resimler[a]); // Oyuncu resmini taş olarak güncelle
                imageViewBilgisayar.setImageResource(resimler[a]); // Bilgisayar resmini taş olarak güncelle
            }
        });

        btnHile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hileAktif = !hileAktif; // Hileyi aktif veya pasif yap
                String hileDurumu = hileAktif ? "Hile Aktif" : "Hile Pasif";
                Toast.makeText(MainActivity.this, hileDurumu, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void oyna(int oyuncuSecim) {
        int bilgisayarSecim;

        if (hileAktif) {
            // Hile aktifse, bilgisayar seçimini ayarla
            if (oyuncuSecim == 0) {
                bilgisayarSecim = 2; // Bilgisayar makas seçer
            } else if (oyuncuSecim == 1) {
                bilgisayarSecim = 0; // Bilgisayar taş seçer
            } else {
                bilgisayarSecim = 1; // Bilgisayar kağıt seçer
            }
        } else {
            // Hile pasifse, bilgisayar rastgele seçim yapar
            bilgisayarSecim = (int) (Math.random() * 3);
        }

        imageViewOyuncu.setImageResource(resimler[oyuncuSecim]);
        imageViewBilgisayar.setImageResource(resimler[bilgisayarSecim]);

        if (oyuncuSecim == bilgisayarSecim) {
            // Berabere durumu
            //Toast.makeText(this, "Berabere :|", Toast.LENGTH_SHORT).show();
        } else if ((oyuncuSecim == 0 && bilgisayarSecim == 2) ||
                (oyuncuSecim == 1 && bilgisayarSecim == 0) ||
                (oyuncuSecim == 2 && bilgisayarSecim == 1)) {
            // Oyuncu kazandı durumu
            oyuncuKazandi();
        } else {
            // Bilgisayar kazandı durumu
            bilgisayarKazandi();
        }
    }

    private void oyuncuKazandi() {
        oyuncuPuan++;
        //Toast.makeText(this, "Kazandınız :)", Toast.LENGTH_SHORT).show();

        textViewOyuncu.setText("Oyuncu: " + oyuncuPuan);
        textViewBilgisayar.setText("Bilgisayar: " + bilgisayarPuan);
    }

    private void bilgisayarKazandi() {
        bilgisayarPuan++;
        //Toast.makeText(this, "Kaybettiniz :(", Toast.LENGTH_SHORT).show();

        textViewOyuncu.setText("Oyuncu: " + oyuncuPuan);
        textViewBilgisayar.setText("Bilgisayar: " + bilgisayarPuan);
    }

    private void sifirlama() {
        oyuncuPuan = 0;
        bilgisayarPuan = 0;
        textViewOyuncu.setText("Oyuncu: " + oyuncuPuan); // Oyuncu puanını 0'a eşitle
        textViewBilgisayar.setText("Bilgisayar: " + bilgisayarPuan); // Bilgisayar puanını 0'a eşitle
    }
}
