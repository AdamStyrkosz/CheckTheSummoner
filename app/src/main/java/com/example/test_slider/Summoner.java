package com.example.test_slider;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Summoner implements Serializable {
    private String name,level,iconID,SOLO_tier,SOLO_rank,SOLO_wins,SOLO_losses,FLEX_tier,FLEX_rank,FLEX_wins,FLEX_losses,SOLO_leaguePoints,FLEX_leaguePoints;
    private String masteryId_1,masteryId_2,masteryId_3,masteryPoints_1,masteryPoints_2,masteryPoints_3,masteryLevel_1,masteryLevel_2,masteryLevel_3;


    public Summoner(String name, String level, String iconID, String solo_tier, String solo_rank, String solo_wins, String solo_losses, String flex_tier, String flex_rank, String flex_wins, String flex_losses, String solo_leaguePoints, String flex_leaguePoints) {
        this.name = name;
        this.level = level;
        this.iconID = iconID;
        SOLO_tier = solo_tier;
        SOLO_rank = solo_rank;
        SOLO_wins = solo_wins;
        SOLO_losses = solo_losses;
        FLEX_tier = flex_tier;
        FLEX_rank = flex_rank;
        FLEX_wins = flex_wins;
        FLEX_losses = flex_losses;
        SOLO_leaguePoints = solo_leaguePoints;
        FLEX_leaguePoints = flex_leaguePoints;
        masteryId_1="0";
        masteryId_2="0";
        masteryId_3="0";
        masteryLevel_1="0";
        masteryLevel_2="0";
        masteryLevel_3="0";
        masteryPoints_1="0";
        masteryPoints_2="0";
        masteryPoints_3="0";

    }


    public String getName() {
        return name;
    }

    public String getLevel() {
        return level;
    }

    public String getIconID() {
        return iconID;
    }

    public String getSOLO_tier() {
        return SOLO_tier;
    }

    public String getSOLO_rank() {
        return SOLO_rank;
    }

    public String getSOLO_wins() {
        return SOLO_wins;
    }

    public String getSOLO_losses() {
        return SOLO_losses;
    }

    public String getFLEX_tier() {
        return FLEX_tier;
    }

    public String getFLEX_rank() {
        return FLEX_rank;
    }

    public String getFLEX_wins() {
        return FLEX_wins;
    }

    public String getFLEX_losses() {
        return FLEX_losses;
    }

    public String getSOLO_leaguePoints() {
        return SOLO_leaguePoints;
    }

    public String getFLEX_leaguePoints() {
        return FLEX_leaguePoints;
    }

    public void setMastery(String masteryId_1, String masteryId_2, String masteryId_3, String masteryLevel_1,String masteryLevel_2,String masteryLevel_3,String masteryPoints_1,String masteryPoints_2, String masteryPoints_3){
        this.masteryId_1 = masteryId_1;
        this.masteryId_2 = masteryId_2;
        this.masteryId_3 = masteryId_3;
        this.masteryLevel_1 = masteryLevel_1;
        this.masteryLevel_2 = masteryLevel_2;
        this.masteryLevel_3 = masteryLevel_3;
        this.masteryPoints_1 = masteryPoints_1;
        this.masteryPoints_2 = masteryPoints_2;
        this.masteryPoints_3 = masteryPoints_3;
    }


    public String getMasteryId_1() {
        return masteryId_1;
    }

    public String getMasteryId_2() {
        return masteryId_2;
    }

    public String getMasteryId_3() {
        return masteryId_3;
    }

    public String getMasteryPoints_1() {
        return masteryPoints_1;
    }

    public String getMasteryPoints_2() {
        return masteryPoints_2;
    }

    public String getMasteryPoints_3() {
        return masteryPoints_3;
    }

    public String getMasteryLevel_1() {
        return masteryLevel_1;
    }

    public String getMasteryLevel_2() {
        return masteryLevel_2;
    }

    public String getMasteryLevel_3() {
        return masteryLevel_3;
    }
}
