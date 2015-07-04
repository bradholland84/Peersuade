package com.topdawgapps.peersuade;

import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.firebase.client.Firebase;


public class MainActivity extends AppCompatActivity implements
        DiscussionFragment.OnFragmentInteractionListener,
        TopicFragment.onTopicChosenListener,
        VoteFragment.OnFragmentInteractionListener {

    private static final String FIREBASE_URL = "https://radiant-torch-7422.firebaseio.com/android";
    private Firebase topicFirebaseRef;
    private Firebase discussionFirebaseRef;
    private Firebase votingFirebaseRef;
    private String mUsername;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Make sure we have a mUsername
        setupUsername();

        topicFirebaseRef = new Firebase(FIREBASE_URL).child("topics");
        discussionFirebaseRef = new Firebase(FIREBASE_URL).child("discussions");
        votingFirebaseRef = new Firebase(FIREBASE_URL).child("voting");

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
    public void onTopicChosen(String topic, String stance) {
        //TODO: make changes in discussion fragment based on the user's chosen topic
        Toast.makeText(this, mUsername + stance, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
