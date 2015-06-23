package com.topdawgapps.peersuade;

import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;


public class MainActivity extends AppCompatActivity implements
        DiscussionFragment.OnFragmentInteractionListener,
        TopicFragment.OnFragmentInteractionListener,
        VoteFragment.OnFragmentInteractionListener {

    // TODO: change this to your own Firebase URL
    private static final String FIREBASE_URL = "https://radiant-torch-7422.firebaseio.com";
    private String mUsername;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Make sure we have a mUsername
        setupUsername();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);

        setSupportActionBar(toolbar);

        viewPager.setAdapter(new CustomPagerAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);

        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            tab.setCustomView(CustomPagerAdapter.getTabView(i, this));
        }
        viewPager.setCurrentItem(1, true);
    }

    private void setupUsername() {
        SharedPreferences prefs = getApplication().getSharedPreferences("ChatPrefs", 0);
        mUsername = prefs.getString("username", null);
        if (mUsername == null) {
            Long l = System.currentTimeMillis();
            // Assign a random user name if we don't have one saved.
            mUsername = "JavaUser" + l;
            prefs.edit().putString("username", mUsername).apply();
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        //TODO: implement custom interaction listeners for all three fragments
    }
}
