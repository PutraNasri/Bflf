package com.example.kinket.bflf;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }
    public void butuh (View view) {
        Intent intent = new Intent(Main2Activity.this, CariDarah.class);
        startActivity(intent);
    }
    public void donor (View view) {
        Intent intent = new Intent(Main2Activity.this, Syarat.class);
        startActivity(intent);
    }
    public void lokasi (View view) {
        Intent intent = new Intent(Main2Activity.this, Lokasi.class);
        startActivity(intent);
    }
    public void saran (View view) {
        Intent intent = new Intent(Main2Activity.this, Saran.class);
        startActivity(intent);
    }
    public void tentang (View view) {
        Intent intent = new Intent(Main2Activity.this, tentang.class);
        startActivity(intent);
    }
}
