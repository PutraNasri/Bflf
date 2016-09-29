package com.example.kinket.bflf;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class pilih_prov extends Activity {
    private Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilih_prov);
        spinner = (Spinner) findViewById(R.id.spinner);
        List<String> item = new ArrayList<String>();
        item.add("ACEH");
        item.add("BALI");
        item.add("BANTEN");
        item.add("Meuraxa");
        item.add("BENGKULU");
        item.add("GORONTALO");
        item.add("JAKARTA");
        item.add("JAMBI");
        item.add("JAWA BARAT");
        item.add("JAWA TENGAH");
        item.add("JAWA TIMUR");
        item.add("KALIMANTAN BARAT");
        item.add("KALIMANTAN SELATAN");
        item.add("KALIMANTAN TENGAH");
        item.add("KALIMANTAN TIMUR");
        item.add("KALIMANTAN UTARA");
        item.add("KEPULAUAN BANGKA BELITUNG");
        item.add("KAPULAUAN RIAU");
        item.add("LAMPUNG");
        item.add("MALUKU");
        item.add("MALUKU UTARA");
        item.add("NUSA TENGGARA BARAT");
        item.add("NUSA TENGGARA TIMUR");
        item.add("PAPUA");
        item.add("PAPUA BARAT");
        item.add("RIAU");
        item.add("SULAWESI BARAT");
        item.add("SULAWESI SELATAN");
        item.add("SULAWESI TENGAH");
        item.add("SULAWESI TENGGARA");
        item.add("SULAWESI UTARA");
        item.add("SUMATERA BARAT");
        item.add("SUMATERA SELATAN");
        item.add("SUMATERA UTARA");
        item.add("YOGYAKARTA");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(pilih_prov.this, android.R.layout.simple_spinner_dropdown_item, item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
    public void aceh(View view){
        Intent intent = new Intent(pilih_prov.this, pilih_golongan.class);
        startActivity(intent);
        finish();
    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        Intent intent = new Intent(pilih_prov.this, Main2Activity.class);
        startActivity(intent);
        finish();

        return super.onKeyDown(keyCode, event);
    }
}
