package com.janta.esir.megatips.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.janta.esir.megatips.R;
import com.janta.esir.megatips.models.Bet;

import java.util.List;

/**
 * Created by esir on 04/07/2017.
 */

public class BetAdapter extends RecyclerView.Adapter<BetAdapter.MyViewHolder> {
    private Context mContext;
    private List<Bet> bets;

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView teamA, teamB, leauge, date, odds, results, tip, time;
        public ImageView teamAFlag, teamBFlag, leagueFlag;

        public MyViewHolder(View view ){
            super(view);
            tip = (TextView) view.findViewById(R.id.tip);
            teamA = (TextView) view.findViewById(R.id.tvTeamAname);
            teamAFlag = (ImageView) view.findViewById(R.id.teamAFlag);
            teamBFlag = (ImageView) view.findViewById(R.id.teamBFlag);
            leagueFlag = (ImageView) view.findViewById(R.id.leagueFlag);
            teamB = (TextView) view.findViewById(R.id.tvTeamBname);
            leauge = (TextView) view.findViewById(R.id.tvLeaugeTeams);
            date = (TextView) view.findViewById(R.id.dateTxt);
            odds = (TextView) view.findViewById(R.id.textView2);
            results = (TextView) view.findViewById(R.id.result);
            time = (TextView) view.findViewById(R.id.time);
        }
    }

    public BetAdapter(Context context, List<Bet> bets){
        this.mContext = context;
        this.bets = bets;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_bet, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Bet bet = bets.get(position);
        holder.teamA.setText(bet.getTeamA());
        holder.teamB.setText(bet.getTeamB());
        holder.results.setText(bet.getResults());
        holder.odds.setText("Odds: " + bet.getOdds()+" ");
        holder.tip.setText(bet.getTip());
        holder.date.setText(bet.getDate());
        holder.leauge.setText(bet.getLeauge());
        holder.time.setText(bet.getTime());
        Glide.with(mContext).load(bet.getLeaugeFlag())
                .placeholder(R.drawable.international_flag_of_planet_earth)
                .fitCenter()
                .dontAnimate()
                .into(holder.leagueFlag);
        Glide.with(mContext).load(bet.getTeamAFlag())
                .placeholder(R.drawable.international_flag_of_planet_earth)
                .fitCenter()
                .dontAnimate()
                .into(holder.teamAFlag);
        Glide.with(mContext).load(bet.getTeamBFlag())
                .placeholder(R.drawable.international_flag_of_planet_earth)
                .dontAnimate()
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.teamBFlag);
    }

    @Override
    public int getItemCount() {
        return bets.size();
    }
}
