package com.yogi.financeapp;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.yogi.financeapp.Adapter.AllExpenseAdapter;
import com.yogi.financeapp.Fragments.AllExpensesFragment;
import com.yogi.financeapp.RoomDb.ExpenseEntity;
import com.yogi.financeapp.RoomDb.ExpenseViewModel;

public class SwipeToDeleteExpense extends ItemTouchHelper.SimpleCallback {

    private static final String TAG = "SwipeToDeleteExpense";

    private AllExpenseAdapter myAdapter;
    private ExpenseViewModel expenseViewModel;
    private Context context;
    ExpenseEntity currentEntity;
    private AllExpensesFragment fragmentManager;

    public SwipeToDeleteExpense(AllExpenseAdapter myAdapter, ExpenseViewModel expenseViewModel, Context context, AllExpensesFragment fragmentManager) {
        super(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        this.myAdapter = myAdapter;
        this.expenseViewModel = expenseViewModel;
        this.context = context;
        this.fragmentManager = fragmentManager;

    }


    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        expenseViewModel = ViewModelProviders.of(fragmentManager).get(ExpenseViewModel.class);
        int currentPosition = viewHolder.getAdapterPosition();
        Log.d(TAG, "onSwiped: count: " + currentPosition);
        currentEntity = myAdapter.getExpenseEntityAt(viewHolder.getAdapterPosition());
        Log.d(TAG, "onSwiped: entity: " + currentEntity.getAmount());
        expenseViewModel.delete(currentEntity);
        showUndoSnackBar();
    }

    private void showUndoSnackBar() {
        View view = fragmentManager.getView().findViewById(R.id.coordinator_layout);

        Snackbar snackbar = Snackbar.make(view, "The following item was deleted", Snackbar.LENGTH_LONG)
                .setAction(context.getString(R.string.undo), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        expenseViewModel.insert(currentEntity);
                    }
                });
        snackbar.show();
    }
}
