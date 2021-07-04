package com.hasanaligultekin.soccerleague.model;

public class Fixture {

    String homeTeam;
    String awayTeam;

    public Fixture(String homeTeam, String awayTeam){
        this.homeTeam=homeTeam;
        this.awayTeam=awayTeam;
    }

    public String getHomeTeam(){ return homeTeam; }
    public void setHomeTeam(String homeTeam){ this.homeTeam=homeTeam; }

    public String getAwayTeam(){ return awayTeam; }
    public void  setAwayTeam(String awayTeam){ this.awayTeam=awayTeam; }

}
