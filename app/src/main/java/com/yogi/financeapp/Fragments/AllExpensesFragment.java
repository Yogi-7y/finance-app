package com.yogi.financeapp.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yogi.financeapp.Adapter.AllExpenseAdapter;
import com.yogi.financeapp.Adapter.ExpenseAdapter;
import com.yogi.financeapp.R;
import com.yogi.financeapp.RoomDb.ExpenseEntity;
import com.yogi.financeapp.RoomDb.ExpenseViewModel;
import com.yogi.financeapp.models.Blog;

import java.util.ArrayList;
import java.util.List;

public class AllExpensesFragment extends Fragment {

    private ExpenseViewModel expenseViewModel;
    private RecyclerView recyclerView;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_all_expenses, container, false);

        recyclerView = view.findViewById(R.id.all_expense_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        final AllExpenseAdapter allExpenseAdapter = new AllExpenseAdapter();
        recyclerView.setAdapter(allExpenseAdapter);


        expenseViewModel = ViewModelProviders.of(this).get(ExpenseViewModel.class);
        expenseViewModel.getListLiveData().observe(this, new Observer<List<ExpenseEntity>>() {
            @Override
            public void onChanged(List<ExpenseEntity> expenseEntities) {
                Toast.makeText(getActivity().getApplicationContext(), "On Changed", Toast.LENGTH_SHORT).show();
                allExpenseAdapter.setExpenseEntities(expenseEntities);
            }
        });

        return view;
    }
}
