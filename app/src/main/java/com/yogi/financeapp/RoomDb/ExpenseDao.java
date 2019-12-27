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

    @Query("SELECT * FROM expense_table ORDER BY date DESC")
    LiveData<List<ExpenseEntity>> getAllEntities();

    @Query("SELECT * FROM expense_table")
    List<ExpenseEntity> getAllAmount();

//    @Query("SELECT * FROM fare WHERE createdDateDb >=datetime('now', '-30 day')")
//    @Query("SELECT * FROM expense_table WHERE date >= datetime('now', '-7 day')")
    @Query("SELECT * FROM expense_table WHERE date >= DATE('now', '-10 day')")
    LiveData<List<ExpenseEntity>> getDataToDisplay();

}
