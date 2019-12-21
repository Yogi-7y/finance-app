package com.yogi.financeapp;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {

    public static final String TAG = HomeActivity.class.getSimpleName();
    BottomNavigationView bottomNavigationView;

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
                            fragment = new AddExpensesFragment();
                            break;
                        case R.id.blogs:
//                            toolbar.setTitle("Blogs");
                            fragment = new BlogsFragment();
                            break;
                        case R.id.expenses_list:
//                            toolbar.setTitle("All Expenses");
                            fragment = new AllExpensesFragment();
                            break;

                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, fragment).commit();
                    return true;

                }
            };


}
