package com.hasanaligultekin.soccerleague.model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TeamAPI {
    @GET("Teams/")
    Call<List<Team>> getTeams();
}
