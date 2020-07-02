package com.example.login;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ReplyActivity extends AppCompatActivity {

    ListView listView;
    Button btn_reply;
    EditText et_reply;
    ReplyAdapter adapter;
    String board_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reply);

        btn_reply = findViewById(R.id.btnReply);
        et_reply = findViewById(R.id.et_reply);
        listView = findViewById(R.id.reply_listview);
        adapter = new ReplyAdapter();
        listView.setAdapter(adapter);

        Intent intent =  getIntent();
        board_id = intent.getStringExtra("board_id");

        try{

            String result = new HTTPReplyLoad(board_id).execute().get();
            JSONArray arr = new JSONArray(result);

            for(int i=0; i<arr.length(); i++){
                JSONObject object = arr.getJSONObject(i);

                String author = object.getString("author");
                String body = object.getString("body");

                adapter.addItem(author, body);
            }


        }catch(Exception e){
            e.printStackTrace();
        }

        btn_reply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.accumulate("board_id", board_id);
                    jsonObject.accumulate("body", et_reply.getText().toString());
                    jsonObject.accumulate("author", App.getInstance().getstrNickname());

                    String json = jsonObject.toString();

                    new HTTPReplyUpload().execute(json);
                }catch(Exception e){
                    e.printStackTrace();
                }

                adapter.addItem(App.getInstance().getstrNickname(), et_reply.getText().toString());
                et_reply.setText("");
                adapter.notifyDataSetChanged();

            }
        });





//        adapter.addItem("Asd", "ASD");
//        adapter.addItem("Asd", "ASD");
//        adapter.addItem("Asd", "ASD");



    }

    private class HTTPReplyLoad extends  AsyncTask<Void, Void, String>{

        private String url = "http:///155.230.248.31:3000/reply/loadAll/";
        private String board_id;

        public HTTPReplyLoad(String board_id){
            this.board_id = board_id;
        }

        @Override
        protected String doInBackground(Void... voids) {

            String result = "";
            try{
                URL url = new URL(this.url+this.board_id);
                HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                conn.setRequestProperty("Accept", "application/json");
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setRequestMethod("GET");

                System.out.println("code" + conn.getResponseCode());

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


            }catch(Exception e){
                e.printStackTrace();
            }

            return result;
        }
    }

    private class HTTPReplyUpload extends AsyncTask<String, Void, Void>{

        private String url = "http://155.230.248.31:3000/reply/upload";
        @Override
        protected Void doInBackground(String... params) {

            try{
                URL url = new URL(this.url);

                HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                conn.setRequestProperty("Accept", "application/json");
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setRequestMethod("POST");

                OutputStream os = conn.getOutputStream();
                os.write(params[0].getBytes("utf-8"));
                os.flush();
                System.out.println("code : " + conn.getResponseCode());

                conn.disconnect();

            }catch(Exception e){
                e.printStackTrace();
            }

            return null;
        }
    }
}
