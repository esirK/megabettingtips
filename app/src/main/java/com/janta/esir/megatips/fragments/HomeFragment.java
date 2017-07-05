package com.janta.esir.megatips.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
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

public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private BetAdapter betAdapter;
    private List<Bet> betList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Check If Person Is Logged in
        View v = inflater.inflate(R.layout.home_fragment, null);
        recyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);
        betList = new ArrayList<>();
        betAdapter = new BetAdapter(getActivity(), betList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(betAdapter);
        prepareBets();

        return v;
    }
    //Prepare temp bets
    private void prepareBets(){
        Bet betA = new Bet();
        betA.setOdds((float) 3.23);
        betA.setLeauge("England - Premier league");
        betA.setTeamA("Manchester United");
        betA.setTeamAFlag("https://seeklogo.com/images/M/manchester-united-logo-F14DA1FCCD-seeklogo.com.png");
        betA.setTeamB("Manchester City");
        betA.setTeamBFlag("http://mcivta.com/wp0001/wp-content/uploads/2003/03/mcfc2-badge.jpeg");
        betA.setTip("Tip: 1");
        betA.setResults("2:1");
        betA.setLeaugeFlag("http://il6.picdn.net/shutterstock/videos/13778159/thumb/1.jpg");
        betA.setDate("Saturday 27/07/2017");
        betA.setTime("22:00");
        betList.add(betA);

        Bet bet2 = new Bet();
        bet2.setOdds((float) 2.23);
        bet2.setLeauge("England - Premier league");
        bet2.setTeamA("Liverpool");
        bet2.setTeamAFlag("https://s-media-cache-ak0.pinimg.com/originals/da/de/cf/dadecf2eb40502838cf7f789aab5a828.jpg");
        bet2.setTeamB("Arsenal");
        bet2.setTip("Tip: Draw");
        bet2.setResults("1:1");
        bet2.setTeamBFlag("https://upload.wikimedia.org/wikipedia/en/thumb/5/53/Arsenal_FC.svg/870px-Arsenal_FC.svg.png");
        bet2.setLeaugeFlag("http://il6.picdn.net/shutterstock/videos/13778159/thumb/1.jpg");
        bet2.setDate("Friday 27/07/2017");
        bet2.setTime("21:00");
        betList.add(bet2);

        Bet bet3 = new Bet();
        bet3.setOdds((float) 3.23);
        bet3.setLeauge("Bundesliga - league");
        bet3.setTeamA("Bayern Leverkusen");
        bet3.setTeamAFlag("http://www.prostamerika.com/wp-content/uploads/2016/08/Bayer-04-Leverkusen.png");
        bet3.setTeamB("Bayern munich");
        bet3.setTeamBFlag("https://weneedfun.com/wp-content/uploads/2016/11/Bayern-Munich-Logo-2.png");
        bet3.setLeaugeFlag("http://s.bundesliga.com/2016/img/fallback_picture.jpg");
        bet3.setTip("Tip: 2");
        bet3.setResults("0:1");
        bet3.setDate("Sunday 28/07/2017");
        bet3.setTime("14:00");
        betList.add(bet3);


        Bet bet4 = new Bet();
        bet4.setOdds((float) 3.23);
        bet4.setLeauge("Bundesliga - league");
        bet4.setTeamA("Leverkusen");
        bet4.setTeamB("Munich");
        bet4.setTip("Tip: Draw");
        bet3.setLeaugeFlag("http://s.bundesliga.com/2016/img/fallback_picture.jpg");
        bet4.setResults("1:1");
        bet4.setDate("Monday 30/07/2017");
        bet4.setTime("17:00");
        betList.add(bet4);

        betAdapter.notifyDataSetChanged();
    }
}
