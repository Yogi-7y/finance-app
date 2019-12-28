package com.yogi.financeapp;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class App extends Application {

    public static final String CHANNEL_1_ID = "CHANNEL_1_ID";
    public static final String CHANNEL_2_ID = "CHANNEL_2_ID";

    @Override
    public void onCreate() {
        super.onCreate();

        createNotificationChannels();
    }

    private void createNotificationChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel1 = new NotificationChannel(
                    CHANNEL_1_ID,
                    "Channel 1",
                    NotificationManager.IMPORTANCE_HIGH
            );

            notificationChannel1.setDescription("Hello, Did you forget to add today's income and expenses?");
            NotificationChannel notificationChannel2 = new NotificationChannel(
                    CHANNEL_1_ID,
                    "Channel 2",
                    NotificationManager.IMPORTANCE_LOW
            );

            notificationChannel2.setDescription("Hello, Did you forget to add today's income and expenses? 2");

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(notificationChannel1);
            notificationManager.createNotificationChannel(notificationChannel2);
        }
    }
}
