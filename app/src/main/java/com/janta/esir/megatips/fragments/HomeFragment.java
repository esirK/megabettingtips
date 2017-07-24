package com.janta.esir.megatips.fragments;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.janta.esir.megatips.Login;
import com.janta.esir.megatips.R;
import com.janta.esir.megatips.SignUp;
import com.janta.esir.megatips.adapters.BetAdapter;
import com.janta.esir.megatips.helper.SessionManager;
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
    private boolean loggedIn;
    private SessionManager session;
    Context context;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Check If Person Is Logged in
        context = getActivity();
        session = new SessionManager(context);
        loggedIn = session.isLoggedIn();
        View v;
        if(loggedIn) {
            v = inflater.inflate(R.layout.home_fragment, container, false);
            recyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);
            betList = new ArrayList<>();
            betAdapter = new BetAdapter(getActivity(), betList);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(betAdapter);
            prepareBets();
        }else{
            v = inflater.inflate(R.layout.login_or_signup, container, false);
            //
            Button login = (Button) v.findViewById(R.id.button_login);
            Button sign_up = (Button) v.findViewById(R.id.button_signup);

            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getActivity(), Login.class);
                    startActivity(i);
                    getActivity().finish();
                }
            });
            sign_up.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getActivity(), SignUp.class);
                    startActivity(i);
                }
            });
        }
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
        betA.setWin(true);
        betA.setFavourite(false);
        betList.add(betA);

        Bet bet2 = new Bet();
        bet2.setOdds((float) 2.23);
        bet2.setLeauge("England - Premier league");
        bet2.setTeamA("Liverpool");
        bet2.setTeamAFlag("https://s-media-cache-ak0.pinimg.com/originals/da/de/cf/dadecf2eb40502838cf7f789aab5a828.jpg");
        bet2.setTeamB("Arsenal");
        bet2.setTip("Tip: Draw");
        bet2.setResults(null);
        bet2.setTeamBFlag("https://upload.wikimedia.org/wikipedia/en/thumb/5/53/Arsenal_FC.svg/870px-Arsenal_FC.svg.png");
        bet2.setLeaugeFlag("http://il6.picdn.net/shutterstock/videos/13778159/thumb/1.jpg");
        bet2.setDate("Friday 27/07/2017");
        bet2.setTime("21:00");
        bet2.setFavourite(false);
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
        bet3.setResults(null);
        bet3.setDate("Sunday 28/07/2017");
        bet3.setTime("14:00");
        bet3.setFavourite(false);
        betList.add(bet3);


        Bet bet4 = new Bet();
        bet4.setOdds((float) 3.23);
        bet4.setLeauge("Bundesliga - league");
        bet4.setTeamA("Leverkusen");
        bet4.setTeamB("Munich");
        bet4.setTip("Tip: Draw");
        bet3.setLeaugeFlag("http://s.bundesliga.com/2016/img/fallback_picture.jpg");
        bet4.setResults(null);
        bet4.setDate("Monday 30/07/2017");
        bet4.setTime("17:00");
        bet4.setFavourite(false);
        betList.add(bet4);

        betAdapter.notifyDataSetChanged();
    }
}
