package com.nain.navdemo;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class Frag1 extends Fragment {



    public Frag1() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View local_view = inflater.inflate(R.layout.fragment_frag1, container, false);

        Button button = local_view.findViewById(R.id.btn_submit);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText userText = getView().findViewById(R.id.userText);
                Frag1Directions.ActionFrag1ToFrag2 action = Frag1Directions.actionFrag1ToFrag2();


                action.setMesg(userText.getText().toString());
                Navigation.findNavController(view).navigate(action);
               // Navigation.findNavController(view).navigate(R.id.action_frag1_to_frag2);
            }
        });

//return inflater.inflate(R.layout.fragment_frag1, container, false);
        return local_view;
    }
}