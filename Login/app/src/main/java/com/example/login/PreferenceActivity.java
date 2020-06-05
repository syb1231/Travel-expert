package com.example.login;



import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.kakao.util.OptionalBoolean;


public class PreferenceActivity extends AppCompatActivity {
    String strNickname, strProfile, strEmail, strAgeRange, strGender, strBirthday;
     Adapter adapter;
     ViewPager viewPager;
     @Override
    protected  void onCreate(Bundle savedInstanceState){
         Intent intent1 =  getIntent();
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_preference);
         viewPager =(ViewPager) findViewById(R.id.view);

         adapter = new Adapter(this);
         viewPager.setAdapter(adapter);

         Intent intent =  getIntent();
         App myApp = (App) getApplication();
         //이름.이메일, 나잇대, 성별, 생일을 intent에서 가져와서 각 String에 저장함
         strNickname = intent.getStringExtra("name");
         strProfile = intent.getStringExtra("profile");
         strEmail = intent.getStringExtra("email");
         strAgeRange = intent.getStringExtra("ageRange");
         strGender = intent.getStringExtra("gender");

// 전역변수 설정
         App.getInstance().setstrNickname(strNickname);
         App.getInstance().setstrProfile(strProfile);
         App.getInstance().setstrEmail(strEmail);
         App.getInstance().setstrAgeRange(strAgeRange);
         App.getInstance().setstrGender(strGender);

// 전역변수 값 받아오기
         String globalstrNickname=  App.getInstance().getstrNickname();
         String globalstrProfile=  App.getInstance().getstrProfile();
         String globalstrEmail=  App.getInstance().getstrEmail();
         String globalstrAgeRange=  App.getInstance().getstrAgeRange();
         String globalstrGender=  App.getInstance().getstrGender();

        strNickname = intent.getStringExtra("name");
        strProfile = intent.getStringExtra("profile");
        //이메일, 나잇대, 성별, 생일을 intent에서 가져와서 각 String에 저장함
        strEmail = intent.getStringExtra("email");
        strAgeRange = intent.getStringExtra("ageRange");
        strGender = intent.getStringExtra("gender");


     }


}
