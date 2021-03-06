package com.example.kinket.bflf;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DonorDarah extends Activity {
    private EditText editTextnama1;
    private EditText editTextnohp5;
    private EditText editTextberatbadan6;


    Spinner sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_darah);

        sp = (Spinner) findViewById(R.id.editTextgoldarah);
        List<String> item = new ArrayList<String>();
        item.add("Golongan Darah *");
        item.add("B");
        item.add("A");
        item.add("O");
        item.add("AB");

        ArrayAdapter<String> adapterr = new ArrayAdapter<String>(DonorDarah.this,android.R.layout.simple_spinner_dropdown_item,item);
        adapterr.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(adapterr);
    }
    public void daftar(View view) {

        editTextnama1 = (EditText) findViewById(R.id.editTextnama);
        editTextnohp5 = (EditText) findViewById(R.id.editTextnohp);
        editTextberatbadan6 = (EditText) findViewById(R.id.editTextberatbadan);
        if(editTextnama1.getText().toString().equals("")){
            AlertDialog.Builder a_builder = new AlertDialog.Builder(DonorDarah.this);
            a_builder.setMessage("Nama tidak boleh kosong")
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert = a_builder.create();
            alert.setTitle("Info");
            alert.show();
        }

        else if(editTextnohp5.getText().toString().equals("")){
            AlertDialog.Builder a_builder = new AlertDialog.Builder(DonorDarah.this);
            a_builder.setMessage("No Handphone tidak boleh kosong")
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert = a_builder.create();
            alert.setTitle("Info");
            alert.show();
        }
        else if(editTextberatbadan6.getText().toString().equals("")){
            AlertDialog.Builder a_builder = new AlertDialog.Builder(DonorDarah.this);
            a_builder.setMessage("Berat Badan harus di isi")
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert = a_builder.create();
            alert.setTitle("Info");
            alert.show();
        }
        else if(sp.getSelectedItem().toString().equals("Golongan Darah *")){
            AlertDialog.Builder a_builder = new AlertDialog.Builder(DonorDarah.this);
            a_builder.setMessage("Golongan Darah belum di pilih")
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert = a_builder.create();
            alert.setTitle("Info");
            alert.show();
        }

        else {
            String nama = editTextnama1.getText().toString();
            String goldarah = sp.getSelectedItem().toString();
            String nohp = editTextnohp5.getText().toString();
            String beratbadan = editTextberatbadan6.getText().toString();

            insertToDatabase(nama, goldarah, nohp, beratbadan);

            AlertDialog.Builder a_builder = new AlertDialog.Builder(DonorDarah.this);
            a_builder.setMessage("Terima Kasih Telah Bergabung, Anda Akan di Hubungi Ketika Ada yang Membutuhkan Darah Sesuai Golongan Daarah Anda  ")
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                            Intent hasilIntent = new Intent(DonorDarah.this, Main2Activity.class);
                            startActivity(hasilIntent);
                            finish();
                        }
                    });
            AlertDialog alert = a_builder.create();
            alert.setTitle("Info");
            alert.show();
        }
    }
    private void insertToDatabase(String nama, String goldarah, String nohp, String beratbadan){
        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @SuppressWarnings("ResourceType")
            @Override
            protected String doInBackground(String... params) {
                String paramnama = params[0];
                String paramgoldarah= params[1];
                String paramnohp= params[2];
                String paramberatbadan= params[3];

                //InputStream is = null;
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("nama", paramnama));
                nameValuePairs.add(new BasicNameValuePair("golongan", paramgoldarah));
                nameValuePairs.add(new BasicNameValuePair("nohp", paramnohp));
                nameValuePairs.add(new BasicNameValuePair("beratbadan", paramberatbadan));

                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(
                            "http://cobabflf.esy.es/add_aceh.php");
                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                    HttpResponse response = httpClient.execute(httpPost);
                    HttpEntity entity = response.getEntity();
                    //is = entity.getContent();
                } catch (ClientProtocolException e) {
                } catch (IOException e) {
                }
                return "success";
            }
            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
                TextView textViewResult = (TextView) findViewById(R.id.textViewResult);
                textViewResult.setText("Inserted");
            }
        }
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute(nama, goldarah, nohp, beratbadan);
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            //Log.d(this.getClass().getName(), "back button pressed");
            //Toast.makeText(getApplicationContext(), "Tidak Bisa Back", Toast.LENGTH_LONG).show();
            android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(DonorDarah.this);
            builder.setTitle("Anda Yakin Kembali Ke Menu?");
            String[] pilihan = {"Ya", "Tidak"};
            builder.setItems(pilihan, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int item) {
                    switch(item){
                        case 0 :
                            Intent intent = new Intent(DonorDarah.this, Main2Activity.class);
                            startActivity(intent);
                            finish();
                            break;
                        case 1 :
                            break;
                    }
                }
            });
            builder.create().show();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }
}
