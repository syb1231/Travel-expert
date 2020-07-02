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
    private ArrayList<Adapteritem> adapteritems = new ArrayList<Adapteritem>();

    public Adapter(Context context, String userid){
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

        Adapteritem item = adapteritems.get(position);

        textView1.setText(item.getTitle());
        textView2.setText(item.getBody());

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

                Adapteritem item = adapteritems.get(position);

                if(tb.isChecked()){

                    //Toast.makeText(context, "토글눌렀어1", Toast.LENGTH_LONG)';
                    try {
                        String json="";

                        JSONObject jsonObject=new JSONObject();
                        jsonObject.accumulate("userid", userid);
                        jsonObject.accumulate("code", item.getPref_code());

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
                        jsonObject.accumulate("code", item.getPref_code());

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

    public void addItem(String title, String body, String image, int pref_code){

        Adapteritem item = new Adapteritem();

        item.setTitle(title);
        item.setBody(body);
        item.setImage(image);
        item.setPref_code(pref_code);

        adapteritems.add(item);
    }

    private class PrefCodeRequest extends AsyncTask<String, Void, Integer> {

        private String url = "http://155.230.248.31:3000/pref/";
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
