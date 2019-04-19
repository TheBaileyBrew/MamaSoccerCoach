package com.thebaileybrew.mamasoccercoach;

import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.transition.Slide;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.thebaileybrew.mamasoccercoach.fragments.FragmentPlayerUpdate;

import static com.thebaileybrew.mamasoccercoach.Constants.*;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Fragment loadedFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        //Determine the screen to display based on menu selection
        switch (id) {
            case R.id.nav_player_details:
                loadFragments(NAV_MENU_PLAYERS);
                break;
            case R.id.nav_quarters:
                loadFragments(NAV_MENU_QUARTERS);
                break;
            case R.id.nav_game_notes:
                loadFragments(NAV_MENU_NOTES);
                break;
            case R.id.nav_tools:
                loadFragments(NAV_MENU_SETTINGS);
                break;
            case R.id.nav_schedule_game:
                loadFragments(NAV_MENU_SCHEDULE);
                break;
            case R.id.nav_schedule_snack:
                loadFragments(NAV_MENU_SNACKS);
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void loadFragments(String pageLoading) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        switch (pageLoading) {
            case NAV_MENU_PLAYERS:
                if (loadedFragment == null) {
                    loadedFragment = new FragmentPlayerUpdate();
                    loadedFragment.setExitTransition(new Slide(GravityCompat.START));
                    fragmentTransaction.add(R.id.fragment_holder, loadedFragment).commit();
                } else {
                    if (!(fragmentManager.findFragmentById(R.id.fragment_holder) instanceof FragmentPlayerUpdate)) {
                        loadedFragment = new FragmentPlayerUpdate();
                        loadedFragment.setEnterTransition(new Slide(GravityCompat.END));
                        fragmentTransaction.replace(R.id.fragment_holder, loadedFragment);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                    }
                }
        }
    }
}
