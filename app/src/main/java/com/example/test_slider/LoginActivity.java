package com.example.test_slider;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {



    public static Button login,buttoncont;
    public static EditText summonername;
    public static String API_KEY="?api_key=RGAPI-3dcb8f7e-ba24-4999-92d5-b1a7eb79df20";
    public Summoner summonerinfo;
    public Spinner spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginodjuli);

        login = (Button) findViewById(R.id.login);
        buttoncont = (Button) findViewById(R.id.buttoncont);
        summonername = (EditText) findViewById(R.id.editText);
        summonername.setText("Nahajek");

        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.region_array, R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                summonerinfo = new Summoner();
                fetchData fetchData = (com.example.test_slider.fetchData) new fetchData(summonername.getText().toString(), API_KEY, new fetchData.AsyncResponse() {
                    @Override
                    public void processFinish(Summoner output) {
                        summonerinfo = output;
                    }
                }).execute();
            }
        });

        buttoncont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity2();
            }
        });
    }

    public void openActivity2(){
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("summoner",summonerinfo);
        startActivity(intent);
    }
}
