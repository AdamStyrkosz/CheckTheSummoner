package com.example.test_slider;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.squareup.picasso.Picasso;

public class SliderAdapter extends PagerAdapter {


    Context context;
    LayoutInflater layoutInflater;
    public Summoner summonerinfo;

    public SliderAdapter(Context context,Summoner s){
        summonerinfo = s;
        this.context = context;
    }


    public String[] slider_headings = {
            "1",
            "2"
    };



    @Override
    public int getCount() {
        return slider_headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (RelativeLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout, container , false);

        ImageView slideImageView = (ImageView) view.findViewById(R.id.imageView);
        TextView text1 = (TextView) view.findViewById(R.id.text1);
        TextView text2 = (TextView) view.findViewById(R.id.text2);
        ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        progressBar.setMax(100);

        String iconID = summonerinfo.getIconID();
        String[] firstline = {summonerinfo.getSOLO_tier() + " " + summonerinfo.getSOLO_rank(),summonerinfo.getFLEX_tier() + " "+ summonerinfo.getFLEX_rank()};
        String[] secondline = {summonerinfo.getSOLO_wins()+ " / "+ summonerinfo.getSOLO_losses(),summonerinfo.getFLEX_wins()+ " / "+ summonerinfo.getFLEX_losses()};
        int[] progressdata = {Integer.parseInt(summonerinfo.getSOLO_leaguePoints()),Integer.parseInt(summonerinfo.getFLEX_leaguePoints())};
        int temp1 = checkicon(summonerinfo.getSOLO_tier());
        int temp2 = checkicon(summonerinfo.getFLEX_tier());
        int[] icon = {temp1,temp2};


        text1.setText(firstline[position]);
        text2.setText(secondline[position]);
        slideImageView.setImageResource(icon[position]);
        progressBar.setProgress(progressdata[position]);

        container.addView(view);
        return view;
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((RelativeLayout)object);
    }

    public int checkicon (String tier){
        int s = 0;
        switch(tier)
        {
            case"IRON": s= (R.drawable.iron);
                break;
            case"BRONZE": s=  (R.drawable.bronze);
                break;
            case"SILVER": s= (R.drawable.silver);
                break;
            case"GOLD":s= (R.drawable.gold);
                break;
            case"PLATINUM":s=(R.drawable.platinum);
                break;
            case"DIAMOND":s= (R.drawable.diamond);
                break;
            case"MASTER":s= (R.drawable.master);
                break;
            case"GRANDMASTER": s= (R.drawable.grandmaster);
                break;
            case"CHALLENGER": s=(R.drawable.challenger);
                break;
            default:
               s=  (R.drawable.unranked);
        }
        return s;
    }

}
