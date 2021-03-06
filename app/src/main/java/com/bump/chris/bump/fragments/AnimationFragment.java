package com.bump.chris.bump.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bump.chris.bump.R;
import com.bump.chris.bump.network.AuthenticatorTask;

/**
 * Created by chris on 18/07/16.
 */
public class AnimationFragment extends Fragment {
    private static final String LOG_TAG = "AnimationFragment";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_animation, container, false);
        return v;
    }

}
