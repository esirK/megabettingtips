package com.janta.esir.megatips;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.janta.esir.megatips.fragments.HomeFragment;
import com.janta.esir.megatips.fragments.MyGamesFragment;
import com.janta.esir.megatips.fragments.TopTipsFragment;

import static com.janta.esir.megatips.R.drawable.ic_home_black_24dp;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //Fragments
        FragmentManager fragmentManager = getFragmentManager();
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        final HomeFragment homeFragment = new HomeFragment();
        final MyGamesFragment myGamesFragment = new MyGamesFragment();
        final TopTipsFragment topTipsFragment = new TopTipsFragment();

        fragmentTransaction.add(R.id.content_main, homeFragment, "home");
        fragmentTransaction.commit();

        AHBottomNavigation bottomNavigation = (AHBottomNavigation) findViewById(R.id.bottom_navigation);
        // Create items
        AHBottomNavigationItem item1 = new AHBottomNavigationItem("Home", ic_home_black_24dp, R.color.colorAccent);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem("Top Tips", R.drawable.ic_whatshot_black_24dp, R.color.colorAccent);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem("My Games", R.drawable.ic_grade_black_24dp, R.color.colorAccent);
        // Add items
        bottomNavigation.addItem(item1);
        bottomNavigation.addItem(item2);
        bottomNavigation.addItem(item3);
        // Set background color
        bottomNavigation.setDefaultBackgroundColor(getResources().getColor(R.color.white));
        //bottomNavigation.setDefaultBackgroundColor(Color.parseColor("#FEFEFE"));
        // Manage titles
        bottomNavigation.setTitleState(AHBottomNavigation.TitleState.SHOW_WHEN_ACTIVE);

        bottomNavigation.setBehaviorTranslationEnabled(true);

    // Use colored navigation with circle reveal effect
        bottomNavigation.setColored(false);

    // Set current item programmatically
        bottomNavigation.setCurrentItem(0);
        // Customize notification (title, background, typeface)
        bottomNavigation.setNotificationBackgroundColor(Color.parseColor("#F63D2B"));

        bottomNavigation.setAccentColor(Color.parseColor("#c66121"));
    // Add or remove notification for each item
        bottomNavigation.setNotification("1", 2);

        // Set listeners
        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                // Do something cool here...
                if(position == 0) {
                    FragmentTransaction fragmentTransaction =getFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.content_main, homeFragment, "home");
                    fragmentTransaction.commit();
                }
                else if(position == 1){
                    FragmentTransaction fragmentTransaction =getFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.content_main, topTipsFragment, "tips");
                    fragmentTransaction.commit();
                }
                else if(position == 2) {
                    FragmentTransaction fragmentTransaction =getFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.content_main, myGamesFragment, "myGames");
                    fragmentTransaction.commit();
                }
            return true;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
