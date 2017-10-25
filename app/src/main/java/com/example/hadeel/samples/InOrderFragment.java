package com.example.hadeel.samples;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by hadeel on 9/11/2017.
 */

public class InOrderFragment extends Fragment {
    RecyclerView recyclerView;
    ArrayList<InOrderItemModel> orderList=new ArrayList<>();
    InOrderAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preperData();

    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.in_order_fragment, container, false);

        recyclerView=(RecyclerView)rootView.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager=new GridLayoutManager(getContext(),2);
        recyclerView.setLayoutManager(layoutManager);
        adapter=new InOrderAdapter(orderList,getContext());
        recyclerView.setAdapter(adapter);

        return rootView;
    }
    public void preperData(){
        InOrderItemModel item= new InOrderItemModel();
        item.setImagemeal(R.drawable.album1);
        item.setTitle("BURGER ");

        orderList.add(item);

        InOrderItemModel item1= new InOrderItemModel();
        item1.setImagemeal(R.drawable.album2);
        item1.setTitle("pizza");

        orderList.add(item1);

        InOrderItemModel item2= new InOrderItemModel();
        item2.setImagemeal(R.drawable.album3);
        item2.setTitle("zinger");

        orderList.add(item2);

        InOrderItemModel item3= new InOrderItemModel();
        item3.setImagemeal(R.drawable.album4);
        item3.setTitle("sochi");

        orderList.add(item3);

        InOrderItemModel item4= new InOrderItemModel();
        item4.setImagemeal(R.drawable.album5);
        item4.setTitle("kabab");

        orderList.add(item4);

        InOrderItemModel item5= new InOrderItemModel();
        item5.setImagemeal(R.drawable.album6);
        item5.setTitle("maghareta");

        orderList.add(item5);

        InOrderItemModel item6= new InOrderItemModel();
        item6.setImagemeal(R.drawable.album7);
        item6.setTitle("dwale");

        orderList.add(item6);

        InOrderItemModel item7= new InOrderItemModel();
        item7.setImagemeal(R.drawable.album8);
        item7.setTitle("checken");

        orderList.add(item7);

        InOrderItemModel item8= new InOrderItemModel();
        item8.setImagemeal(R.drawable.album9);
        item8.setTitle("dessert");

        orderList.add(item8);

        InOrderItemModel item9= new InOrderItemModel();
        item9.setImagemeal(R.drawable.album10);
        item9.setTitle("passta");

        orderList.add(item9);



    }

}
