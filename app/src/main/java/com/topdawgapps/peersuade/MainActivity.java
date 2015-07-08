package com.topdawgapps.peersuade;

import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.HashMap;


public class MainActivity extends AppCompatActivity implements
        DiscussionFragment.OnFragmentInteractionListener,
        TopicFragment.onTopicChosenListener,
        VoteFragment.OnFragmentInteractionListener {

    private static final String FIREBASE_URL = "https://radiant-torch-7422.firebaseio.com/android";
    private Firebase topicFirebaseRef;
    private Firebase discussionFirebaseRef;
    private Firebase votingFirebaseRef;
    private Firebase usersFirebaseRef;
    private Firebase queueFirebaseRef;
    private Firebase messagesFirebaseRef;
    private boolean isEnqued;
    private String mUsername;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Make sure we have a mUsername
        setupUsername();
        usersFirebaseRef = new Firebase(FIREBASE_URL).child("users");
        ensureUserFirebase();

        topicFirebaseRef = new Firebase(FIREBASE_URL).child("topics");
        discussionFirebaseRef = new Firebase(FIREBASE_URL).child("discussions");
        votingFirebaseRef = new Firebase(FIREBASE_URL).child("voting");
        queueFirebaseRef = new Firebase(FIREBASE_URL).child("queue");
        messagesFirebaseRef = new Firebase(FIREBASE_URL).child("messages");


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
        //make changes in discussion fragment based on the user's chosen topic
        isEnqued = true;
        enqueue(topic, stance);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    //when the user launches the application, ensure that they have their own firebase
    // in the 'users' section
    private void ensureUserFirebase() {
        ValueEventListener usernameListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (!dataSnapshot.hasChild(mUsername)) {
                    Firebase userRef = usersFirebaseRef.child(mUsername);
                    User u = new User(mUsername);
                    userRef.setValue(u);
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        };
        usersFirebaseRef.addValueEventListener(usernameListener);
    }

    private void enqueue(final String topic, final String stance) {

        queueFirebaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (!dataSnapshot.hasChild(topic)) {
                    Log.v("TAG", "nobody in queue");
                    //nobody else is waiting in the queue
                    Firebase q = queueFirebaseRef.child(topic);
                    HashMap<String, String> map = new HashMap<>();
                    map.put(mUsername, stance);
                    q.setValue(map);
                } else if (isEnqued) {
                    Log.v("TAG", "somebody in queue");
                    isEnqued = false;
                    //there is someone waiting in the queue, match with them
                    //TODO: retrieve name of the user that was enqueued first
                    Discussion d = new Discussion(topic, mUsername, otheruser);
                    Firebase newRef = discussionFirebaseRef.push();
                    newRef.setValue(d);

                    //Once the discussion is made, make a reference to it under this user's firebase
                    Firebase userDiscussions = usersFirebaseRef.child(mUsername).child("discussions");
                    // Add the reference to this discussion under their username
                    HashMap<String, String> map = new HashMap<>();
                    map.put(newRef.getKey(), stance);
                    userDiscussions.push().setValue(map);
                }

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }
}
