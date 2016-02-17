package com.example.kinket.bflf;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Syarat extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_syarat);
    }
    public void lanjut(View view) {

        Intent intent = new Intent(Syarat.this, DonorDarah.class);
        startActivity(intent);
        finish();
    }
}
