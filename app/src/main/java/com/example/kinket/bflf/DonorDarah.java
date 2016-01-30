package com.example.kinket.bflf;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
    private EditText editTextalamat2;
    private EditText editTextumur3;
    private EditText editTextnohp5;
    private EditText editTextberatbadan6;

    Spinner sp,sp2,sp3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_darah);

        sp = (Spinner) findViewById(R.id.editTextgoldarah);
        List<String> item = new ArrayList<String>();
        item.add("Golongan Darah");
        item.add("B");
        item.add("A");
        item.add("O");
        item.add("AB");

        sp2 = (Spinner) findViewById(R.id.spinner2);
        sp3 = (Spinner) findViewById(R.id.spinner3);


        ArrayAdapter<String> adapterr = new ArrayAdapter<String>(DonorDarah.this,android.R.layout.simple_spinner_dropdown_item,
                item);
        adapterr.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(adapterr);


    }
    public void daftar(View view) {

        editTextnama1 = (EditText) findViewById(R.id.editTextnama);
        editTextalamat2 = (EditText) findViewById(R.id.editTextalamat);
        editTextumur3 = (EditText) findViewById(R.id.editTextumur);
        editTextnohp5 = (EditText) findViewById(R.id.editTextnohp);
        editTextberatbadan6 = (EditText) findViewById(R.id.editTextberatbadan);


        String nama = editTextnama1.getText().toString();
        String alamat    = editTextalamat2.getText().toString();
        String umur = editTextumur3.getText().toString();
        String goldarah = sp.getSelectedItem().toString();
        String nohp = editTextnohp5.getText().toString();
        String beratbadan = editTextberatbadan6.getText().toString();
        String jeniskelamin = sp3.getSelectedItem().toString();
        String daerah = sp2.getSelectedItem().toString();
        insertToDatabase(nama, alamat, umur, goldarah, nohp, beratbadan, jeniskelamin, daerah);

    }
    private void insertToDatabase(String nama, String alamat, String umur, String goldarah, String nohp, String beratbadan, String jeniskelamin, String daerah){
        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @SuppressWarnings("ResourceType")
            @Override
            protected String doInBackground(String... params) {
                String paramnama = params[0];
                String paramalamat = params[1];
                String paramumur = params[2];
                String paramgoldarah= params[3];
                String paramnohp= params[4];
                String paramberatbadan= params[5];
                String paramjeniskelamin= params[6];
                String paramdaerah= params[7];
                //InputStream is = null;
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("nama", paramnama));
                nameValuePairs.add(new BasicNameValuePair("alamat", paramalamat));
                nameValuePairs.add(new BasicNameValuePair("umur", paramumur));
                nameValuePairs.add(new BasicNameValuePair("goldarah", paramgoldarah));
                nameValuePairs.add(new BasicNameValuePair("nohp", paramnohp));
                nameValuePairs.add(new BasicNameValuePair("beratbadan", paramberatbadan));
                nameValuePairs.add(new BasicNameValuePair("jeniskelamin", paramjeniskelamin));
                nameValuePairs.add(new BasicNameValuePair("daerah", paramdaerah));
                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(
                            "http://kinketkuena.esy.es/insert-relawan.php");
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
        sendPostReqAsyncTask.execute(nama, alamat, umur, goldarah, nohp, beratbadan, jeniskelamin, daerah);
    }}





