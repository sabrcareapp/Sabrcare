package com.sabrcare.app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.sabrcare.app.medicine.AlarmActivity;

import androidx.core.app.NotificationCompat;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println(">>>>>>>>>>>>>>>>>>>>Recieved");

        //Notif

        NotificationHelper notificationHelper = new NotificationHelper(context);
        NotificationCompat.Builder nb = notificationHelper.getChannelNotification();
        notificationHelper.getManager().notify(1, nb.build());

        String med = intent.getStringExtra("medication");

        Intent openAlarm = new Intent(context,AlarmActivity.class);
        openAlarm.putExtra("medication", med);
        System.out.println("RECIEVED MED>>>>>>>>>>>>>"+med);
        context.startActivity(openAlarm);
    }
}
