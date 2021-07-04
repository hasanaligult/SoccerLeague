package com.hasanaligultekin.soccerleague.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.transition.Slide;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.Gravity;
import android.widget.Button;
import android.widget.TextView;

import com.hasanaligultekin.soccerleague.R;
import com.hasanaligultekin.soccerleague.model.Fixture;
import com.hasanaligultekin.soccerleague.model.Team;
import com.hasanaligultekin.soccerleague.model.TeamAPI;
import com.hasanaligultekin.soccerleague.model.TeamAPIService;
import com.hasanaligultekin.soccerleague.viewmodel.FixtureCreate;
import com.hasanaligultekin.soccerleague.viewmodel.FixtureViewHolder;
import com.hasanaligultekin.soccerleague.viewmodel.OnSwipeTouchListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.FormatFlagsConversionMismatchException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DrawActivity extends AppCompatActivity {
    List<Team> teamList;
    List<String> team_name;
    List<Fixture> fixture;

    RecyclerView rv;
    Button nextBtn, prevBtn;
    private int TOTAL_NUM_ITEMS;
    private int ITEMS_PER_PAGE;
    private int ITEMS_REMAINING;
    private int LAST_PAGE;
    private int currentPage = 0;
    private int totalPages;
    TextView week ;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw);
        overridePendingTransition(
                R.anim.enter, R.anim.exit);

        fixture=new ArrayList<>();
        week=findViewById(R.id.week_count);
        Intent intent=getIntent();
        ArrayList<Team> list=(ArrayList<Team>) intent.getSerializableExtra("list");
        Collections.shuffle(list);
        week.setText("Week "+String.valueOf(currentPage+1));
        TeamAPI apiService=TeamAPIService.getClient().create(TeamAPI.class);
        Call<List<Team>> call=apiService.getTeams();
        call.enqueue(new Callback<List<Team>>() {
            @Override
            public void onResponse(Call<List<Team>> call, Response<List<Team>> response) {
                teamList=response.body();
            }

            @Override
            public void onFailure(Call<List<Team>> call, Throwable t) {
                Log.d("TAG", "Response = "+t.toString());
            }
        });
        team_name=new ArrayList<String>();

        for(int c=0; c<list.size(); c++){
            team_name.add(list.get(c).getTeamName());
        }

        FixtureCreate fixtureCreate=new FixtureCreate();
        List<List<Fixture>> rounds=(List<List<Fixture>>) fixtureCreate.getFixtures(team_name,true);

        for (int i=0; i<rounds.size(); i++){
            List<Fixture> round=(List<Fixture>) rounds.get(i);

            for(Fixture fixtures: round){
                fixture.add(fixtures);
            }
        }

        TOTAL_NUM_ITEMS = fixture.size();
        ITEMS_PER_PAGE = fixture.size() / rounds.size();
        ITEMS_REMAINING = TOTAL_NUM_ITEMS % ITEMS_PER_PAGE;
        LAST_PAGE = TOTAL_NUM_ITEMS/ITEMS_PER_PAGE;
        totalPages = TOTAL_NUM_ITEMS / ITEMS_PER_PAGE;

        rv=(RecyclerView) findViewById(R.id.rv_fixture);

        rv.setLayoutManager((new LinearLayoutManager(this)));

        rv.setAdapter(new FixtureViewHolder(DrawActivity.this, generatePage(currentPage)));

        rv.setOnTouchListener(new OnSwipeTouchListener(this) {
            public void onSwipeRight(){
                if(currentPage==totalPages){
                    currentPage -=1;
                    week.setText("Week "+String.valueOf(currentPage+1));
                    Slide slide=new Slide();
                    slide.setSlideEdge(Gravity.END);
                    TransitionManager.beginDelayedTransition(rv,slide);
                    rv.setAdapter(new FixtureViewHolder(DrawActivity.this, generatePage(currentPage)));
                }else if(currentPage >=1 && currentPage < totalPages){
                    currentPage-=1;
                    week.setText("Week "+String.valueOf(currentPage+1));
                    androidx.transition.Slide slide = new androidx.transition.Slide();
                    slide.setSlideEdge(Gravity.END);
                    androidx.transition.TransitionManager.beginDelayedTransition(rv, slide);
                    rv.setAdapter(new FixtureViewHolder(DrawActivity.this, generatePage(currentPage)));
                }else  {

                }
            }
            public void onSwipeLeft() {
                if (currentPage == 0) {
                    currentPage += 1;
                    week.setText("Week "+String.valueOf(currentPage+1));

                    androidx.transition.Slide slide = new androidx.transition.Slide();
                    slide.setSlideEdge(Gravity.START);
                    androidx.transition.TransitionManager.beginDelayedTransition(rv, slide);

                    rv.setAdapter(new FixtureViewHolder(DrawActivity.this, generatePage(currentPage)));

                }else if (currentPage >= 1 && currentPage < totalPages) {
                    currentPage += 1;
                    if(currentPage == totalPages){
                        week.setText("Week "+String.valueOf(currentPage));
                    }else{
                        week.setText("Week "+String.valueOf(currentPage+1));
                    }

                    androidx.transition.Slide slide = new androidx.transition.Slide();
                    slide.setSlideEdge(Gravity.START);
                    androidx.transition.TransitionManager.beginDelayedTransition(rv, slide);
                    rv.setAdapter(new FixtureViewHolder(DrawActivity.this, generatePage(currentPage)));

                }

                else{

                }
            }
        });
    }

    private List<Fixture> generatePage(int currentPage) {
        int startItem=(currentPage*ITEMS_PER_PAGE);
        int numOfData=ITEMS_PER_PAGE;
        ArrayList<Fixture> pageData=new ArrayList<>();


        if (currentPage==LAST_PAGE && ITEMS_REMAINING>0)
        {
            for (int i=startItem;i<startItem+ITEMS_REMAINING;i++)
            {
                pageData.add(fixture.get(i));
            }
        }else
        {
            for (int i=startItem;i<startItem+numOfData;i++)
            {
                pageData.add(fixture.get(i));
            }
        }
        return pageData;
    }

    public void onStop () {
        super.onStop();
        overridePendingTransition(
                R.anim.enter, R.anim.exit);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(
                R.anim.enter, R.anim.exit);
    }


}