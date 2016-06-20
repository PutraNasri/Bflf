package com.example.kinket.bflf;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class CariDarah extends Activity implements  ListView.OnItemClickListener {
    private ListView listView;
    private String JSON_STRING;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cari_darah);

        listView = (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(this);


        getJSON();
    }

    private void getJSON(){
        class GetJSON extends AsyncTask<Void,Void,String>{

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(CariDarah.this,"Mengambil Data","Loading...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                JSON_STRING = s;
                showEmployee();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequest(config.URL_GET_ALL_DATA);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }
    private void showEmployee(){

        JSONObject jsonObject = null;
        ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String, String>>();
        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(config.TAG_JSON_ARRAY);

            for(int i = 0; i<result.length(); i++){
                JSONObject jo = result.getJSONObject(i);
                String id = jo.getString(config.TAG_ID);
                String name = jo.getString(config.TAG_NAME);
                String nohp = jo.getString(config.TAG_NOHP);
                String darah = jo.getString(config.TAG_DARAH);
                String daerah = jo.getString(config.TAG_DAERAH);

                HashMap<String,String> employees = new HashMap<>();
                employees.put(config.TAG_ID,id);
                employees.put(config.TAG_NAME,name);
                employees.put(config.TAG_NOHP,nohp);
                employees.put(config.TAG_DARAH,darah);
                employees.put(config.TAG_DAERAH,daerah);

                list.add(employees);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        ListAdapter adapter = new SimpleAdapter(
                CariDarah.this, list, R.layout.list,
                new String[]{config.TAG_NAME,config.TAG_NOHP,config.TAG_DARAH,config.TAG_DAERAH},
                new int[]{R.id.name, R.id.nohp, R.id.darah, R.id.daerah});

        listView.setAdapter(adapter);



    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        /*
        Intent intent = new Intent(this, detail_meuraxa.class);
        HashMap<String,String> map =(HashMap)parent.getItemAtPosition(position);
        String empId = map.get(config.TAG_ID).toString();
        intent.putExtra(config.EMP_ID,empId);
        startActivity(intent);
       */
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
                            Intent intent = new Intent(CariDarah.this, Main2Activity.class);
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