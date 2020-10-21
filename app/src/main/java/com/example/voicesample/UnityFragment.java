package com.example.voicesample;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.unity3d.player.UnityPlayer;

public class UnityFragment extends Fragment {

    protected UnityPlayer mUnityPlayer; // don't change the name of this variable; referenced from native code
    //Declare a FrameLayout object
    FrameLayout fl_forUnity;
    //Test Button
    public UnityFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mUnityPlayer = new UnityPlayer(getActivity());
        View view = inflater.inflate(R.layout.fragment_unity, container, false);

        //Inflate the frame layout from XML
        this.fl_forUnity = (FrameLayout) view.findViewById(R.id.frameLayoutForUnity);

        //Add the mUnityPlayer view to the FrameLayout, and set it to fill all the area available
        this.fl_forUnity.addView(mUnityPlayer.getView(),
                FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);

        //Requesting the Focus
        mUnityPlayer.requestFocus();

        //The main fix of resolving BLACK SCREEN PLAYER ISSUE
        mUnityPlayer.windowFocusChanged(true);//First fix Line
        // Yes, it's "static" way and should to be more dynamic, anyway, it works well
        return view;
    }

    // Quit Unity
    @Override
    public void onDestroy() {
        mUnityPlayer.quit();
        super.onDestroy();
    }

    // Pause Unity
    @Override
    public void onPause() {
        super.onPause();
        mUnityPlayer.pause();
    }

    // Resume Unity
    @Override
    public void onResume() {
        super.onResume();
        mUnityPlayer.resume();
    }

// This ensures the layout will be correct.
@Override
   public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
       mUnityPlayer.configurationChanged(newConfig);
   }
}