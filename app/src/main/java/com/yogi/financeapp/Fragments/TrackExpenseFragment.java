package com.yogi.financeapp.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.yogi.financeapp.Adapter.ExpenseAdapter;
import com.yogi.financeapp.R;

public class TrackExpenseFragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    ExpenseAdapter expenseAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_expense_tracker, container, false);

        viewPager = view.findViewById(R.id.expense_view_pager);
        tabLayout = view.findViewById(R.id.expense_manager_tabs);

        setViewPager(viewPager);

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_attach_money_black_24dp);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_money_off_black_24dp);



        return view;
    }

    private void setViewPager(ViewPager viewPager) {

        expenseAdapter = new ExpenseAdapter(getChildFragmentManager(), 0);
        expenseAdapter.addFragment(new AddIncomeFragment(), "Add Income");
        expenseAdapter.addFragment(new AddExpenseFragment(), "Add Expense");

        viewPager.setAdapter(expenseAdapter);

    }
}
