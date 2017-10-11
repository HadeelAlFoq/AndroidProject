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
import java.util.List;

/**
 * Created by hadeel on 9/6/2017.
 */

public class NavigationAdapter extends RecyclerView.Adapter<NavigationAdapter.ViewHolder> {
    ArrayList<DrawerItem> navList=new ArrayList<>();
    private Context mContext;
    public NavigationAdapter (ArrayList<DrawerItem> navList,Context mContext){
        this.mContext=mContext;
        this.navList=navList;

    }
    @Override
    public NavigationAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_content_nav,parent,false);
        return new NavigationAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(NavigationAdapter.ViewHolder holder, int position) {
        final DrawerItem item=navList.get(position);
        holder.title.setText(navList.get(position).getTitle());
        holder.icon.setImageResource(navList.get(position).getIcon());
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if (isLongClick){

                    Toast.makeText(mContext,"welcome",Toast.LENGTH_LONG).show();

                }
                else {
                    switch (position) {
                        case 0:
                        Intent i = new Intent(mContext, DisplaySQLiteDataActivity.class);
                        i.putExtra("name", item.getTitle());
                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mContext.startActivity(i);
                        Toast.makeText(mContext, item.getTitle(), Toast.LENGTH_LONG).show();
                            break;
                        case 1:
                            Intent b=new Intent(mContext,Bottom_tab.class);
                            b.putExtra("name", item.getTitle());
                            b.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            mContext.startActivity(b);
                            Toast.makeText(mContext, item.getTitle(), Toast.LENGTH_LONG).show();
                            break;
                        case 2:
                            Intent t=new Intent(mContext,TabActivity.class);
                            t.putExtra("name", item.getTitle());
                            t.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            mContext.startActivity(t);
                            Toast.makeText(mContext, item.getTitle(), Toast.LENGTH_LONG).show();
                            break;
                        case 3:
                            Intent s=new Intent(mContext,LanguagyActivity.class);
                            s.putExtra("name", item.getTitle());
                            s.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            mContext.startActivity(s);
                            Toast.makeText(mContext, item.getTitle(), Toast.LENGTH_LONG).show();
                            break;
                        case 4:
                            Intent C=new Intent(mContext,Date_bicker.class);
                            C.putExtra("name", item.getTitle());
                            C.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            mContext.startActivity(C);
                            Toast.makeText(mContext, item.getTitle(), Toast.LENGTH_LONG).show();
                            break;

                    }


                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return navList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ItemClickListener itemClickListener;
        ImageView icon;
        TextView title;

        public ViewHolder(View itemView) {

            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            icon = (ImageView) itemView.findViewById(R.id.icon);
            itemView.setOnClickListener(this);
        }
        public void setItemClickListener(ItemClickListener itemClickListener){
            this.itemClickListener=itemClickListener;}

        @Override
        public void onClick(View v) {
            itemClickListener.onClick(v,getAdapterPosition(),false);

        }


    }
    }

