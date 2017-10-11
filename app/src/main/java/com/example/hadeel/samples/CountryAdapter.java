package com.example.hadeel.samples;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hadeel on 9/6/2017.
 */

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.ViewHolder> {
    private static   List<country> countryList;

    country co=new country();
    private Context mContext;
    public CountryAdapter (List<country> countryList, Context mContext){
        this.countryList=countryList;
        this.mContext=mContext;
        String name;

    }
    @Override
    public CountryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.content_row,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CountryAdapter.ViewHolder holder, int position) {
      final country count=countryList.get(position);
        holder.country.setText(count.getCountry());
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if (isLongClick){

                    Toast.makeText(mContext,"welcome",Toast.LENGTH_LONG).show();

                }
                else {
                    //Toast.makeText(mContext,"hello",Toast.LENGTH_LONG).show();
                    Intent i = new Intent(mContext,CountryDetails.class);
                    i.putExtra("name",count.getCountry());
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mContext.startActivity(i);
                    Toast.makeText(mContext,count.getCountry(),Toast.LENGTH_LONG).show();


                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return countryList.size();
    }
//    public  void Filter(ArrayList<country> newList,country co){
//        countryList=new ArrayList<>();
//        countryList.addAll(newList);
//        notifyDataSetChanged();
//    }

    public static void Filter(ArrayList<country> newList) {
        countryList=new ArrayList<>();
        countryList.addAll(newList);

    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {
        private ItemClickListener itemClickListener;
        TextView country;
        public ViewHolder(View itemView) {
            super(itemView);
            country=(TextView) itemView.findViewById(R.id.country);
            itemView.setOnClickListener(this);
        }

        public void setItemClickListener(ItemClickListener itemClickListener){
            this.itemClickListener=itemClickListener;
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onClick(v,getAdapterPosition(),false);

        }

        public boolean onLongClick(View v) {
            itemClickListener.onClick(v,getAdapterPosition(),true);
            return true;
        }
    }
}
