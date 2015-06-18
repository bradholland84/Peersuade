package com.topdawgapps.peersuade;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by Brad on 6/17/2015.
 */
public class CustomPagerAdapter extends FragmentPagerAdapter {

    private static int[] layoutResId =
            {R.layout.dicussion_tab, R.layout.topics_tab, R.layout.voting_tab};

    public CustomPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new DiscussionFragment();
            case 1:
                return new TopicFragment();
            default:
                return new VoteFragment();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Discussions";
            case 1:
                return "Topics";
            default:
                return "Vote";
        }
    }

    public static View getTabView(int i, Context mContext) {
        return LayoutInflater.from(mContext).inflate(layoutResId[i], null);
    }
}

