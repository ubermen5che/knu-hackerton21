package com.knuworld.com;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        // 1. Title Bar 그림 설정
        getSupportActionBar().setIcon(R.drawable.dog_icon_2);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        // 2. Google Map 기본 설정
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    // 3. Google Map 설정
    public void onMapReady(final GoogleMap googleMap) {
        // 4. Intent로 넘어온 JSON Array 수신
        Intent intent = getIntent();
        String mapString = intent.getStringExtra("jsonArray");
        LatLng latlng_my = new LatLng(35.88883600000000, 128.61030000000000);
        LatLng latlng_tmp = null;

        // 5. Google Map 설정
        mMap = googleMap;

        try {
            JSONArray mapJSONArray = new JSONArray(mapString);
            for (int i = 0; i < mapJSONArray.length(); i++) {
                JSONObject mapJSONObject = (JSONObject) mapJSONArray.get(i);
                // 6. JSON Array에서 요소 빼내기
                double lat = mapJSONObject.getDouble("lat");
                double lng = mapJSONObject.getDouble("lng");
                String type = mapJSONObject.getString("type");
                String addr = mapJSONObject.getString("address");
                String disinfection = mapJSONObject.getString("disinfection");

                latlng_tmp = new LatLng(lat, lng);
                MarkerOptions markerOptions = new MarkerOptions();

                // 7. Google Map 마커 설정
                markerOptions.position(latlng_tmp)
                            .title(type)
                            .snippet(addr);

                // 8. 소독 완료시 아이콘 변경
                if(disinfection.equals("소독완료")) {
                    markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.kguard));
                } else {
                    markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.infection_icon));
                }
                // 8. Google Map 마커 추가
                mMap.addMarker(markerOptions);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // 0. 권한 설정 (Default)
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mMap.setMyLocationEnabled(true);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latlng_my, 14));
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
    }
}