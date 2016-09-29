package com.example.kinket.bflf;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class detail_relawan extends Activity {
    private TextView editnama;
    private TextView editgoldarah;
    private TextView editnohp;
    private TextView editberat;

    String nohp="";
    private String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_relawan);
        Intent intent = getIntent();
        id= intent.getStringExtra(config.EMP_ID);

        editnama = (TextView)findViewById(R.id.Tnama);
        editgoldarah = (TextView)findViewById(R.id.Tgoldarah);
        editnohp = (TextView)findViewById(R.id.Tnohp);
        editberat = (TextView)findViewById(R.id.Tberat);

        getEmployee();
    }
    private void getEmployee(){
        class GetEmployee extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(detail_relawan.this,"Menampilkan Data...","Loading...",false,false);
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
                String s = rh.sendGetRequestParam(config.URL_GET_RELAWAN, id);
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
            String nama = c.getString(config.TAG_NAME);
            String goldarah = c.getString(config.TAG_DARAH);
            nohp = c.getString(config.TAG_NOHP);
            String berat = c.getString(config.TAG_BERAT);
            /////////////////////////////////////////////////////
            ////////////////////////////////////////////////////
            editnama.setText(nama);
            editgoldarah.setText(goldarah);
            editnohp.setText(nohp);
            editberat.setText(berat);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public  void  call (View view){
        dialContactPhone(nohp);
    }
    private void dialContactPhone(final String phoneNumber) {
        startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phoneNumber, null)));
    }
    public void sms (View view){
        Log.i("Send SMS", "");
        Intent smsIntent = new Intent(Intent.ACTION_VIEW);
        smsIntent.setData(Uri.parse("smsto:"));
        smsIntent.setType("vnd.android-dir/mms-sms");
        smsIntent.putExtra("address"  , new String (nohp));
        smsIntent.putExtra("sms_body"  , "");

        try {
            startActivity(smsIntent);
            finish();
            Log.i("Finished sending SMS...", "");
        }
        catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(detail_relawan.this,
                    "SMS faild, please try again later.", Toast.LENGTH_SHORT).show();
        }
    }
}
