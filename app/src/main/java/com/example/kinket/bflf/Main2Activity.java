package com.example.kinket.bflf;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

public class Main2Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }
    public void butuh (View view) {
        Intent intent = new Intent(Main2Activity.this, pilih_prov.class);
        startActivity(intent);
        finish();
    }
    public void donor (View view) {
        Intent intent = new Intent(Main2Activity.this, Syarat.class);
        startActivity(intent);
        finish();
    }
    public void lokasi (View view) {
    //    Intent intent = new Intent(Main2Activity.this, Lokasi.class);
      //  startActivity(intent);
       // finish();
    }
    public void saran (View view) {
        Intent intent = new Intent(Main2Activity.this, Saran.class);
        startActivity(intent);
        finish();
    }
    public void tentang (View view) {
        Intent intent = new Intent(Main2Activity.this, tentang.class);
        startActivity(intent);
        finish();
    }
    public void berita (View view) {
        Intent intent = new Intent(Main2Activity.this, berita.class);
        startActivity(intent);
        finish();
    }
    public void grub (View view) {
        Intent intent = new Intent(Main2Activity.this, profil.class);
       startActivity(intent);
       finish();
    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        finish();
        return super.onKeyDown(keyCode, event);
    }
}
