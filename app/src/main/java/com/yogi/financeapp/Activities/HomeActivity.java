package com.yogi.financeapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.yogi.financeapp.Fragments.TrackExpenseFragment;
import com.yogi.financeapp.Fragments.AllExpensesFragment;
import com.yogi.financeapp.Fragments.BlogsFragment;
import com.yogi.financeapp.Fragments.GraphFragment;
import com.yogi.financeapp.Fragments.ToolsFragment;
import com.yogi.financeapp.R;

public class HomeActivity extends AppCompatActivity {

    public static final String TAG = HomeActivity.class.getSimpleName();
    BottomNavigationView bottomNavigationView;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bottomNavigationView = findViewById(R.id.bottom_navigation_view);

        BottomNavigationView navigation = findViewById(R.id.bottom_navigation_view);
        navigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener);



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

        if (count == 0) {
            super.onBackPressed();
            //additional code
        } else {
            getSupportFragmentManager().popBackStack();
        }

    }


}
