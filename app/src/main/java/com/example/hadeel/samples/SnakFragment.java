package com.example.hadeel.samples;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by hadeel on 9/11/2017.
 */

public class SnakFragment extends Fragment {
    RecyclerView recyclerView;
    ArrayList<snakItem> snakList=new ArrayList<>();
    SnakAdapter adapter;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preperData();

    }
    public View onCreateView(LayoutInflater inflater, ViewGroup continer, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.snaks_fragment, continer, false);
        recyclerView=(RecyclerView)rootView.findViewById(R.id.recyclerViewSnak);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter=new SnakAdapter(snakList,getContext());
        recyclerView.setAdapter(adapter);

        return rootView;

    }
    public void preperData(){
        snakItem item= new snakItem();
        item.setLogo(R.drawable.alya);
        item.setTitle("BURGER KING");
        item.setTitle2("Buy");
        snakList.add(item);

        snakItem item1= new snakItem();
        item1.setLogo(R.drawable.burger);
        item1.setTitle("MAC");
        item1.setTitle2("Buy");
        snakList.add(item1);

        snakItem item2= new snakItem();
        item2.setLogo(R.drawable.knafa);
        item2.setTitle("KFC");
        item2.setTitle2("Buy");
        snakList.add(item2);


    }







}
