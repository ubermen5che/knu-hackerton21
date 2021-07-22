package com.knuworld.com;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    JSONObject mainMapObj;
    JSONObject mainIDPWObj;
    EditText mainID;
    EditText mainPW;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. 전역변수 선언
        mainMapObj = new JSONObject();
        mainIDPWObj = new JSONObject();
        mainID = findViewById(R.id.activity_start_et_id);
        mainPW = findViewById(R.id.activity_start_et_pw);

        // 2. Title Bar 그림 설정
        getSupportActionBar().setIcon(R.drawable.dog_icon_2);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        // 3. 위도, 경도 관련 Location Manager 설정
        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        // 0. 권한 설정 (Default)
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        // 3. 위도, 경도 수신
        Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        // 3. JSON Object에 위도와 경도 삽입
        try {
            mainMapObj.put("lat", 35.88883600000000);
            mainMapObj.put("lng", 128.61030000000000);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    // 4. 로그인 버튼 클릭시
    public void mainLoginClicked(View v) throws JSONException {
        mainIDPWObj.put("id", mainID.getText().toString());
        mainIDPWObj.put("pw", mainPW.getText().toString());
        mainSendLogin(mainIDPWObj);
    }

    // 5. ID 발급 버튼 클릭시
    public void mainSignupClicked(View v) {
        Toast.makeText(this, "아이디가 발급되었습니다", Toast.LENGTH_LONG).show();
    }

    public void mainSendLogin(JSONObject obj) {
        String url = "http://3.36.121.93:8084/api/login";
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                url, obj,
                // 응답을 잘 받았을 때 이 메소드가 자동으로 호출
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("현재 응답은 ", String.valueOf(response));
                        try {
                            if(response.getString("result").equals("success"))
                                mainSendJSON(mainMapObj);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        //mainSendJSON(mainMapObj);
                    }
                },
                // 에러 발생시 호출될 리스너 객체
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("에러 발생! 원인은 ", error.getMessage());
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/json");
                return params;
            }
        };
        // 이전 결과가 있어도 새로 요청하여 응답을 보여줌
        jsonObjReq.setShouldCache(false);
        // Request Queue 초기화 필수
        AppHelper.requestQueue = Volley.newRequestQueue(this);
        AppHelper.requestQueue.add(jsonObjReq);
    }

    // 6. Volley로 HTTP 요청
    public void mainSendJSON(JSONObject obj) {
        String url = "http://3.36.121.93:8084/api/getXY";
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                url, obj,
                new Response.Listener<JSONObject>() {
                    // 6-1. 수신성공 -> JSONArray 다음 Activiy로 넘김
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("성공", response.toString());
                        mainReceiveJSON(response);
                    }

                }, new Response.ErrorListener() {
            // 6-2. 수신실패 -> 오류 출력
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("실패", error.toString());
            }

        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/json");
                return params;
            }
        };;
        jsonObjReq.setShouldCache(false);
        AppHelper.requestQueue = Volley.newRequestQueue(this);
        AppHelper.requestQueue.add(jsonObjReq);
    }

    // 7. JSON 객체를 다음 Activity로 넘김
    void mainReceiveJSON(JSONObject response) {
        try {
            JSONArray arr = (JSONArray) response.get("dataList");
            Intent intent = new Intent(getApplicationContext(), MapActivity.class);
            intent.putExtra("jsonArray", arr.toString());
            startActivity(intent);
            this.finish();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}