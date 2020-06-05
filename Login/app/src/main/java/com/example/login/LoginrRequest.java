package com.example.login;


import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class LoginrRequest extends StringRequest {
    //서버 url 설정
    final static private  String URL ="http://tjdudqls9098.dothome.co.kr/Login.php";
    private Map<String,String> map;



    public LoginrRequest(String ID, String EMAIL, String AGE, Response.Listener<String> listener){
       super(Method.POST,URL,listener,null);

       map =new HashMap<>();
       map.put("ID",ID);
       map.put("EMAIL",EMAIL);
       map.put("AGE",AGE);;

    }

    @Override
    protected  Map<String,String> getParams() throws AuthFailureError{
        return map;
    }
}
