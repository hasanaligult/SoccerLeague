package com.hasanaligultekin.soccerleague.viewmodel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hasanaligultekin.soccerleague.R;
import com.hasanaligultekin.soccerleague.model.Fixture;

import java.util.List;

public class FixtureViewHolder extends RecyclerView.Adapter<FixtureViewHolder.ViewHolder> {

    Context mcontext;
    List<Fixture> fixtureList;

    public FixtureViewHolder(Context mcontext, List<Fixture> fixtureList){
        this.mcontext=mcontext;
        this.fixtureList=fixtureList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView awayTeam_name, homeTeam_name, versus;

        public ViewHolder(@NonNull View itemView){
            super(itemView);

            awayTeam_name=itemView.findViewById(R.id.away_team_name);
            homeTeam_name=itemView.findViewById(R.id.home_team_name);
            versus=itemView.findViewById(R.id.versus);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view= LayoutInflater.from(mcontext).inflate(R.layout.item_fixture, viewGroup,false);
        return new FixtureViewHolder.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int position) {
        final Fixture fixture=fixtureList.get(position);

        viewHolder.homeTeam_name.setText(fixture.getHomeTeam());
        viewHolder.awayTeam_name.setText(fixture.getAwayTeam());

    }

    @Override
    public int getItemCount() {
        return fixtureList.size();
    }


}
