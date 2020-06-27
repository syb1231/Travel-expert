package com.example.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.zip.Inflater;

import static android.content.Intent.getIntent;
import static androidx.core.content.ContextCompat.startActivity;


public class Adapter extends PagerAdapter {

    private int[] images ={R.drawable.one,R.drawable.two,R.drawable.three};
    private  LayoutInflater inflater;
    private  Context context;
    public  Adapter(Context context){
        this.context =context;
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
    public  Object instantiateItem(ViewGroup container, int position){

        inflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.slider ,container,false);
        Button btn1=(Button)v.findViewById(R.id.button);
        ImageView imageView= (ImageView)v.findViewById(R.id.imageView);
        TextView textView=(TextView)v.findViewById(R.id.textView);
        TextView textView1=(TextView)v.findViewById(R.id.textView1);
        imageView.setImageResource(images[position]);
        textView.setText("선호하는 여행지를 눌러주세요");
        if(position==0)
                textView1.setText("OO워터파크");
       if(position==1)
                textView1.setText("OO생태공원");
       if(position==2)
                textView1.setText("OO해수욕장");
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
                    tb.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.fill));
                    Toast.makeText(context, "토글눌렀어1", Toast.LENGTH_LONG);
                }else{
                    tb.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.empty));
                    Toast.makeText(context, "토글눌렀어2", Toast.LENGTH_LONG);
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

}
