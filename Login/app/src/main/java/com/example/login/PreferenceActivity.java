package com.example.login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

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

         adapter.addItem("경주 보문정", "#국내  #유적지  #관광", "a", 3);
         adapter.addItem("일본 규슈", "#해외  #힐링  #사진명소 " , "one", 4);
         adapter.addItem("광한루", "#국내  #유적지  #야경", "b", 0);
         adapter.addItem("조지아", "#해외  #사진명소  #관광  #액티비티", "two", 0);
         adapter.addItem("남해 가천 다랭이 마을", "#국내  #바다  #힐링", "c", 5);
         adapter.addItem("부산 광안대교", "#국내  #바다  #힐링  #야경", "three", 6);
         adapter.addItem("말레이시아 코타키나발루", "#해외  #바다  #힐링  #액티비티", "d", 1);
         adapter.addItem("우도", "#국내  #바다  #힐링  #관광", "four", 7);
         adapter.addItem("싱가포르 팔라우 우빈섬", "#해외  #바다  #힐링", "e", 8);
         adapter.addItem("세량제", "#국내   #힐링  #사진명소", "f", 9);
         adapter.addItem("제주 협재 해수욕장", "#국내  #바다  #힐링", "g", 1);





         Toast.makeText(getApplicationContext(), strNickname+"님 환영합니다!", Toast.LENGTH_LONG).show();
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
