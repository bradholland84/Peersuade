package com.topdawgapps.peersuade;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TopicFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TopicFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TopicFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ImageView iv;
    private ImageView iv2;
    private ImageView iv3;
    private EditText et;
    private EditText et2;
    private EditText et3;
    private Button btn;
    private Button btn2;
    private Button btn3;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TopicFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TopicFragment newInstance(String param1, String param2) {
        TopicFragment fragment = new TopicFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
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
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
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

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //methods requiring Context can be here

        //These are placeholder URL's - final version will pull from database
        Picasso.with(getActivity()).load("http://i.imgur.com/DvpvklR.png").fit().centerCrop().into(iv);
        Picasso.with(getActivity()).load("http://www.themost10.com/wp-content/uploads/2012/03/Girl-Before-A-Mirror-By-Pablo-Picasso.jpg").fit().centerCrop().into(iv2);
        Picasso.with(getActivity()).load("http://www.artsetter.com/uploads/artwork/9a4f9c23dbd75d8f836d22e92a3b5fc52ba68167_original.jpg").fit().centerCrop().into(iv3);

        et.addTextChangedListener(new TopicTextWatcher(btn));
        et2.addTextChangedListener(new TopicTextWatcher(btn2));
        et3.addTextChangedListener(new TopicTextWatcher(btn3));
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
