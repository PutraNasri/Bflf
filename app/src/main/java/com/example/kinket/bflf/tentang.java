package com.example.kinket.bflf;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;

public class tentang extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tentang);
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {


                            Intent intent = new Intent(tentang.this, Main2Activity.class);
                            startActivity(intent);
                            finish();

            return false;
        }
        return super.onKeyDown(keyCode, event);
    }
}
