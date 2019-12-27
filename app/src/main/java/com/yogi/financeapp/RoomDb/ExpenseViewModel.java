package com.yogi.financeapp.RoomDb;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class ExpenseViewModel extends AndroidViewModel {

    private ExpenseRepository expenseRepository;
    private LiveData<List<ExpenseEntity>> listLiveData;
    private LiveData<List<ExpenseEntity>> dataToDisplay;
    private List<ExpenseEntity> allAmount;

    public ExpenseViewModel(@NonNull Application application) throws ExecutionException, InterruptedException {
        super(application);

        expenseRepository = new ExpenseRepository(application);
        listLiveData = expenseRepository.getListLiveData();
        dataToDisplay = expenseRepository.getDataToDisplay();
        allAmount = expenseRepository.getAllAmount();
    }

    public void insert(ExpenseEntity expenseEntity) {
        expenseRepository.insert(expenseEntity);
    }

    public LiveData<List<ExpenseEntity>> getListLiveData() {
        return listLiveData;
    }

    public List<ExpenseEntity> getAllAmount() {
        return allAmount;
    }

    public LiveData<List<ExpenseEntity>> getDataToDisplay() {
        return dataToDisplay;
    }
}
