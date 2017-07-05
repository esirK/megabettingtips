package com.janta.esir.megatips.models;


/**
 * Created by esir on 03/07/2017.
 */

public class Bet {

    private String TeamA;
    private String TeamB;
    private boolean favourite;
    private String leauge;
    private String date;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    private String time;
    private String Tip;
    private float odds;
    private String results;
    private String teamAFlag;
    private String teamBFlag;

    public String getLeaugeFlag() {
        return leaugeFlag;
    }

    public void setLeaugeFlag(String leaugeFlag) {
        this.leaugeFlag = leaugeFlag;
    }

    private String leaugeFlag;

    public void setTeamA(String a){
        this.TeamA = a;
    }
    public String getTeamA(){
        return TeamA;
    }
    public void setTeamB(String b){
        this.TeamB = b;
    }
    public String getTeamB(){
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
    public void setDate(String date){
        this.date = date;
    }
    public String getDate(){
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

    public String getTeamBFlag() {
        return teamBFlag;
    }

    public void setTeamBFlag(String teamBFlag) {
        this.teamBFlag = teamBFlag;
    }

    public String getTeamAFlag() {
        return teamAFlag;
    }

    public void setTeamAFlag(String teamAFlag) {
        this.teamAFlag = teamAFlag;
    }
}
