package com.example.hadeel.samples;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by hadeel on 9/7/2017.
 */

public class SqliteHelper extends SQLiteOpenHelper {
    static String DATABASE_NAME="AndroidJSonDataBase";

    public static final String TABLE_NAME="OrangeTable";

    public static final String Table_Column_ID="id";

    public static final String Table_Column_1_Name="name";

    public static final String Table_Column_2_PhoneNumber="phone_number";
    public static final String Table_Column_3_Email="email";
    public static final String Table_Column_4_Age="age";

    public static final String TABLE_NAME_2="commentTable";

    public static final String Table2_Column_ID="id";

    public static final String Table2_Column_1_Comment="comment";
    public int countComment=0;



    public SqliteHelper(Context context) {

        super(context, DATABASE_NAME, null, 5);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE="CREATE TABLE IF NOT EXISTS OrangeTable( id  INTEGER PRIMARY KEY,  name VARCHAR, phone_number VARCHAR,email VARCHAR,age VARCHAR )";
        db.execSQL(CREATE_TABLE);

        String CREATE_TABLE_COMMENT="CREATE TABLE IF NOT EXISTS commentTable( id  INTEGER PRIMARY KEY,  comment VARCHAR )";
        db.execSQL(CREATE_TABLE_COMMENT);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME_2);

        onCreate(db);

    }
    public void insert(String Name,String Phone,String Email,String Age)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("name",Name);
        contentValues.put("phone_number",Phone);
        contentValues.put("email",Email);
        contentValues.put("age",Age);
        db.insert("OrangeTable",null,contentValues);

    }
    public void insertComment(String Comment){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("comment",Comment);
        db.insert("commentTable",null,contentValues);
    }
 public int getCommentCounter(){

        //sqLiteDatabase = sqLiteHelper.getReadableDatabase();

        String query="SELECT comment FROM commentTable";
        Cursor cursor = getReadableDatabase().rawQuery(query, null);
        if(cursor.getCount()>0){
            cursor.moveToFirst();
         countComment=countComment+1;

         }close();
     return countComment;
 }
}

