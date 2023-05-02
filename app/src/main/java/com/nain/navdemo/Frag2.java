package com.nain.navdemo;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class Frag2 extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_frag2, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        TextView argText = getView().findViewById(R.id.argText);
        Frag2Args args= Frag2Args.fromBundle(getArguments());

//see comment at step 15 above
        String mesg = args.getMesg();
        argText.setText(mesg);
    }
}