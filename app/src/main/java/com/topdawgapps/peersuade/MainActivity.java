package com.topdawgapps.peersuade;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

    @Override
    public void onFragmentInteraction(Uri uri) {
        //TODO: implement custom interaction listeners for all three fragments
    }
}
