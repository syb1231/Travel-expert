package com.example.login;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.viewpager.widget.PagerAdapter;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class Adapter extends PagerAdapter {

    private int[] images ={R.drawable.a,R.drawable.one,R.drawable.b,R.drawable.two,R.drawable.c,R.drawable.d,R.drawable.four,R.drawable.e,R.drawable.five,R.drawable.f,R.drawable.g};
    private LayoutInflater inflater;
    private Context context;
    private String userid;

    public  Adapter(Context context, String userid){
        this.context = context;
        this.userid = userid;
    }
    @Override
    public int getCount() {
        return images.length;

    }

    @Override
    public boolean isViewFromObject(View view,Object object) {
        return view ==((LinearLayout) object);
    }

    @Override
    public  Object instantiateItem(ViewGroup container, final int position){

        inflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.slider ,container,false);
        Button btn1=(Button)v.findViewById(R.id.button);
        ImageView imageView= (ImageView)v.findViewById(R.id.imageView);
        TextView textView=(TextView)v.findViewById(R.id.textView);
        TextView textView1=(TextView)v.findViewById(R.id.textView1);
        TextView textView2=(TextView)v.findViewById(R.id.textView2);
        imageView.setImageResource(images[position]);

        textView.setText("선호하는 여행지를 눌러주세요");
        switch(position){
            case 0:
                textView1.setText("경주 보문정");
                textView2.setText("#국내  #힐링  #관광  #역사");
                break;
            case 1:

                textView1.setText("일본 규슈");
                textView2.setText("#해외  #힐링  #온천 ");
                break;
            case 2:

                textView1.setText("광한루");
                textView2.setText("#국내 #역사 ");
                break;
            case 3:

                textView1.setText("조지아");
                textView2.setText("#해외  #배낭여행  #관광 #액티비티 ");
                break;
            case 4:

                textView1.setText("남해 가천 다랭이마을");
                textView2.setText("#국내 #바다 #힐링 ");
                break;
             case 5:

                textView1.setText(" 부산 광안대교");
                textView2.setText("#국내  #바다  #힐링  #야경");
                break;

            case 6:

                textView1.setText("코타키나발루(말레이시아)");
                textView2.setText("#해외 #바다  #힐링  #액티비티 ");
                break;
            case 7:

                textView1.setText("우도");
                textView2.setText("#국내  #바다  #힐링  #관광");
                break;
            case 8:

                textView1.setText("팔라우 우빈섬(싱가포르)");
                textView2.setText("#해외  #바다  #힐링 ");
                break;

            case 9:

                textView1.setText("세량제");
                textView2.setText("#국내  #바다  #힐링  #사진명소");
                break;

            default:

                textView1.setText("제주 협재 해수욕장");
                textView2.setText(" #국내  #바다  #힐링");
                break;


        }


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent().setClass(context,MainActivity.class);
                context.startActivity(intent);



            }
        });

        final ToggleButton tb = (ToggleButton)v.findViewById(R.id.price_count);
        tb.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(tb.isChecked()){
                    System.out.println("On : " + position);
                    //Toast.makeText(context, "토글눌렀어1", Toast.LENGTH_LONG)';
                    try {
                        String json="";

                        JSONObject jsonObject=new JSONObject();
                        jsonObject.accumulate("userid", userid);
                        jsonObject.accumulate("code", position);

                        json=jsonObject.toString();

                        PrefCodeRequest request=new PrefCodeRequest("/add");
                        int code = request.execute(json).get();

                        if(code == 200) {
                            tb.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.fill));
                        }
                    } catch (Exception e){
                        e.printStackTrace();
                    }

                }else{
                    //Toast.makeText(context, "토글눌렀어2", Toast.LENGTH_LONG);

                    try {
                        String json="";

                        JSONObject jsonObject=new JSONObject();
                        jsonObject.accumulate("userid", userid);
                        jsonObject.accumulate("code", position);

                        json=jsonObject.toString();

                        PrefCodeRequest request=new PrefCodeRequest("/delete");
                        int code = request.execute(json).get();

                        if(code == 200) {
                            tb.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.empty));
                        }
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                }

            }
        });


        container.addView(v);
        return v;
    }

    @Override
    public void destroyItem(ViewGroup container,int position ,Object object){
        container.invalidate();
        container.invalidate();
    }

    private class PrefCodeRequest extends AsyncTask<String, Void, Integer> {

        private String url = "http://155.230.248.161:3000/pref/";
        private String uri = "";

        public PrefCodeRequest(String uri) {
            this.uri = uri;
        }

        @Override
        protected Integer doInBackground(String... params) {

            int code = 404;

            try {
                URL url = new URL(this.url + this.uri);

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestProperty("Accept", "application/json");
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setRequestMethod("POST");

                conn.setDoInput(true);
                conn.setDoOutput(true);

                OutputStream os = conn.getOutputStream();
                os.write(params[0].getBytes("utf-8"));
                os.flush();

                code = conn.getResponseCode();

                conn.disconnect();

            } catch (Exception e) {
                e.printStackTrace();
            }

            return code;
        }
    }
}
