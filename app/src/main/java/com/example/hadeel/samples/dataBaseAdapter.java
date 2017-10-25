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
 * Created by hadeel on 9/7/2017.
 */

public class DataBaseAdapter extends RecyclerView.Adapter<DataBaseAdapter.ViewHolder> {
    Context context;
    SQLiteDatabase sqLiteDatabase;
    SqliteHelper sqLiteHelper;

    ArrayList<String> Name;
    ArrayList<String> PhoneNumber;
    ArrayList<String> Email;
    ArrayList<String> Age;
    private DataBaseAdapter thisAdapter = this;
    public DataBaseAdapter(Context context,
                           ArrayList<String> Name,
                           ArrayList<String> PhoneNumber,
                           ArrayList<String> Email, ArrayList<String> Age){
        this.context=context;
        this.Name=Name;
        this.PhoneNumber=PhoneNumber;
        this.Email=Email;
        this.Age=Age;

    }

    @Override
    public DataBaseAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.content_row_database,parent,false);
        return new DataBaseAdapter.ViewHolder(itemView);
    }



    @Override
    public void onBindViewHolder(final DataBaseAdapter.ViewHolder holder, final int position) {
        holder.name.setText(Name.get(position));
        holder.phone.setText(PhoneNumber.get(position));
        holder.email.setText(Email.get(position));
        holder.age.setText(Age.get(position));

        holder.delete.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){


       String value=Email.get(position).toString();
                sqLiteHelper=new SqliteHelper(context);
               SQLiteDatabase db = sqLiteHelper.getWritableDatabase();
                db.execSQL("DELETE FROM " + SqliteHelper.TABLE_NAME+ " WHERE "+SqliteHelper.Table_Column_3_Email+"='"+value+"'");
                Name.remove(position);
                Email.remove(position);
                PhoneNumber.remove(position);
                Age.remove(position);
                notifyDataSetChanged();


                notifyItemRemoved(position);




                Toast.makeText(context,value,Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return Name.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder   {
        TextView name,email,phone,age,delete;


        public ViewHolder(View itemView) {

            super(itemView);
            name=(TextView) itemView.findViewById(R.id.name);
            phone=(TextView) itemView.findViewById(R.id.phone);
            email=(TextView) itemView.findViewById(R.id.email);
            age=(TextView) itemView.findViewById(R.id.age);
            delete=(TextView) itemView.findViewById(R.id.delete);
        }


    }
}
