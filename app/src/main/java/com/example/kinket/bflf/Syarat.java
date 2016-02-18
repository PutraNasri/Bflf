package com.example.kinket.bflf;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
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
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            //Log.d(this.getClass().getName(), "back button pressed");
            //Toast.makeText(getApplicationContext(), "Tidak Bisa Back", Toast.LENGTH_LONG).show();

                            Intent intent = new Intent(Syarat.this, MainActivity.class);
                            startActivity(intent);
                            finish();

        }
        return super.onKeyDown(keyCode, event);
    }
}
