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
public class CariDarah extends Activity {
    private EditText editTextnama1;
    private EditText editTextnohp2;
    private EditText editTextalamat3;

    Spinner sp,sp2,sp3;

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

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(CariDarah.this,android.R.layout.simple_spinner_dropdown_item,
                item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(adapter);
    }
    public void kirim(View view) {
        editTextnama1 = (EditText) findViewById(R.id.editTextnama);
        editTextnohp2 = (EditText) findViewById(R.id.editTextnohp);
        editTextalamat3 = (EditText) findViewById(R.id.editTextalamat);

        String nama = editTextnama1.getText().toString();
        String nohp    = editTextnohp2.getText().toString();
        String alamat = editTextalamat3.getText().toString();
        String goldarah = sp.getSelectedItem().toString();

        insertToDatabase(nama, nohp, alamat, goldarah);
    }
    private void insertToDatabase(String nama, String nohp, String alamat, String goldarah){
        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @SuppressWarnings("ResourceType")
            @Override
            protected String doInBackground(String... params) {

                String paramnama = params[0];
                String paramnohp = params[1];
                String paramalamat = params[2];
                String paramgoldarah= params[3];

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("nama", paramnama));
                nameValuePairs.add(new BasicNameValuePair("nohp", paramnohp));
                nameValuePairs.add(new BasicNameValuePair("alamat", paramalamat));
                nameValuePairs.add(new BasicNameValuePair("goldarah", paramgoldarah));

                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(
                            "http://kinketkuena.esy.es/insert-caridarah.php");
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
        sendPostReqAsyncTask.execute(nama, nohp, alamat, goldarah);
    }}