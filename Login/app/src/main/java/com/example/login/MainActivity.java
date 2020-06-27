package com.example.login;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;


import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;
import com.kakao.usermgmt.callback.UnLinkResponseCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class MainActivity extends AppCompatActivity implements LocationListener {

    String msg;
    ListView listView;
    BoardAdapter adapter;
    DatabaseHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* 내장 디비 pref 받아오기 */
        SQLiteDatabase db;
        handler = new DatabaseHandler(this);

        db = handler.getReadableDatabase();
        Cursor cursor = db.query("pref", new String[] {"code"}, null, null, null, null, null);

        String code = "";
        while(cursor.moveToNext()){
            code = cursor.getString(0);
        }

        System.out.println("asd: " + code);

        cursor.close();
        handler.close();

        // 전역변수 값 받아오기
        String globalstrNickname=App.getInstance().getstrNickname();
        String globalstrProfile=App.getInstance().getstrProfile();
        String globalstrEmail=App.getInstance().getstrEmail();
        String globalstrAgeRange=App.getInstance().getstrAgeRange();
        String globalstrGender=App.getInstance().getstrGender();

        listView = findViewById(R.id.board_listView);
        adapter = new BoardAdapter();
        listView.setAdapter(adapter);

//        adapter.addItem("asdas", "asdasd", "aasdasd");
//        adapter.addItem("a12sdas", "asddsa", "asdasdasd");
//        adapter.addItem("as232das", "asdas", "aasasasdsaddasd");
//        adapter.addItem("232", "asdsa", "asdsad");
//        adapter.addItem("323", "asdasd", "asdsad");





        locationListener.onLocationChanged(getLocation());
        if(msg==null)
            Toast.makeText(getApplicationContext(), "내용없음.", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(getApplicationContext(),  msg, Toast.LENGTH_LONG).show();
        Button btnLogout=findViewById(R.id.btnLogout);


        btnLogout.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                //   Toast.makeText(getApplicationContext(),App.getInstance().getstrNickname()+"ddd", Toast.LENGTH_LONG).show();
//                Toast.makeText(getApplicationContext(), "정상적으로 로그아웃되었습니다.", Toast.LENGTH_SHORT).show();
//
//                UserManagement.getInstance().requestLogout(new LogoutResponseCallback() {
//                    @Override
//                    public void onCompleteLogout() {
//                        Intent intent=new Intent(MainActivity.this, LoginActivity.class);
//                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                        startActivity(intent);
//                    }
//                });

                try {
                    String result = new HTTPLoadBoard().execute().get();

                    JSONArray arr = new JSONArray(result);

                    for(int i=0; i<arr.length(); i++){
                        JSONObject object = arr.getJSONObject(i);

                        int id = object.getInt("id");
                        String title = object.getString("title");
                        String image = object.getString("image");
                        String body = object.getString("body");
                        int pref = object.getInt("pref");


                        adapter.addItem(title, image, body);
                        adapter.notifyDataSetChanged();

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    /* 날씨 */
    LocationManager locationManager;
    // 현재 GPS 사용유무
    boolean isGPSEnabled=false;
    // 네트워크 사용유무
    boolean isNetworkEnabled=false;
    // GPS 상태값
    boolean isGetLocation=false;
    Location location;
    double lat; // 위도
    double lon; // 경도

    // 최소 GPS 정보 업데이트 거리 1000미터
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES=1000;

    // 최소 업데이트 시간 1분
    private static final long MIN_TIME_BW_UPDATES=1000 * 60 * 1;

    public Location getLocation() {
        try {
            //locationManager =(LocationManager) mContext.getSystemService(LOCATION_SERVICE);
            locationManager=(LocationManager) getSystemService(Context.LOCATION_SERVICE);

            // GPS 정보 가져오기
            isGPSEnabled=locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

            // 현재 네트워크 상태 값 알아오기
            isNetworkEnabled=locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

            if (!isGPSEnabled && !isNetworkEnabled) {
                // GPS 와 네트워크사용이 가능하지 않을때 소스 구현
            } else {
                this.isGetLocation=true;
                // 네트워크 정보로 부터 위치값 가져오기
               // Toast.makeText(getApplicationContext()," PERMISSION_GRddANTED: " +ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) , Toast.LENGTH_LONG).show();
                if (isNetworkEnabled) {
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        Toast.makeText(getApplicationContext(), "어플리케이션 설정에서 위치정보 승인을 해주세요 ", Toast.LENGTH_LONG).show();
                    }

                    locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, MIN_TIME_BW_UPDATES, MIN_DISTANCE_CHANGE_FOR_UPDATES, locationListener);
                            if (locationManager != null) {
                                location=locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                                if (location != null) {
                                    // 위도 경도 저장
                                    lat=location.getLatitude();
                                    lon=location.getLongitude();
                        }
                    }
                }

                if (isGPSEnabled) {
                    if (location == null) {
                        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                            // TODO: Consider calling
                            //    ActivityCompat#requestPermissions
                            // here to request the missing permissions, and then overriding
                            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                            //                                          int[] grantResults)
                            // to handle the case where the user grants the permission. See the documentation
                            // for ActivityCompat#requestPermissions for more details.
                            Toast.makeText(getApplicationContext(), "어플리케이션 설정에서 위치정보 승인을 해주세요 ", Toast.LENGTH_LONG).show();
                        }
                        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, MIN_TIME_BW_UPDATES, MIN_DISTANCE_CHANGE_FOR_UPDATES, locationListener);
                        if (locationManager != null) {
                            location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                            if (location != null) {
                                lat = location.getLatitude();
                                lon = location.getLongitude();
                            }
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return location;
    }

    private LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
//            Toast("onLocationChanged");
            //Toast.makeText(getApplicationContext(), "로케이션 정상적인 실행.", Toast.LENGTH_SHORT).show();
            getWeatherData( location.getLatitude() , location.getLongitude() );

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
//            Toast("onStatusChanged");
        }

        @Override
        public void onProviderEnabled(String provider) {
//            Toast("onProviderEnabled");
        }

        @Override
        public void onProviderDisabled(String provider) {
//            Toast("onProviderDisabled");
        }
    };

    private void getWeatherData( double lat, double lng ){

        String url = "http://api.openweathermap.org/data/2.5/weather?lat="+ lat + "&lon=" + lng +"&units=metric&appid=a5232b1ac90a1e61540f5594acdeb9af";

        ReceiveWeatherTask receiveUseTask = new ReceiveWeatherTask();
        receiveUseTask.execute(url);

    }

    private class ReceiveWeatherTask extends AsyncTask<String, Void, JSONObject> {

        @Override
        protected void onPreExecute() {

            super.onPreExecute();
        }

        @Override
        protected JSONObject doInBackground(String... datas) {
            //Toast.makeText(getApplicationContext(), "여기는가능", Toast.LENGTH_LONG).show();

           /* AsyncTask.execute(new Runnable() {
                @Override
                public void run() {

                }
            }); */

            try {
                //Toast.makeText(getApplicationContext(), "여기는가능", Toast.LENGTH_LONG).show();

                HttpURLConnection conn = (HttpURLConnection) new URL(datas[0]).openConnection();
                conn.setConnectTimeout(10000);
                conn.setReadTimeout(10000);
                conn.connect();

                if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    InputStream is = conn.getInputStream();
                    InputStreamReader reader = new InputStreamReader(is);
                    BufferedReader in = new BufferedReader(reader);

                    String readed;
                    while ((readed = in.readLine()) != null) {
                        JSONObject jObject = new JSONObject(readed);
                       // String result = jObject.getJSONArray("weather").getJSONObject(0).getString("icon");

                        return jObject;
                    }
                } else {
                    return null;
                }
                return null;
            } catch (Exception e) {
                // 에러 출력함수
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(JSONObject result){
              // Log.i("TAG", result.toString());
              //Toast.makeText(getApplicationContext(), "result: "+result, Toast.LENGTH_LONG).show();

            if( result != null ){

                String iconName = "";
                String nowTemp = "";
                String maxTemp = "";
                String minTemp = "";

                String humidity = "";
                String speed = "";
                String main = "";
                String description = "";

                try {
                    iconName = result.getJSONArray("weather").getJSONObject(0).getString("icon");
                    nowTemp = result.getJSONObject("main").getString("temp");
                    humidity = result.getJSONObject("main").getString("humidity");
                    minTemp = result.getJSONObject("main").getString("temp_min");
                    maxTemp = result.getJSONObject("main").getString("temp_max");
                    speed = result.getJSONObject("wind").getString("speed");
                    main = result.getJSONArray("weather").getJSONObject(0).getString("main");
                    description = result.getJSONArray("weather").getJSONObject(0).getString("description");

                }
                catch (JSONException e ){
                    e.printStackTrace();
                }
                description = transferWeather( description );
                //final String msg = description + " 습도 " + humidity +"%, 풍속 " + speed +"m/s" + " 온도 현재:"+nowTemp+" / 최저:"+ minTemp + " / 최고:" + maxTemp;
                msg = description + " 습도 " + humidity +"%, 풍속 " + speed +"m/s" + " 온도 현재:"+nowTemp+" / 최저:"+ minTemp + " / 최고:" + maxTemp;



            }

        }
    }

    private String transferWeather( String weather ){

        weather = weather.toLowerCase();

        if( weather.equals("haze") ){
            return "안개";
        }
        else if( weather.equals("fog") ){
            return "안개";
        }
        else if( weather.equals("clouds") ){
            return "구름";
        }
        else if( weather.equals("few clouds") ){
            return "구름 조금";
        }
        else if( weather.equals("scattered clouds") ){
            return "구름 낌";
        }
        else if( weather.equals("broken clouds") ){
            return "구름 많음";
        }
        else if( weather.equals("overcast clouds") ){
            return "구름 많음";
        }
        else if( weather.equals("clear sky") ){
            return "맑음";
        }

        return "";
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    public String getPref() {
        /* 내장 디비 pref 받아오기 */
        SQLiteDatabase db;
        handler = new DatabaseHandler(this);

        db = handler.getReadableDatabase();
        Cursor cursor = db.query("pref", new String[] {"code"}, null, null, null, null, null);

        String pref = "";
        while(cursor.moveToNext()){
            pref = cursor.getString(0);
        }

        System.out.println("asd: " + pref);

        cursor.close();
        handler.close();

        return pref;
    }

    private class HTTPLoadBoard extends AsyncTask<Void, Void, String> {

        private String url = "http://155.230.249.243:3000/board/loadAll/";

        @Override
        protected String doInBackground(Void... voids) {

            String pref = getPref();

            String result = "";

            try {
                URL url = new URL(this.url + pref);

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestProperty("Accept", "application/json");
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setRequestMethod("GET");

                conn.setDoInput(true);

                if(conn.getResponseCode() == 200) {

                    InputStream is = conn.getInputStream();
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    byte[] byteBuffer = new byte[1024];
                    byte[] byteData = null;
                    int nLength = 0;
                    while((nLength = is.read(byteBuffer, 0, byteBuffer.length)) != -1) {
                        baos.write(byteBuffer, 0, nLength);
                    }
                    byteData = baos.toByteArray();

                    result = new String(byteData);

                } else {
                    result = "Fail";
                }

                conn.disconnect();

            } catch (Exception e) {
                e.printStackTrace();
            }

            return result;
        }
    }
}

