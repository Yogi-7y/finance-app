package com.yogi.financeapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.allyants.notifyme.NotifyMe;
import com.yogi.financeapp.R;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    public final int SPLASH_SCREEN_TIME_OUT = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NotifyMe.Builder builder = new NotifyMe.Builder(getApplicationContext());

        builder.title("Hey");
        builder.content("there");
        builder.color(255, 0, 0, 5);//Color of notification header
        builder.led_color(255, 0, 0, 0);//Color of LED when notification pops up
        builder.time(Calendar.getInstance());//The time to popup notification
//        builder.delay(400);//Delay in ms
        builder.large_icon(R.drawable.hearthealth);//Icon resource by ID
//        builder.rrule("FREQ=MINUTELY;INTERVAL=5;COUNT=2")//RRULE for frequency of notification
//        builder.addAction(Intent intent,String text); //The action will call the intent when pressed
        builder.build();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                MainActivity.this.startActivity(intent);
                MainActivity.this.finish();
            }
        }, SPLASH_SCREEN_TIME_OUT);
    }



}
