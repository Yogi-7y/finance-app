package com.yogi.financeapp.Activities;

import android.app.IntentService;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.github.mikephil.charting.charts.PieChart;
import com.yogi.financeapp.App;
import com.yogi.financeapp.R;

public class MyIntentServices extends IntentService {

    public static final int NOTIFICATION_ID = 3;
    private NotificationManagerCompat notificationManagerCompat;

    public MyIntentServices() {
        super("MyIntentServices");
    }


    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        notificationManagerCompat = NotificationManagerCompat.from(this);

        Intent activityIntent = new Intent(getApplicationContext(), HomeActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, activityIntent, 0);

        Notification notification = new NotificationCompat.Builder(this, App.CHANNEL_1_ID)
                .setSmallIcon(R.drawable.ic_event_available_black_24dp)
                .setContentTitle("Finance App")
                .setContentText("Hello, Did you forget to add today's income and expenses?")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setContentIntent(pendingIntent)
                .build();

        notificationManagerCompat.notify(1, notification);

//        Notification.Builder builder = new Notification.Builder(this);
//        builder.setContentTitle("My Title");
//        builder.setContentText("This is the Body");
//        builder.setSmallIcon(R.drawable.hearthealth);

//        Intent notifyIntent = new Intent(this, MainActivity.class);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 2, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);

//        builder.setContentIntent(pendingIntent);
//        Notification notificationCompat = notification;
//        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
//        managerCompat.notify(NOTIFICATION_ID, notificationCompat);
    }
}
