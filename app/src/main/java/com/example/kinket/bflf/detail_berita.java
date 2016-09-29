package com.example.kinket.bflf;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class detail_berita extends Activity {
    private TextView editjudul;
    private TextView editisi;
    private TextView edittanggal;

    private String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_berita);
        Intent intent = getIntent();
        id= intent.getStringExtra(config.EMP_ID);

        editjudul = (TextView)findViewById(R.id.Tjudul);
        editisi = (TextView)findViewById(R.id.Tisi);
        edittanggal = (TextView)findViewById(R.id.Ttanggal);


        getEmployee();
    }
    private void getEmployee(){
        class GetEmployee extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(detail_berita.this,"Menampilkan Data...","Loading...",false,false);
            }
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                showEmployee(s);
            }
            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(config.URL_GET_BERITA, id);
                return s;
            }
        }
        GetEmployee ge = new GetEmployee();
        ge.execute();
    }
    private void showEmployee(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(config.TAG_JSON_ARRAY);
            JSONObject c = result.getJSONObject(0);
            String id = c.getString(config.TAG_ID);
            String judul = c.getString(config.TAG_JUDUL);
            String isi = c.getString(config.TAG_ISI);
            String tanggal = c.getString(config.TAG_TANGGAL);
            /////////////////////////////////////////////////////
            ////////////////////////////////////////////////////
            editjudul.setText(judul);
            editisi.setText(isi);
            edittanggal.setText(tanggal);


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {

        Intent intent = new Intent(detail_berita.this, berita.class);
        startActivity(intent);
        finish();

        return super.onKeyDown(keyCode, event);
    }
}
