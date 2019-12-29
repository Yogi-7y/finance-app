package com.yogi.financeapp.Adapter;

import android.content.Context;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.yogi.financeapp.Fragments.AddExpenseFragment;
import com.yogi.financeapp.Fragments.AddIncomeFragment;
import com.yogi.financeapp.Fragments.AllExpensesFragment;
import com.yogi.financeapp.R;
import com.yogi.financeapp.RoomDb.ExpenseEntity;
import com.yogi.financeapp.models.Blog;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class AllExpenseAdapter extends RecyclerView .Adapter<AllExpenseAdapter.AllExpenseViewHolder> {

    public static final String TAG = AllExpensesFragment.class.getSimpleName();

    private Context context;
    private List<ExpenseEntity> entities;
    private List<ExpenseEntity> expenseEntities = new ArrayList<>();

    public AllExpenseAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public AllExpenseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.expense_item, parent, false);

        return new AllExpenseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllExpenseViewHolder holder, int position) {

        ExpenseEntity entity = expenseEntities.get(position);

        holder.expenseTypeTextView.setText(entity.getTransactionType());
        holder.setIsRecyclable(true);
        if (entity.getTransactionType().equals(AddIncomeFragment.CONSTANT_INCOME)) {
            holder.materialCardView.setStrokeColor(holder.itemView.getContext().getResources().getColor(R.color.blue));
        } else if (entity.getTransactionType().equals(AddExpenseFragment.CONSTANT_EXPENSE)) {
            holder.materialCardView.setStrokeColor(holder.itemView.getContext().getResources().getColor(R.color.red));
        }

        SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
        String dateToBeSet=sdf.format(entity.getDate());
        holder.dateTextView.setText(dateToBeSet);


//        holder.dateTextView.setText(entity.getDate().toString());
        Log.d(TAG, "onBindViewHolder: Date:  " + entity.getDate());
        holder.descriptionTextView.setText(entity.getDescription());
        String amount = entity.getAmount() + "";
        holder.amountTextView.setText(amount);
        holder.categoryTextView.setText(entity.getCategory());
        
        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "delete this item out of existence bitch", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return expenseEntities.size();
    }

    public void setExpenseEntities(List<ExpenseEntity> expenseEntities) {
        this.expenseEntities = expenseEntities;
        notifyDataSetChanged();
    }

    public void deleteItem(int position) {
        ExpenseEntity recentlyDeletedItem = expenseEntities.get(position);
        int recentlyDeletedItemPosiiton = position;
        expenseEntities.remove(position);
        notifyItemRemoved(position);
        showUndoSnackBar();
    }

    private void showUndoSnackBar() {
        Toast.makeText(context, "Undo Snackbar", Toast.LENGTH_SHORT).show();
    }

    public ExpenseEntity getExpenseEntityAt(int position) {
        return expenseEntities.get(position);

    }

    class AllExpenseViewHolder extends RecyclerView.ViewHolder {

        private TextView expenseTypeTextView;
        private TextView amountTextView;
        private TextView descriptionTextView;
        private TextView dateTextView;
        private TextView categoryTextView;
        private MaterialCardView materialCardView;
        private ImageButton deleteButton;

        public AllExpenseViewHolder(@NonNull View itemView) {
            super(itemView);

            expenseTypeTextView = itemView.findViewById(R.id.expense_type_item);
            amountTextView = itemView.findViewById(R.id.expense_amount_item);
            descriptionTextView =  itemView.findViewById(R.id.expense_description_item);
            dateTextView = itemView.findViewById(R.id.expense_date_item);
            categoryTextView= itemView.findViewById(R.id.category_expense_item);
            materialCardView = itemView.findViewById(R.id.expense_card_view);
            deleteButton = itemView.findViewById(R.id.delete_button);
        }
    }
}
