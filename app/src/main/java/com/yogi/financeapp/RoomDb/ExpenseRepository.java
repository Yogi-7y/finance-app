package com.yogi.financeapp.RoomDb;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class ExpenseRepository {

    private ExpenseDao expenseDao;
    private LiveData<List<ExpenseEntity>> listLiveData;
    private LiveData<List<ExpenseEntity>> dataToDisplay;

    public ExpenseRepository(Application application) {
        ExpenseDatabase expenseDatabase = ExpenseDatabase.getInstance(application);
        expenseDao = expenseDatabase.expenseDao();
        listLiveData = expenseDao.getAllEntities();
        dataToDisplay = expenseDao.getDataToDisplay();
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

    public LiveData<List<ExpenseEntity>> getDataToDisplay() {
        return dataToDisplay;

    }

    public List<ExpenseEntity> getAllAmount() throws ExecutionException, InterruptedException {
        return new GetAmountAsyncTask(expenseDao).execute().get();
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

    private static class GetAmountAsyncTask extends AsyncTask<Void, Void, List<ExpenseEntity>> {

        private ExpenseDao expenseDao;

        private GetAmountAsyncTask(ExpenseDao expenseDao) {
            this.expenseDao = expenseDao;
        }

        @Override
        protected List<ExpenseEntity> doInBackground(Void... voids) {
            return expenseDao.getAllAmount();
        }
    }
}
