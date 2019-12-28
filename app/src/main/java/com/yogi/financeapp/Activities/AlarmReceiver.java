package com.yogi.financeapp.Activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class AlarmReceiver extends BroadcastReceiver {

    public AlarmReceiver() {

    }

    private static final String TAG = "AlarmReceiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent intent1 = new Intent(context, MyIntentServices.class);
        intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startService(intent1);
    }
}
