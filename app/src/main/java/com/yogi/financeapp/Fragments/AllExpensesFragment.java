package com.yogi.financeapp.Fragments;

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

import com.yogi.financeapp.R;
import com.yogi.financeapp.RoomDb.ExpenseEntity;
import com.yogi.financeapp.RoomDb.ExpenseViewModel;

import java.util.List;

public class AllExpensesFragment extends Fragment {

    private ExpenseViewModel expenseViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_all_expenses, container, false);

        expenseViewModel = ViewModelProviders.of(this).get(ExpenseViewModel.class);
        expenseViewModel.getListLiveData().observe(this, new Observer<List<ExpenseEntity>>() {
            @Override
            public void onChanged(List<ExpenseEntity> expenseEntities) {
                Toast.makeText(getActivity().getApplicationContext(), "On Changed", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
