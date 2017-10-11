package com.example.hadeel.samples;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by hadeel on 9/11/2017.
 */

public class FirstFragment extends Fragment {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);





    }
    public View onCreateView(LayoutInflater inflater, ViewGroup continer,Bundle savedInstanceState ){
        View rootView=inflater.inflate(R.layout.first_fragment,continer,false);
        return rootView;
    }
}
