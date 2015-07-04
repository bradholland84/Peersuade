package com.topdawgapps.peersuade;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.firebase.client.Query;

import java.util.Date;

/**
 * Created by Brad on 7/4/2015.
 */
public class DiscussionListAdapter extends FirebaseListAdapter<Discussion> {

    // The mUsername for this client.
    private String mUsername;

    public DiscussionListAdapter(Query ref, Activity activity, int layout, String mUsername) {
        super(ref, Discussion.class, layout, activity);
        this.mUsername = mUsername;
    }

    /**
     * Bind an instance of the <code>Discussion</code> class to our view. This method is called by <code>FirebaseListAdapter</code>
     * when there is a data change, and we are given an instance of a View that corresponds to the layout that we passed
     * to the constructor, as well as a single <code>Discussion</code> instance that represents the current data to bind.
     *
     * @param view A view instance corresponding to the layout we passed to the constructor.
     * @param d An instance representing the current state of a discussion
     */
    @Override
    protected void populateView(View view, Discussion d) {
        String title = d.getTitle();
        TextView tv = (TextView) view.findViewById(R.id.tv_discussion_title);
        tv.setText(title);

        Date date = d.getCreatedOn();
        TextView tv_date = (TextView) view.findViewById(R.id.tv_discussion_date);
        tv_date.setText(date.toString());
    }
}
