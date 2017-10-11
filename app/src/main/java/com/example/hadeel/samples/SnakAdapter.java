package com.example.hadeel.samples;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by hadeel on 9/11/2017.
 */

public class SnakAdapter extends RecyclerView.Adapter<SnakAdapter.ViewHolder> {
    ArrayList<snakItem> snakList=new ArrayList<>();
    private Context mContext;
    public SnakAdapter(ArrayList<snakItem> snakList,Context mContext){
        this.snakList=snakList;
        this.mContext=mContext;
    }
    @Override
    public SnakAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.snak_row_content,parent,false);
        return new SnakAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(SnakAdapter.ViewHolder holder, int position) {
        final snakItem item=snakList.get(position);
        holder.title.setText(snakList.get(position).getTitle());
        holder.title2.setText(snakList.get(position).getTitle2());
        holder.logo.setImageResource(snakList.get(position).getLogo());

    }

    @Override
    public int getItemCount() {
        return snakList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView logo;
        TextView title,title2;
        public ViewHolder(View itemView) {
            super(itemView);
            logo=(CircleImageView) itemView.findViewById(R.id.profile_image);
            title=(TextView) itemView.findViewById(R.id.title);
            title2=(TextView)itemView.findViewById(R.id.title2);
        }
    }
}
