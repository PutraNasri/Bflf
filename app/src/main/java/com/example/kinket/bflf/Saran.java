package com.example.kinket.bflf;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Saran extends Activity {
    private EditText editsaran1;
    String dateFormat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saran);
    }
    public void kirim(View view) {

        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        Date date = new Date();

        editsaran1 = (EditText) findViewById(R.id.editsaran);
        String text = editsaran1.getText().toString();
        String tgl = dateFormat.format(String.valueOf(date));


        insertToDatabase(text, tgl);

    }
    private void insertToDatabase(String text, String tgl){
        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @SuppressWarnings("ResourceType")
            @Override
            protected String doInBackground(String... params) {
                String paramtext = params[0];
                String paramtgl = params[1];

                //InputStream is = null;
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("text", paramtext));
                nameValuePairs.add(new BasicNameValuePair("tgl", paramtgl));

                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(
                            "http://bflfadmin.esy.es/insert-saran.php");
                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                    HttpResponse response = httpClient.execute(httpPost);
                    HttpEntity entity = response.getEntity();
                    //is = entity.getContent();
                } catch (ClientProtocolException e) {
                } catch (IOException e) {
                }
                return "Berhasil Memberi Saran";
            }
            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
                TextView textViewResult = (TextView) findViewById(R.id.textViewResult);
                textViewResult.setText("Inserted");



                AlertDialog.Builder a_builder = new AlertDialog.Builder(Saran.this);
                a_builder.setMessage("Terima Kasih Telah Memberi Saran Kepada Kami")
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                                Intent hasilIntent = new Intent(Saran.this, MainActivity.class);
                                startActivity(hasilIntent);
                                finish();
                            }
                        });
                AlertDialog alert = a_builder.create();
                alert.setTitle("Info");
                alert.show();
            }
        }
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute(text, tgl);
    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            //Log.d(this.getClass().getName(), "back button pressed");
            //Toast.makeText(getApplicationContext(), "Tidak Bisa Back", Toast.LENGTH_LONG).show();
            android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(Saran.this);
            builder.setTitle("Anda Yakin Kembali Ke Menu?");
            String[] pilihan = {"Ya", "Tidak"};
            builder.setItems(pilihan, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int item) {
                    switch(item){
                        case 0 :
                            Intent intent = new Intent(Saran.this, MainActivity.class);
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
    }}