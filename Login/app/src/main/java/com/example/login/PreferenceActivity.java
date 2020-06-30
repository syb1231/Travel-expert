package com.example.login;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;


public class PreferenceActivity extends AppCompatActivity {
    String strNickname, strProfile, strEmail, strAgeRange, strGender, strBirthday;
     Adapter adapter;
     ViewPager viewPager;
     @Override
    protected  void onCreate(Bundle savedInstanceState){
         Intent intent1 =  getIntent();
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_preference);

         Intent intent =  getIntent();
         App myApp = (App) getApplication();
         //이름.이메일, 나잇대, 성별, 생일을 intent에서 가져와서 각 String에 저장함
         strNickname = intent.getStringExtra("name");
         strProfile = intent.getStringExtra("profile");
         strEmail = intent.getStringExtra("email");
         strAgeRange = intent.getStringExtra("ageRange");
         strGender = intent.getStringExtra("gender");

         viewPager =(ViewPager) findViewById(R.id.view);
         adapter = new Adapter(this, strNickname);
         viewPager.setAdapter(adapter);



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
