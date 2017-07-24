package com.janta.esir.megatips.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.janta.esir.megatips.R;
import com.janta.esir.megatips.adapters.BetAdapter;
import com.janta.esir.megatips.models.Bet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by esir on 03/07/2017.
 */

public class MyGamesFragment extends Fragment {

    private RecyclerView recyclerView;
    private BetAdapter betAdapter;
    private List<Bet> betList;
    private SwipeRefreshLayout swipeContainer;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.my_games_fragment, container, false);
        //Check If Person Is Logged in
        boolean loggedIn = true;
        if(loggedIn) {
            recyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);
            betList = new ArrayList<>();
            betAdapter = new BetAdapter(getActivity(), betList);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(betAdapter);
            prepareBets();
        }
        // Lookup the swipe container view
        swipeContainer = (SwipeRefreshLayout) v.findViewById(R.id.swipeContainer);

        // Setup refresh listener which triggers new data loading
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Your code to refresh the list here.
                // Make sure you call swipeContainer.setRefreshing(false)
                // once the network request has completed successfully.

            }
        });
        // Configure the refreshing colors
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        return v;
    }
    //Prepare temp bets
    private void prepareBets(){
        Bet betA = new Bet();
        betA.setOdds((float) 3.23);
        betA.setLeauge("England - Premier league");
        betA.setTeamA("Sydney");
        betA.setTeamAFlag("http://secure.cache.images.core.optasports.com/soccer/teams/150x150/4295.png");
        betA.setTeamB("Arsenal");
        betA.setTeamBFlag("https://upload.wikimedia.org/wikipedia/en/thumb/5/53/Arsenal_FC.svg/870px-Arsenal_FC.svg.png");
        betA.setTip("Tip: 1");
        betA.setResults("2:2");
        betA.setLeaugeFlag("http://il6.picdn.net/shutterstock/videos/13778159/thumb/1.jpg");
        betA.setDate("Sunday 13/2017");
        betA.setTime("02:00");
        betA.setFavourite(true);
        betA.setWin(false);
        betList.add(betA);

        Bet bet2 = new Bet();
        bet2.setOdds((float) 2.23);
        bet2.setLeauge("England - Premier league");
        bet2.setTeamA("Chelsea");
        bet2.setTeamAFlag("http://secure.cache.images.core.optasports.com/soccer/teams/150x150/661.png");
        bet2.setTeamB("Burnley");
        bet2.setTip("Tip: Draw");
        bet2.setTeamBFlag("http://secure.cache.images.core.optasports.com/soccer/teams/150x150/698.png");
        bet2.setLeaugeFlag("http://il6.picdn.net/shutterstock/videos/13778159/thumb/1.jpg");
        bet2.setDate("Friday 12/08/2017");
        bet2.setTime("21:00");
        bet2.setFavourite(true);
        bet2.setResults(null);
        betList.add(bet2);

        Bet bet3 = new Bet();
        bet3.setOdds((float) 3.23);
        bet3.setLeauge("Bundesliga - league");
        bet3.setTeamA("Bayern München");
        bet3.setTeamAFlag("http://secure.cache.images.core.optasports.com/soccer/teams/150x150/961.png");
        bet3.setTeamB("Bayer Leverkusen");
        bet3.setTeamBFlag("http://secure.cache.images.core.optasports.com/soccer/teams/150x150/963.png");
        bet3.setLeaugeFlag("http://s.bundesliga.com/2016/img/fallback_picture.jpg");
        bet3.setTip("Tip: 2");
        bet3.setResults("2:3");
        bet3.setDate("Sunday 18/08/2017");
        bet3.setTime("14:00");
        bet3.setWin(true);
        bet3.setFavourite(true);

        betList.add(bet3);

        Bet bet4 = new Bet();
        bet4.setOdds((float) 3.23);
        bet4.setLeauge("LIGUE 1");
        bet4.setTeamA("PSG");
        bet4.setTeamAFlag("http://secure.cache.images.core.optasports.com/soccer/teams/150x150/886.png");
        bet4.setTeamB("Amiens SC");
        bet4.setTeamBFlag("http://secure.cache.images.core.optasports.com/soccer/teams/150x150/908.png");
        bet4.setTip("Tip: Draw");
        bet4.setLeaugeFlag("https://media.contentapi.ea.com/content/www-easports/en_US/fifa/ultimate-team/news/2017/fut-17-tots-ligue-1/_jcr_content/headerImages/image.img.jpg");
        bet4.setResults("1:1");
        bet4.setDate("Monday 30/07/2017");
        bet4.setTime("17:00");
        bet4.setFavourite(true);
        bet4.setWin(true);
        betList.add(bet4);

        betAdapter.notifyDataSetChanged();
    }
}
