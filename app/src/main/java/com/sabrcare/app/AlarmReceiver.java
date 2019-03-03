package com.sabrcare.app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.sabrcare.app.medicine.AlarmActivity;

import java.util.ArrayList;

import androidx.core.app.NotificationCompat;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println(">>>>>>>>>>>>>>>>>>>>Recieved");

        //Notif

        NotificationHelper notificationHelper = new NotificationHelper(context);
        NotificationCompat.Builder nb = notificationHelper.getChannelNotification();
        notificationHelper.getManager().notify(1, nb.build());

        String medList = intent.getStringExtra("medications");

        Intent openAlarm = new Intent(context,AlarmActivity.class);
        openAlarm.putExtra("medications", medList);
        System.out.println("RECIEVED MEDS>>>>>>>>>>>>>"+medList);
        context.startActivity(openAlarm);
    }
}
