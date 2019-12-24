package com.yogi.financeapp.Adapter;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yogi.financeapp.R;
import com.yogi.financeapp.RoomDb.ExpenseEntity;
import com.yogi.financeapp.models.Blog;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class AllExpenseAdapter extends RecyclerView .Adapter<AllExpenseAdapter.AllExpenseViewHolder> {

    private Context context;
    private List<ExpenseEntity> entities;
    private List<ExpenseEntity> expenseEntities = new ArrayList<>();

//    public AllExpenseAdapter(Context context, List<ExpenseEntity> entities) {
//        this.context = context;
//        this.entities = entities;
//        expenseEntities = new ArrayList<>(entities);
//    }

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
        holder.descriptionTextView.setText(entity.getDescription());
        String amount = entity.getAmount() + "";
        holder.amountTextView.setText(amount);

    }

    @Override
    public int getItemCount() {
        return expenseEntities.size();
    }

    public void setExpenseEntities(List<ExpenseEntity> expenseEntities) {
        this.expenseEntities = expenseEntities;
        notifyDataSetChanged();
    }

    class AllExpenseViewHolder extends RecyclerView.ViewHolder {

        private TextView expenseTypeTextView;
        private TextView amountTextView;
        private TextView descriptionTextView;

        public AllExpenseViewHolder(@NonNull View itemView) {
            super(itemView);

            expenseTypeTextView = itemView.findViewById(R.id.expense_type_item);
            amountTextView = itemView.findViewById(R.id.expense_amount_item);
            descriptionTextView =  itemView.findViewById(R.id.expense_description_item);
        }
    }
}
