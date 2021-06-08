package com.example.test_slider;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class MainActivity extends AppCompatActivity {

    private ViewPager mSlideViewPager;
    private SliderAdapter sliderAdapter;
    private Button button;
    public static ImageView imageView2,mastery1,mastery2,mastery3;
    public static TextView nickname,level,mastery1text,mastery2text,mastery3text;


    public static String API_KEY="?api_key=xxx";



    //onCreate => setFields
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent i = getIntent();
        Summoner summonerinfo = (Summoner) i.getSerializableExtra("summoner");

        setContentView(R.layout.activity_main);

        mSlideViewPager = (ViewPager) findViewById(R.id.testlinear);
        sliderAdapter = new SliderAdapter(this,summonerinfo);
        mSlideViewPager.setAdapter(sliderAdapter);
        imageView2 = (ImageView) findViewById(R.id.imageView2);
        nickname = (TextView) findViewById(R.id.nickname);
        level = (TextView) findViewById(R.id.level);

        mastery1 = (ImageView) findViewById(R.id.mastery1);
        mastery2 = (ImageView) findViewById(R.id.mastery2);
        mastery3 = (ImageView) findViewById(R.id.mastery3);
        mastery1text = (TextView) findViewById(R.id.mastery1text);
        mastery2text = (TextView) findViewById(R.id.mastery2text);
        mastery3text = (TextView) findViewById(R.id.mastery3text);



        championinfo championinfo = new championinfo();
        String m1 = championinfo.translateIdtoName(Integer.parseInt(summonerinfo.getMasteryId_1()));
        String m2 = championinfo.translateIdtoName(Integer.parseInt(summonerinfo.getMasteryId_2()));
        String m3 = championinfo.translateIdtoName(Integer.parseInt(summonerinfo.getMasteryId_3()));


        mastery1text.setText(summonerinfo.getMasteryPoints_1());
        mastery2text.setText(summonerinfo.getMasteryPoints_2());
        mastery3text.setText(summonerinfo.getMasteryPoints_3());



        Picasso.get().load("http://ddragon.leagueoflegends.com/cdn/10.16.1/img/champion/"+m1+".png").into(mastery1);
        Picasso.get().load("http://ddragon.leagueoflegends.com/cdn/10.16.1/img/champion/"+m2+".png").into(mastery2);
        Picasso.get().load("http://ddragon.leagueoflegends.com/cdn/10.16.1/img/champion/"+m3+".png").into(mastery3);
        Picasso.get().load("http://ddragon.leagueoflegends.com/cdn/10.16.1/img/profileicon/"+summonerinfo.getIconID()+".png").into(imageView2);
        nickname.setText(summonerinfo.getName());
        level.setText("Level: "+summonerinfo.getLevel());


    }

}
