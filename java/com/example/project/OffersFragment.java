package com.example.project;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class OffersFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (inflater != null && container != null) {
            return inflater.inflate(R.layout.fragment_offers, container, false);
        } else {
            // Handle the case where inflater or container is null
            return null;
        }
    }
}