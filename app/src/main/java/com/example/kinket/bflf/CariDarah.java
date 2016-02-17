package com.example.kinket.bflf;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CariDarah extends Activity {
    private EditText editTextnama1;
    private EditText editTextnohp2;
    private EditText editTextalamat3;
    private EditText editTextpasien4;
    private EditText editTextnik5;
String dateFormat;
    Spinner sp,sp2,sp3,sp4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cari_darah);

        sp = (Spinner) findViewById(R.id.editTextgoldarah);
        List<String> item = new ArrayList<String>();
        item.add("Golongan Darah yang Dibutuhkan");
        item.add("B");
        item.add("A");
        item.add("O");
        item.add("AB");

        sp2=(Spinner) findViewById(R.id.daerah);
        sp3=(Spinner) findViewById(R.id.tingkat);
        sp4=(Spinner) findViewById(R.id.jumlahdarah);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(CariDarah.this,android.R.layout.simple_spinner_dropdown_item,item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(adapter);
    }
    public void kirim(View view) {

        editTextnama1 = (EditText) findViewById(R.id.editTextnama);
        editTextnohp2 = (EditText) findViewById(R.id.editTextnohp);
        editTextalamat3 = (EditText) findViewById(R.id.editTextalamat);
        editTextpasien4 = (EditText) findViewById(R.id.editTextpasien);
        editTextnik5 = (EditText) findViewById(R.id.editTextnik);

        if(editTextnama1.getText().toString().equals("")){
            AlertDialog.Builder a_builder = new AlertDialog.Builder(CariDarah.this);
            a_builder.setMessage("nama tidak boleh kosong")
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
        else if(editTextnohp2.getText().toString().equals("")){
            AlertDialog.Builder a_builder = new AlertDialog.Builder(CariDarah.this);
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
        else if(sp.getSelectedItem().toString().equals("Golongan Darah yang Dibutuhkan")){
            AlertDialog.Builder a_builder = new AlertDialog.Builder(CariDarah.this);
            a_builder.setMessage("Golongan Darah Belum di Pilih")
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
        else if(sp2.getSelectedItem().toString().equals("KOTA")){
            AlertDialog.Builder a_builder = new AlertDialog.Builder(CariDarah.this);
            a_builder.setMessage("Daerah Kota Belum di Pilih")
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
        if(editTextnik5.getText().toString().equals("")){
            AlertDialog.Builder a_builder = new AlertDialog.Builder(CariDarah.this);
            a_builder.setMessage("NIK pelapor tidak boleh kosong")
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
        else if(sp4.getSelectedItem().toString().equals("Jumlah Kebutuhan")){
            AlertDialog.Builder a_builder = new AlertDialog.Builder(CariDarah.this);
            a_builder.setMessage("Jumlah Kantong Darah Yang di Butuhkan Belum di Pilih")
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
            SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
            Date date = new Date();


            String nama = editTextnama1.getText().toString();
            String nohp = editTextnohp2.getText().toString();
            String alamat = editTextalamat3.getText().toString();
            String goldarah = sp.getSelectedItem().toString();
            String daerah = sp2.getSelectedItem().toString();
            String keprluan = sp3.getSelectedItem().toString();
            String pasien = editTextpasien4.getText().toString();
            String nik_pelapor = editTextnik5.getText().toString();
            String jumlah_kebutuhan = sp4.getSelectedItem().toString();
            String tgl = dateFormat.format(String.valueOf(date));

            insertToDatabase(nama, nohp, alamat, goldarah, daerah, keprluan, pasien, nik_pelapor, jumlah_kebutuhan, tgl);

            AlertDialog.Builder a_builder = new AlertDialog.Builder(CariDarah.this);
            a_builder.setMessage("laporan anda segera di proses dengan cepat,Harap aktifkan selalu handphone anda")
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                            Intent hasilIntent = new Intent(CariDarah.this, MainActivity.class);
                            startActivity(hasilIntent);
                            finish();
                        }
                    });
            AlertDialog alert = a_builder.create();
            alert.setTitle("Info");
            alert.show();
        }
    }
    private void insertToDatabase(String nama, String nohp, String alamat, String goldarah, String daerah, String keprluan, String pasien, String nik_pelapor, String jumlah_kebutuhan, String tgl){
        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @SuppressWarnings("ResourceType")
            @Override
            protected String doInBackground(String... params) {

                String paramnama = params[0];
                String paramnohp = params[1];
                String paramalamat = params[2];
                String paramgoldarah= params[3];
                String paramdaerah= params[4];
                String paramkeprluan= params[5];
                String parampasien  = params[6];
                String paramnik_pelapor= params[7];
                String paramjumlah_kebutuhan= params[8];
                String paramtgl= params[9];

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("nama", paramnama));
                nameValuePairs.add(new BasicNameValuePair("nohp", paramnohp));
                nameValuePairs.add(new BasicNameValuePair("alamat", paramalamat));
                nameValuePairs.add(new BasicNameValuePair("goldarah", paramgoldarah));
                nameValuePairs.add(new BasicNameValuePair("daerah", paramdaerah));
                nameValuePairs.add(new BasicNameValuePair("keprluan", paramkeprluan));
                nameValuePairs.add(new BasicNameValuePair("pasien", parampasien));
                nameValuePairs.add(new BasicNameValuePair("nik_pelapor", paramnik_pelapor));
                nameValuePairs.add(new BasicNameValuePair("jumlah_kebutuhan", paramjumlah_kebutuhan));
                nameValuePairs.add(new BasicNameValuePair("tgl", paramtgl));

                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost("http://bflfadmin.esy.es/insert-caridarah.php");
                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                    HttpResponse response = httpClient.execute(httpPost);
                    HttpEntity entity = response.getEntity();
                    //is = entity.getContent();
                } catch (ClientProtocolException e) {
                } catch (IOException e) {
                }
                return "Berhasil Mengirim";
            }
            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
                //TextView textViewResult = (TextView) findViewById(R.id.textViewResult);
                //textViewResult.setText("Inserted");
            }
        }
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute(nama, nohp, alamat, goldarah, daerah, keprluan, pasien, nik_pelapor, jumlah_kebutuhan, tgl);
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            //Log.d(this.getClass().getName(), "back button pressed");
            //Toast.makeText(getApplicationContext(), "Tidak Bisa Back", Toast.LENGTH_LONG).show();
            android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(CariDarah.this);
            builder.setTitle("Anda Yakin Kembali Ke Menu?");
            String[] pilihan = {"Ya", "Tidak"};
            builder.setItems(pilihan, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int item) {
                    switch(item){
                        case 0 :
                            Intent intent = new Intent(CariDarah.this, MainActivity.class);
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