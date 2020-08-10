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
    public static TextView test,masteryname1,masterypoints1;


    public static String API_KEY="?api_key=RGAPI-3dcb8f7e-ba24-4999-92d5-b1a7eb79df20";


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
        test = (TextView) findViewById(R.id.textView);
        mastery1 = (ImageView) findViewById(R.id.mastery1);
        mastery2 = (ImageView) findViewById(R.id.mastery2);
        mastery3 = (ImageView) findViewById(R.id.mastery3);
        masteryname1 = (TextView) findViewById(R.id.masteryname1);
        masterypoints1 = (TextView) findViewById(R.id.masterypoints1);

        championinfo championinfo = new championinfo();
        String m1 = championinfo.translateIdtoName(Integer.parseInt(summonerinfo.getMasteryId_1()));
        String m2 = championinfo.translateIdtoName(Integer.parseInt(summonerinfo.getMasteryId_2()));
        String m3 = championinfo.translateIdtoName(Integer.parseInt(summonerinfo.getMasteryId_3()));

        masteryname1.setText(m1);
        masterypoints1.setText(summonerinfo.getMasteryPoints_1());



        Picasso.get().load("http://ddragon.leagueoflegends.com/cdn/10.9.1/img/champion/"+m1+".png").into(mastery1);
        Picasso.get().load("http://ddragon.leagueoflegends.com/cdn/10.9.1/img/champion/"+m2+".png").into(mastery2);
        Picasso.get().load("http://ddragon.leagueoflegends.com/cdn/10.9.1/img/champion/"+m3+".png").into(mastery3);
        Picasso.get().load("http://ddragon.leagueoflegends.com/cdn/10.9.1/img/profileicon/"+summonerinfo.getIconID()+".png").into(imageView2);
        test.setText(summonerinfo.getName() + "\n"+ summonerinfo.getLevel());


    }

}
