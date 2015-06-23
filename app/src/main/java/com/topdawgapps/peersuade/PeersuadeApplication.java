package com.topdawgapps.peersuade;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by Brad on 6/23/2015.
 */
public class PeersuadeApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
    }
}
