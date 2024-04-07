package com.example.project;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class HomeFragment extends Fragment {
    public HomeFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        CardView cardView1 = rootView.findViewById(R.id.fruits);
        CardView cardView2 = rootView.findViewById(R.id.vegetables);
        CardView cardView3 = rootView.findViewById(R.id.herbsAndSeasonings);
        CardView cardView4 = rootView.findViewById(R.id.CutsAndExotics);

        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FruitsActivity.class);
                startActivity(intent);

            }
        });

        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), VegetablesActivity.class);
                startActivity(intent);

            }
        });

        cardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),HerbsAndSeasoningsActivity.class);
                startActivity(intent);

            }
        });

        cardView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),CutsAndExoticsActivity.class);
                startActivity(intent);


            }
        });

        return rootView;

    }


}