package com.example.hadeel.samples;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;

/**
 * Created by hadeel on 9/12/2017.
 */

public class MyFirebaseInstanceIdService extends com.google.firebase.iid.FirebaseInstanceIdService {

    private static final String TAG="MyFirebaseIIDService" ;

    public void onTokenRefreshed(){
        String refreshedToken= FirebaseInstanceId.getInstance().getToken();
        Log.e(TAG,"RefreshedToken :"+refreshedToken);
    }
    private void sendRegistrationToServer(String token) {

    }
}
