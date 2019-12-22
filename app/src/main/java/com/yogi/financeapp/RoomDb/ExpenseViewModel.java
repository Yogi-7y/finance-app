package com.yogi.financeapp.RoomDb;

import android.app.Application;
import android.app.ListActivity;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ExpenseViewModel extends AndroidViewModel {

    private ExpenseRepository expenseRepository;
    private LiveData<List<ExpenseEntity>> listLiveData;

    public ExpenseViewModel(@NonNull Application application) {
        super(application);

        expenseRepository = new ExpenseRepository(application);
        listLiveData = expenseRepository.getListLiveData();
    }

    public void insert(ExpenseEntity expenseEntity){
        expenseRepository.insert(expenseEntity);
    }

    public LiveData<List<ExpenseEntity>> getListLiveData() {
        return listLiveData;
    }
}
