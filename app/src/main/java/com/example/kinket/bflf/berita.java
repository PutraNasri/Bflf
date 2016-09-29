package com.example.kinket.bflf;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
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

public class berita extends Activity implements  ListView.OnItemClickListener {
    private ListView listView;
    private String JSON_STRING;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_berita);
        listView = (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(this);
        getJSON();
    }
    private void getJSON(){
        class GetJSON extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(berita.this,"Mengambil Data","Loading...",false,false);
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
                String s = rh.sendGetRequest(config.URL_GET_ALL_BERITA);
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
                String judul = jo.getString(config.TAG_JUDUL);
                String tanggal = jo.getString(config.TAG_TANGGAL);
                HashMap<String,String> employees = new HashMap<>();
                employees.put(config.TAG_ID,id);
                employees.put(config.TAG_JUDUL,judul);
                employees.put(config.TAG_TANGGAL,tanggal);
                list.add(employees);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ListAdapter adapter = new SimpleAdapter(
                berita.this, list, R.layout.list_berita,
                new String[]{config.TAG_JUDUL,config.TAG_TANGGAL},
                new int[]{R.id.judul, R.id.tanggal});
        listView.setAdapter(adapter);
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, detail_berita.class);
        HashMap<String,String> map =(HashMap)parent.getItemAtPosition(position);
        String empId = map.get(config.TAG_ID).toString();
        intent.putExtra(config.EMP_ID,empId);
        startActivity(intent);
        finish();
    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {
                            Intent intent = new Intent(berita.this, Main2Activity.class);
                            startActivity(intent);
                            finish();
        return super.onKeyDown(keyCode, event);
    }
}
