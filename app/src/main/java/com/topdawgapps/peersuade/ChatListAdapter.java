package com.topdawgapps.peersuade;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.firebase.client.Query;

/**
 * Created by Brad on 6/23/2015.
 */
public abstract class ChatListAdapter extends FirebaseListAdapter<Chat> {

    // The mUsername for this client. We use this to indicate which messages originated from this user
    private String mUsername;

    public ChatListAdapter(Query ref, Activity activity, int layout, String mUsername) {
        super(ref, Chat.class, layout, activity);
        this.mUsername = mUsername;
    }

    /**
     * Bind an instance of the <code>Chat</code> class to our view. This method is called by <code>FirebaseListAdapter</code>
     * when there is a data change, and we are given an instance of a View that corresponds to the layout that we passed
     * to the constructor, as well as a single <code>Chat</code> instance that represents the current data to bind.
     *
     * @param view A view instance corresponding to the layout we passed to the constructor.
     * @param chat An instance representing the current state of a chat message
     */
    @Override
    protected void populateView(View view, Chat chat) {
        String author = chat.getAuthor();
        TextView tv = (TextView) view.findViewById(R.id.message);
        // If the message was sent by this user, color it differently
        if (author != null && author.equals(mUsername)) {
            tv.setBackgroundResource(R.drawable.chat_bubble);
        } else {
            tv.setBackgroundResource(R.drawable.chat_bubble_light);
        }
        tv.setText(chat.getMessage());
    }
}
