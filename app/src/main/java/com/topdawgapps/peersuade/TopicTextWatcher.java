package com.topdawgapps.peersuade;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

/**
 * Created by Brad on 7/3/2015.
 */
public class TopicTextWatcher implements TextWatcher {

    private View v;

    public TopicTextWatcher (View v) {
        this.v = v;
    }
    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        v.setVisibility(View.VISIBLE);
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}
