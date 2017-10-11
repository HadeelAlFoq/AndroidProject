package com.example.hadeel.samples;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by hadeel on 9/19/2017.
 */

public class personAdapter extends RecyclerView.Adapter<personAdapter.ViewHolder> {
    ArrayList<personItem> personList=new ArrayList<>();
    private Context mContext;
    public personAdapter(ArrayList<personItem> personList,Context mContext){
        this.personList=personList;
        this.mContext=mContext;
    }
    @Override
    public personAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.person_content_row,parent,false);
        return new personAdapter.ViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(personAdapter.ViewHolder holder, int position) {
        final personItem item=personList.get(position);
        holder.name.setText(personList.get(position).getName());
        holder.person.setImageResource(personList.get(position).getPicPerson());
        holder.person.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent per=new Intent(mContext,Option_Activity.class);
                per.putExtra("name",item.getName());
                per.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(per);
                Toast.makeText(mContext,item.getName(),Toast.LENGTH_LONG).show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return personList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder   {
       // private ItemClickListener itemClickListener;
        TextView country;

        ImageView person;
        TextView name;
        public ViewHolder(View itemView) {
            super(itemView);
            person=(ImageView)itemView.findViewById(R.id.person);
            name=(TextView)itemView.findViewById(R.id.name);
           // itemView.setOnClickListener(this);

        }

//        public void setItemClickListener(ItemClickListener itemClickListener){
//            this.itemClickListener=itemClickListener;
//        }
//
//        @Override
//        public void onClick(View v) {
//            itemClickListener.onClick(v,getAdapterPosition(),false);
//
//        }

    }
}
