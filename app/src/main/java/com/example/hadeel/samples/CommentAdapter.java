package com.example.hadeel.samples;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by hadeel on 9/25/2017.
 */

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {
    ArrayList<CommntTableModel> commentList=new ArrayList<>();
    private Context mContext;
    SQLiteDatabase sqLiteDatabase;
    SqliteHelper sqLiteHelper;
    public CommentAdapter(Context mContext, ArrayList<CommntTableModel> commentList)
    {
        this.commentList=commentList;
        this.mContext=mContext;
    }
    @Override
    public CommentAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_table_row,parent,false);

        return new CommentAdapter.ViewHolder(itemView) ;
    }

    @Override
    public void onBindViewHolder(final CommentAdapter.ViewHolder holder, int position) {
        final CommntTableModel comment=commentList.get(position);
        holder.comment.setText(commentList.get(position).getComment());
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, final int position, boolean isLongClick) {
                if (isLongClick){

                    Toast.makeText(mContext,"This Your Comment ",Toast.LENGTH_LONG).show();

                }
                else {




                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return commentList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener  {
        private ItemClickListener itemClickListener;
        TextView comment;
        public ViewHolder(View itemView) {
            super(itemView);
            comment= (TextView) itemView.findViewById(R.id.commentText);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);

        }
        public void setItemClickListener(ItemClickListener itemClickListener){
            this.itemClickListener=itemClickListener;
        }


        @Override
        public void onClick(View v) {
          itemClickListener.onClick(v,getAdapterPosition(),false);
        }

        @Override
        public boolean onLongClick(View v) {
            itemClickListener.onClick(v,getAdapterPosition(),false);
            return true;
        }
    }
}
