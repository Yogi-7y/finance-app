package com.yogi.financeapp.RoomDb;

import android.content.Context;
import android.content.Entity;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.core.app.ComponentActivity;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.Date;

@Database(entities = ExpenseEntity.class, version = 15, exportSchema = false)
public abstract class ExpenseDatabase extends RoomDatabase {

    public static ExpenseDatabase expenseDatabaseInstance;

    public abstract ExpenseDao expenseDao();

    public static synchronized ExpenseDatabase getInstance(Context context) {

        if (expenseDatabaseInstance == null) {
            expenseDatabaseInstance = Room.databaseBuilder(context.getApplicationContext(),
                    ExpenseDatabase.class, "expense_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(callback)
                    .build();
        }
        return expenseDatabaseInstance;
    }

    private static RoomDatabase.Callback callback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(expenseDatabaseInstance).execute();
        }

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
        }
    };


    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {

        private ExpenseDao expenseDao;
        private PopulateDbAsyncTask(ExpenseDatabase expenseDatabase) {
            expenseDao = expenseDatabase.expenseDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
//            expenseDao.insert(new ExpenseEntity(400, "no idea", "desc", new Date(), "income"));
            return null;
        }
    }


}
