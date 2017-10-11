package com.example.hadeel.samples;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by hadeel on 9/11/2017.
 */

public class InOrderAdapter extends RecyclerView.Adapter<InOrderAdapter.ViewHolder> {
    ArrayList<inOrderItem> orderList=new ArrayList<>();
    private Context mContext;
    public  InOrderAdapter(ArrayList<inOrderItem> orderList,Context mContext){
        this.orderList=orderList;
        this.mContext=mContext;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.content_orderin_row,parent,false);

        return new InOrderAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(InOrderAdapter.ViewHolder holder, int position) {
        final  inOrderItem item=orderList.get(position);
        holder.title.setText(orderList.get(position).getTitle());
        holder.meal.setImageResource(orderList.get(position).getImagemeal());

    }


    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView meal;
        TextView title;

        public ViewHolder(View itemView) {
            super(itemView);
            meal=(ImageView)itemView.findViewById(R.id.meal);
            title=(TextView) itemView.findViewById(R.id.nameMeal);
        }
    }
}
