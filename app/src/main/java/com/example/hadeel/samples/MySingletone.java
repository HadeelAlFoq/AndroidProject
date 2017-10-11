package com.example.hadeel.samples;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by hadeel on 9/6/2017.
 */
public class MySingletone {
    private static MySingletone mInstance;
    private RequestQueue requestQueue;
    private  static Context mctx;

    private MySingletone(Context context){
        mctx=context;
        requestQueue=getRequestQueue();
    }
    public static synchronized MySingletone getInstance(Context context){
        if(mInstance==null){
            mInstance=new MySingletone(context);
        }
        return mInstance;
    }

    private RequestQueue getRequestQueue() {
        if(requestQueue==null){
            requestQueue= Volley.newRequestQueue(mctx.getApplicationContext());
        }
        return requestQueue;
    }
    public <T> void addTorequestqueue(Request<T> request){
        requestQueue.add(request);
    }
}