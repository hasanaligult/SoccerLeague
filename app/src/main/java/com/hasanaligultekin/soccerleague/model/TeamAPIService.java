package com.hasanaligultekin.soccerleague.model;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TeamAPIService {

    public static String BASE_URL ="https://60dcb89ec2b6280017febc29.mockapi.io/api/";
    private static Retrofit retrofit;
    public static Retrofit getClient(){
        if(retrofit==null){
            retrofit=new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }


}
