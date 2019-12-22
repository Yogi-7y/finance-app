package com.yogi.financeapp.RoomDb;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ExpenseRepository {

    private ExpenseDao expenseDao;
    private LiveData<List<ExpenseEntity>> listLiveData;

    public ExpenseRepository(Application application) {
        ExpenseDatabase expenseDatabase = ExpenseDatabase.getInstance(application);
        expenseDao = expenseDatabase.expenseDao();
        listLiveData = expenseDao.getAllEntities();
    }

    public void insert(ExpenseEntity expenseEntity) {
        new InsertAsyncTask(expenseDao).execute(expenseEntity);
    }
    public void update(ExpenseEntity expenseEntity) {

    }
    public void delete(ExpenseEntity expenseEntity) {

    }
    public void deleteAll(ExpenseEntity expenseEntity) {

    }
    public LiveData<List<ExpenseEntity>> getListLiveData() {
        return listLiveData;

    }

    private static class InsertAsyncTask extends AsyncTask<ExpenseEntity, Void, Void> {
        private ExpenseDao expenseDao;

        private InsertAsyncTask(ExpenseDao expenseDao) {
            this.expenseDao = expenseDao;
        }

        @Override
        protected Void doInBackground(ExpenseEntity... expenseEntities) {
            expenseDao.insert(expenseEntities[0]);
            return null;
        }
    }
}
