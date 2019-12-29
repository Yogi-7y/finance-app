package com.yogi.financeapp.Activities;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;

import com.allyants.notifyme.NotifyMe;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.yogi.financeapp.Fragments.TrackExpenseFragment;
import com.yogi.financeapp.Fragments.AllExpensesFragment;
import com.yogi.financeapp.Fragments.BlogsFragment;
import com.yogi.financeapp.Fragments.GraphFragment;
import com.yogi.financeapp.Fragments.ToolsFragment;
import com.yogi.financeapp.R;

import java.util.Calendar;
import java.util.Date;

public class HomeActivity extends AppCompatActivity {

    public static final String TAG = HomeActivity.class.getSimpleName();
    BottomNavigationView bottomNavigationView;
    Intent intent;
    boolean doubleBackToExitPressedOnce = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

//        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 21);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
//
//        Intent intent = new Intent("DISPLAY_NOTIFICATION");
//        PendingIntent broadcast = PendingIntent.getBroadcast(this, 100, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//        alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), broadcast);
//
//
        bottomNavigationView = findViewById(R.id.bottom_navigation_view);

        BottomNavigationView navigation = findViewById(R.id.bottom_navigation_view);
        navigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener);

        if (calendar.getTime().compareTo(new Date()) < 0) calendar.add(Calendar.DAY_OF_MONTH, 1);

        Intent notifyIntent = new Intent(this, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 100, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        if (alarmManager != null) {
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
        }

        Fragment fragment = new GraphFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, fragment).commit();


    }

    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment fragment = null;

                    switch (item.getItemId()) {
                        case R.id.line_graph:
//                            toolbar.setTitle("Graph");
                            fragment = new GraphFragment();
                            break;
                        case R.id.tools:
//                            toolbar.setTitle("Tools");
                            fragment = new ToolsFragment();
                            break;
                        case R.id.track_expense:
//                            toolbar.setTitle("Track Expenses");
                            fragment = new TrackExpenseFragment();
                            break;
                        case R.id.blogs:
//                            toolbar.setTitle("Blogs");
                            fragment = new BlogsFragment();
//                            intent = new Intent(HomeActivity.this, TestActivity.class);
//                            startActivity(intent);

                            break;
                        case R.id.expenses_list:
//                            toolbar.setTitle("All Expenses");
                            fragment = new AllExpensesFragment();
                            break;

                    }

                    assert fragment != null;
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, fragment).commit();
                    return true;

                }
            };

    @Override
    public void onBackPressed() {

        int count = getSupportFragmentManager().getBackStackEntryCount();
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
//        if (count == 0) {
//            super.onBackPressed();
//            //additional code
//        } else {
//            getSupportFragmentManager().popBackStack();
//        }

    }


}
