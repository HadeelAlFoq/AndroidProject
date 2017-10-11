package com.example.hadeel.samples;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v7.app.NotificationCompat;

import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by hadeel on 9/12/2017.
 */


public class MyFirebaseMessagingService extends  com.google.firebase.messaging.FirebaseMessagingService {
    private static final String TAG="MyFirebaseIIDService" ;
//
//    public void onMessageReceived(RemoteMessage remoteMessage){
//        sendNotification(remoteMessage.getNotification().getTitle(),remoteMessage.getNotification().getBody());
//    }

//    private void sendNotification(String title, String messageBody) {
//        Intent i= new Intent(this,MainActivity.class);
//        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        PendingIntent pendingIntent=PendingIntent.getActivity(this,0,i,PendingIntent.FLAG_ONE_SHOT);
//        Uri defaulteSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//        NotificationCompat.Builder notificationBuilder= (NotificationCompat.Builder) new NotificationCompat.Builder(this).setSmallIcon(R.mipmap.ic_launcher)
//                .setContentTitle(title).setContentText(messageBody).setAutoCancel(true).setSound(defaulteSoundUri).setContentIntent(pendingIntent);
//
//        NotificationManager notificationManager=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
//        notificationManager.notify(0,notificationBuilder.build());
//    }
}