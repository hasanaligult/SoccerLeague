package com.hasanaligultekin.soccerleague.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Team implements Serializable {

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("team_name")
    @Expose
    private String teamName;

    public String getId() { return id; }

    public void setId(String id) { this.id=id; }

    public String getTeamName() { return teamName; }

    public void setTeamName(String teamName) { this.teamName=teamName; }

}
