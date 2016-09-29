package com.example.kinket.bflf;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

public class pilih_golongan extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilih_golongan);
    }
    public void onClick (View view) {
        Intent intent = new Intent(pilih_golongan.this, CariDarah.class);
        startActivity(intent);
        finish();
    }


    public boolean onKeyDown(int keyCode, KeyEvent event) {

        Intent intent = new Intent(pilih_golongan.this, pilih_prov.class);
        startActivity(intent);
        finish();

        return super.onKeyDown(keyCode, event);
    }
}
