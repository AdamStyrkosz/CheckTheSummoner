package com.example.test_slider;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class fetchData  extends AsyncTask<Void,Void,Summoner> {

    public interface AsyncResponse{
        void processFinish(Summoner output);
    }

    public AsyncResponse delegate = null;
    public ArrayList<HashMap<String,String>> masterypoints = new ArrayList<>();
    public HashMap<String,String> accountinfo = new HashMap<>();
    public ArrayList<HashMap<String,String>> mainList = new ArrayList<HashMap<String, String>>();
    HashMap<String,String> parsedInfo;
    String api_key="";
    String sumname;
    String data = "[";
    Summoner summoner;

    //do testow na stale eun1
    String region = "eun1";


    int code;

    public fetchData(String s,String api,String regionx,AsyncResponse delegate){
        this.delegate = delegate;
        this.region = regionx;
        sumname = s;
        api_key = api;
    }


    @SuppressLint("WrongThread")
    @Override
    protected Summoner doInBackground(Void... voids) {

        try {
            URL url = new URL ("https://"+region+".api.riotgames.com/lol/summoner/v4/summoners/by-name/"+sumname+api_key);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            code = httpURLConnection.getResponseCode();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while(line != null){
                line = bufferedReader.readLine();
                data = data + line;
            }
            data = data.substring(0,data.length()-4);
            data = data+ "]";
            JSONArray JD = new JSONArray(data);
            for(int i = 0;i<= JD.length();i++){
                JSONObject JO = (JSONObject) JD.get(i);
                accountinfo.put("encryptedSumID",JO.get("id").toString());
                accountinfo.put("name",JO.get("name").toString());
                accountinfo.put("level",JO.get("summonerLevel").toString());
                accountinfo.put("iconID",JO.get("profileIconId").toString());
                accountinfo.put("text","Name:" +  accountinfo.get("name") + "\n" + "Level:" +  accountinfo.get("level") + "\n");
                mainList.add(accountinfo);

            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if(code==200) {
            String data2 = "";
            URL url2 = null;
            try {
                url2 = new URL("https://"+region+".api.riotgames.com/lol/league/v4/entries/by-summoner/" + mainList.get(0).get("encryptedSumID") + api_key);
                HttpURLConnection httpURLConnection2 = (HttpURLConnection) url2.openConnection();
                code = httpURLConnection2.getResponseCode();
                InputStream inputStream2 = httpURLConnection2.getInputStream();
                BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(inputStream2));
                String line2 = "";
                while (line2 != null) {
                    line2 = bufferedReader2.readLine();
                    data2 = data2 + line2;
                }
                JSONArray JD2 = new JSONArray(data);
                JD2 = new JSONArray(data2);
                for (int i = 0; i <= JD2.length(); i++) {

                    JSONObject JO = (JSONObject) JD2.get(i);

                    HashMap<String, String> tempMap = new HashMap<String, String>();

                    tempMap.put("queueType", JO.get("queueType").toString());
                    tempMap.put("tier", JO.get("tier").toString());
                    tempMap.put("rank", JO.get("rank").toString());
                    tempMap.put("wins", JO.get("wins").toString());
                    tempMap.put("losses", JO.get("losses").toString());
                    tempMap.put("leaguePoints", JO.get("leaguePoints").toString());
                    mainList.add(tempMap);
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
                try {

                    String data3 = "";
                    URL url3 = new URL("https://"+region+".api.riotgames.com/lol/champion-mastery/v4/champion-masteries/by-summoner/" + mainList.get(0).get("encryptedSumID") + api_key);
                    HttpURLConnection httpURLConnection3 = (HttpURLConnection) url3.openConnection();
                    InputStream inputStream3 = httpURLConnection3.getInputStream();
                    BufferedReader bufferedReader3 = new BufferedReader(new InputStreamReader(inputStream3));
                    String line5 = "";
                    while (line5 != null) {
                        line5 = bufferedReader3.readLine();
                        data3 = data3 + line5;
                    }
                    JSONArray JD3 = new JSONArray(data3);
                    for (int i = 0; i < 3; i++) {
                        JSONObject JO = (JSONObject) JD3.get(i);
                        HashMap<String, String> tempMap2 = new HashMap<String, String>();
                        tempMap2.put("championId", JO.get("championId").toString());
                        tempMap2.put("championPoints", JO.get("championPoints").toString());
                        tempMap2.put("championLevel", JO.get("championLevel").toString());
                        masterypoints.add(tempMap2);
                    }


                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

        }
        if(code==200)checklist();
        return summoner;
    }


    @Override
    protected void onPostExecute(Summoner result) {
        delegate.processFinish(result);
        if(code==200) {
            LoginActivity.buttoncont.setVisibility(View.VISIBLE);
            LoginActivity.login.setText("Search again");
        }
        else {
            LoginActivity.buttoncont.setVisibility(View.INVISIBLE);
            LoginActivity.login.setText("Incorrect nickname");
        }

    }

    public void checklist()
    {

            switch (mainList.size()) {
                case 2:
                    if (mainList.get(1).get("queueType").equals("RANKED_SOLO_5x5")) {

                        summoner = new Summoner(mainList.get(0).get("name"), mainList.get(0).get("level"), mainList.get(0).get("iconID"), mainList.get(1).get("tier"),
                                mainList.get(1).get("rank"), mainList.get(1).get("wins"), mainList.get(1).get("losses"), "unranked", "", "0", "0", mainList.get(1).get("leaguePoints"), "0");
                        summoner.setMastery(masterypoints.get(0).get("championId"),masterypoints.get(1).get("championId"),masterypoints.get(2).get("championId"),masterypoints.get(0).get("championLevel"),
                                masterypoints.get(1).get("championLevel"),masterypoints.get(2).get("championLevel"),masterypoints.get(0).get("championPoints"),masterypoints.get(1).get("championPoints"),masterypoints.get(2).get("championPoints"));

                    } else {
                        summoner = new Summoner(mainList.get(0).get("name"), mainList.get(0).get("level"), mainList.get(0).get("iconID"), "unranked", "", "0", "0", mainList.get(1).get("tier"),
                                mainList.get(1).get("rank"), mainList.get(1).get("wins"), mainList.get(1).get("losses"), "0", mainList.get(1).get("leaguePoints"));
                        summoner.setMastery(masterypoints.get(0).get("championId"),masterypoints.get(1).get("championId"),masterypoints.get(2).get("championId"),masterypoints.get(0).get("championLevel"),
                                masterypoints.get(1).get("championLevel"),masterypoints.get(2).get("championLevel"),masterypoints.get(0).get("championPoints"),masterypoints.get(1).get("championPoints"),masterypoints.get(2).get("championPoints"));

                    }
                    break;
                case 3:
                    if (mainList.get(1).get("queueType").equals("RANKED_SOLO_5x5")) {

                        summoner = new Summoner(mainList.get(0).get("name"), mainList.get(0).get("level"), mainList.get(0).get("iconID"), mainList.get(1).get("tier"),
                                mainList.get(1).get("rank"), mainList.get(1).get("wins"), mainList.get(1).get("losses"), mainList.get(2).get("tier"),
                                mainList.get(2).get("rank"), mainList.get(2).get("wins"), mainList.get(2).get("losses"), mainList.get(1).get("leaguePoints"), mainList.get(2).get("leaguePoints"));
                        summoner.setMastery(masterypoints.get(0).get("championId"),masterypoints.get(1).get("championId"),masterypoints.get(2).get("championId"),masterypoints.get(0).get("championLevel"),
                                masterypoints.get(1).get("championLevel"),masterypoints.get(2).get("championLevel"),masterypoints.get(0).get("championPoints"),masterypoints.get(1).get("championPoints"),masterypoints.get(2).get("championPoints"));

                    } else {
                        summoner = new Summoner(mainList.get(0).get("name"), mainList.get(0).get("level"), mainList.get(0).get("iconID"), mainList.get(2).get("tier"),
                                mainList.get(2).get("rank"), mainList.get(2).get("wins"), mainList.get(2).get("losses"), mainList.get(1).get("tier"),
                                mainList.get(1).get("rank"), mainList.get(1).get("wins"), mainList.get(1).get("losses"), mainList.get(2).get("leaguePoints"), mainList.get(1).get("leaguePoints"));
                        summoner.setMastery(masterypoints.get(0).get("championId"),masterypoints.get(1).get("championId"),masterypoints.get(2).get("championId"),masterypoints.get(0).get("championLevel"),
                                masterypoints.get(1).get("championLevel"),masterypoints.get(2).get("championLevel"),masterypoints.get(0).get("championPoints"),masterypoints.get(1).get("championPoints"),masterypoints.get(2).get("championPoints"));

                    }
                    break;
                default:
                    summoner = new Summoner(mainList.get(0).get("name"), mainList.get(0).get("level"), mainList.get(0).get("iconID"),
                            "unranked", "", "0", "0", "unranked", "", "0", "0", "0", "0");
                    summoner.setMastery(masterypoints.get(0).get("championId"),masterypoints.get(1).get("championId"),masterypoints.get(2).get("championId"),masterypoints.get(0).get("championLevel"),
                            masterypoints.get(1).get("championLevel"),masterypoints.get(2).get("championLevel"),masterypoints.get(0).get("championPoints"),masterypoints.get(1).get("championPoints"),masterypoints.get(2).get("championPoints"));

                    break;
            }

    }


}
