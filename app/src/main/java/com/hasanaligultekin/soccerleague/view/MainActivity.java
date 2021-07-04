package com.hasanaligultekin.soccerleague.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.hasanaligultekin.soccerleague.R;
import com.hasanaligultekin.soccerleague.model.Team;
import com.hasanaligultekin.soccerleague.model.TeamAPI;
import com.hasanaligultekin.soccerleague.model.TeamAPIService;
import com.hasanaligultekin.soccerleague.viewmodel.RecyclerAdapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    List<Team> teamList;
    RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;
    Button draw_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        overridePendingTransition(
                R.anim.enter, R.anim.exit);
        draw_btn=findViewById(R.id.draw_fixture);
        teamList = new ArrayList<Team>();
        recyclerView=(RecyclerView) findViewById(R.id.recyclerview);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerAdapter=new RecyclerAdapter(getApplicationContext(), teamList);
        recyclerView.setAdapter(recyclerAdapter);


        TeamAPI apiService= TeamAPIService.getClient().create(TeamAPI.class);
        Call<List<Team>> call=apiService.getTeams();
        call.enqueue(new Callback<List<Team>>() {
            @Override
            public void onResponse(Call<List<Team>> call, Response<List<Team>> response) {
                teamList =response.body();
                Log.d("TAG","Response = "+ teamList);
                recyclerAdapter.setTeamAPIList(teamList);
            }

            @Override
            public void onFailure(Call<List<Team>> call, Throwable t) {
                Log.d("TAG","Response = "+t.toString());
            }
        });

        draw_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent drawIntent=new Intent(MainActivity.this,DrawActivity.class);

                drawIntent.putExtra("list",(Serializable) teamList);
                startActivity(drawIntent);
            }
        });

    }

    public void onStop () {
        super.onStop();
        overridePendingTransition(
                R.anim.enter, R.anim.exit);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        overridePendingTransition(
                R.anim.enter, R.anim.exit);
    }

    @Override
    protected void onStart() {
        super.onStart();
        overridePendingTransition(
                R.anim.enter, R.anim.exit);
    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(
                R.anim.enter, R.anim.exit);
    }

    // https://60dcb89ec2b6280017febc29.mockapi.io/api/
}