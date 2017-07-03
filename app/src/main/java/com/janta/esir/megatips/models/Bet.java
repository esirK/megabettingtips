package com.janta.esir.megatips.models;

import java.util.Date;

/**
 * Created by esir on 03/07/2017.
 */

public class Bet {

    private Team TeamA;
    private Team TeamB;
    private boolean favourite;
    private String leauge;
    private Date date;
    private String Tip;
    private float odds;
    private String results;

    public void setTeamA(Team a){
        this.TeamA = a;
    }
    public Team getTeamA(){
        return TeamA;
    }
    public void setTeamB(Team b){
        this.TeamB = b;
    }
    public Team getTeamB(){
        return TeamB;
    }
    public void setLeauge(String leauge){
        this.leauge = leauge;
    }
    public String getLeauge(){
        return leauge;
    }
    public void setFavourite(boolean favourite){
        this.favourite = favourite;
    }
    public boolean getFavourite(){
        return favourite;
    }
    public void setTip(String tip){
        this.Tip = tip;
    }
    public String getTip(){
        return Tip;
    }
    public void setDate(Date date){
        this.date = date;
    }
    public Date getDate(){
        return date;
    }
    public void setOdds(float odds){
        this.odds = odds;
    }
    public float getOdds(){
        return odds;
    }
    public void setResults(String results){
        this.results = results;
    }
    public String getResults(){
        return results;
    }
}
