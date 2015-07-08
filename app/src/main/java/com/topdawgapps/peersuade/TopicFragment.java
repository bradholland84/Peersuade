package com.topdawgapps.peersuade;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.firebase.client.Firebase;
import com.squareup.picasso.Picasso;

public class TopicFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String FIREBASE_URL =
            "https://radiant-torch-7422.firebaseio.com/android/topics";

    private String mUsername;
    private Firebase topicFirebase;
    private ImageView iv;
    private ImageView iv2;
    private ImageView iv3;
    private EditText et;
    private EditText et2;
    private EditText et3;
    private Button btn;
    private Button btn2;
    private Button btn3;

    private onTopicChosenListener mListener;


    // TODO: Rename and change types and number of parameters
    public static TopicFragment newInstance(String param1) {
        TopicFragment fragment = new TopicFragment();
        Bundle args = new Bundle();
        args.putString(FIREBASE_URL, param1);
        fragment.setArguments(args);
        return fragment;
    }

    public TopicFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            topicFirebase = new Firebase(getArguments().getString(FIREBASE_URL));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_topic, container, false);
        iv = (ImageView) v.findViewById(R.id.topic_photo);
        iv2 = (ImageView) v.findViewById(R.id.topic_photo2);
        iv3 = (ImageView) v.findViewById(R.id.topic_photo3);
        et = (EditText) v.findViewById(R.id.et_stance);
        et2 = (EditText) v.findViewById(R.id.et_stance2);
        et3 = (EditText) v.findViewById(R.id.et_stance3);
        btn = (Button) v.findViewById(R.id.send_button);
        btn2 = (Button) v.findViewById(R.id.send_button2);
        btn3 = (Button) v.findViewById(R.id.send_button3);

        return v;
    }



    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (onTopicChosenListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement onTopicChosenListener");
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //methods requiring Context can be here
        getUsername();

        //These are placeholder URL's - final version will pull from database
        Picasso.with(getActivity()).load("http://i.imgur.com/DvpvklR.png").fit().centerCrop().into(iv);
        Picasso.with(getActivity()).load("http://www.themost10.com/wp-content/uploads/2012/03/Girl-Before-A-Mirror-By-Pablo-Picasso.jpg").fit().centerCrop().into(iv2);
        Picasso.with(getActivity()).load("http://www.artsetter.com/uploads/artwork/9a4f9c23dbd75d8f836d22e92a3b5fc52ba68167_original.jpg").fit().centerCrop().into(iv3);

        et.addTextChangedListener(new TopicTextWatcher(btn));
        et2.addTextChangedListener(new TopicTextWatcher(btn2));
        et3.addTextChangedListener(new TopicTextWatcher(btn3));

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onButtonPressed("dummy 1", et.getText().toString());
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onButtonPressed("dummy 2", et2.getText().toString());
            }
        });
    }

    public void onButtonPressed(String topic, String stance) {
        if (mListener != null) {
            mListener.onTopicChosen(topic, stance);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    // Places this user as a guest of this topic, waiting to be paired for discussion
    public void makeGuest(Firebase topic, String stance) {
        //Firebase guestRef = topic.child("guests").child(mUsername);
        //Guest me = new Guest(mUsername, stance);
        //guestRef.setValue(me);
    }

    public interface onTopicChosenListener {
        void onTopicChosen(String topic, String stance);
    }

    public void getUsername() {
        SharedPreferences prefs = getActivity().getApplication()
                .getSharedPreferences("ChatPrefs", 0);
        mUsername = prefs.getString("username", null);
    }

}
