package com.example.hadeel.samples;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.media.CamcorderProfile.get;

/**
 * Created by hadeel on 9/10/2017.
 */

public class BottomAdapter extends RecyclerView.Adapter<BottomAdapter.ViewHolder> {
    public  static int sum0,sum1,sum2;
    SQLiteDatabase sqLiteDatabase;

    SqliteHelper db;

    Cursor cursor;
    String commentCounter;

    CommentAdapter commentAdapter;

    int query1;

    Activity activity;
    ArrayList<BottomItem> bottomList=new ArrayList<>();
    private Context mContext;
    private OnCommentClick listener;
    public  BottomAdapter(ArrayList<BottomItem> bottomList,Context mContext,OnCommentClick listener){
        this.bottomList=bottomList;
        this.listener=listener;
        this.mContext=mContext;

    }
    @Override
    public BottomAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.content_row_bottom,parent,false);

        return new ViewHolder(itemView) ;
    }

    @Override
    public void onBindViewHolder(final BottomAdapter.ViewHolder holder, final int position) {
        final BottomItem item=bottomList.get(position);
        holder.title.setText(bottomList.get(position).getTitle());
        holder.profile.setImageResource(bottomList.get(position).getImage());
        holder.commentCount.setText(bottomList.get(position).getCounterComment());

        holder.image.setImageResource(bottomList.get(position).getImageBackGround());
        holder.title.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent pr=new Intent(mContext,OptionActivity.class);
                pr.putExtra("name",item.getTitle());
                pr.putExtra("namepage","profile");

                pr.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(pr);
                Toast.makeText(mContext,item.getTitle(),Toast.LENGTH_LONG).show();

            }
        });
        holder.profile.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                Intent pr = new Intent(mContext, OptionActivity.class);
                pr.putExtra("name", item.getTitle());
                pr.putExtra("namepage","profile");
                pr.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(pr);
                Toast.makeText(mContext, item.getTitle(), Toast.LENGTH_LONG).show();
            }

            });
        holder.like.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
              switch (position){
                  case 0:
                sum0=sum0+1;
                String count=Integer.toString(sum0);
                holder.likeCounter.setText(count);
                break;
                  case 1:
                      sum1=sum1+1;
                      String count1=Integer.toString(sum1);
                      holder.likeCounter.setText(count1);
                      break;
                  case 2:
                      sum2=sum2+1;
                      String count2=Integer.toString(sum2);
                      holder.likeCounter.setText(count2);
                      break;

              }
            }
        });
        holder.comment.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
               listener.onClommentClickListener(bottomList.get(holder.getAdapterPosition()),holder.getAdapterPosition());
            }
        });



    }



    @Override
    public int getItemCount() {
        return bottomList.size() ;
    }


    public class ViewHolder extends RecyclerView.ViewHolder  {

        ImageView image;
        CircleImageView profile;
        TextView  title,like,likeCounter,comment,commentCount;

        public ViewHolder(View itemView) {
            super(itemView);
            profile=(CircleImageView)itemView.findViewById(R.id.profile_image);
            image=(ImageView)itemView.findViewById(R.id.image);
            title=(TextView)itemView.findViewById(R.id.title);
            like=(TextView)itemView.findViewById(R.id.likeic);
            likeCounter =(TextView)itemView.findViewById(R.id.like);
            comment =(TextView)itemView.findViewById(R.id.commentic);
            commentCount= (TextView)itemView.findViewById(R.id.comment);

        }




    }

    interface OnCommentClick{
        void onClommentClickListener(BottomItem item,int position);
    }

}
