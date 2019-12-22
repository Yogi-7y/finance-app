package com.yogi.financeapp.RoomDb;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ExpenseDao {

    @Insert
    void insert(ExpenseEntity expenseEntity);

    @Update
    void update(ExpenseEntity expenseEntity);

    @Delete
    void delete(ExpenseEntity expenseEntity);

    @Query("DELETE FROM expense_table")
    void deleteAll();

    @Query("SELECT * FROM expense_table")
    LiveData<List<ExpenseEntity>> getAllEntities();
}
