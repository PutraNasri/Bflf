package com.example.kinket.bflf;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Lokasi extends Activity implements OnMapReadyCallback {
    GoogleMap gMaps;
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lokasi);
        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.mapslbs);
        mapFragment.getMapAsync(this);
        Intent intent = getIntent();

        try {
            initializeMaps();
            gMaps.addMarker(new MarkerOptions()
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.red))
                    .position(new LatLng(5.563612, 95.338193))
                    .visible(true)
                    .title("RSU BANDA ACEH"));

            gMaps.addMarker(new MarkerOptions()
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.red))
                    .position(new LatLng(5.565908, 95.339881))
                    .visible(true)
                    .title("PMI BANDA ACEH"));

            gMaps.addMarker(new MarkerOptions()
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.red))
                    .position(new LatLng(5.196123, 97.115350))
                    .visible(true)
                    .title("RSU LHOKSEUMAWE"));

            gMaps.addMarker(new MarkerOptions()
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.red))
                    .position(new LatLng(4.469749, 97.970192))
                    .visible(true)
                    .title("RSU LANGSA"));
            gMaps.addMarker(new MarkerOptions()
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.red))
                    .position(new LatLng(4.156341, 96.129869))
                    .visible(true)
                    .title("RSU MEULABOH"));
            gMaps.addMarker(new MarkerOptions()
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.red))
                    .position(new LatLng(4.636706, 96.851477))
                    .visible(true)
                    .title("RSU TAKENGON"));
            gMaps.addMarker(new MarkerOptions()
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.red))
                    .position(new LatLng(3.267379, 97.176744))
                    .visible(true)
                    .title("RSU ACEH SELATAN"));
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            gMaps.setMyLocationEnabled(true);
            CameraPosition cameraPosition = new CameraPosition.Builder().target(new LatLng(5.553670, 95.318832)).zoom(10).build();

            gMaps.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            gMaps.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition), 500, null);


        } catch (Exception e) {

        }
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void initializeMaps() {
        if (gMaps == null) {
            gMaps = ((MapFragment) getFragmentManager().findFragmentById(R.id.mapslbs)).getMap();

            if (gMaps == null) {
                Toast.makeText(getApplicationContext(), "Unable to create maps", Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void rood(View view)
    {
        gMaps.setMapType(GoogleMap.MAP_TYPE_NORMAL);
    }
    public void sat(View view)
    {
        gMaps.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }


        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onMapReady(GoogleMap map) {

        gMaps.setMapType(GoogleMap.MAP_TYPE_NORMAL);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            //Log.d(this.getClass().getName(), "back button pressed");
            //Toast.makeText(getApplicationContext(), "Tidak Bisa Back", Toast.LENGTH_LONG).show();
            android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(Lokasi.this);
            builder.setTitle("Anda Yakin Kembali Ke Menu?");
            String[] pilihan = {"Ya", "Tidak"};
            builder.setItems(pilihan, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int item) {
                    switch(item){
                        case 0 :
                            Intent intent = new Intent(Lokasi.this, MainActivity.class);
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