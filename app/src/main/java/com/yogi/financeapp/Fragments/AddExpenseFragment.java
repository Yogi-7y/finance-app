package com.yogi.financeapp.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.yogi.financeapp.R;
import com.yogi.financeapp.RoomDb.ExpenseEntity;
import com.yogi.financeapp.RoomDb.ExpenseViewModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class AddExpenseFragment extends Fragment {
    public static final String TAG = AddExpenseFragment.class.getSimpleName();
    public static final String CONSTANT_EXPENSE = "EXPENSE";

    TextInputLayout amountTextInputLayout, descriptionTextInputLayout;
    TextView dateTextView;
    Spinner categorySpinner;
    ArrayList<String> categories;
    Calendar calendar;
    LinearLayout dateLinearLayout;
    private ExpenseViewModel expenseViewModel;
    MaterialButton saveMaterialButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_expense, container, false);

        amountTextInputLayout = view.findViewById(R.id.expense_amount_text_input_layout);
        descriptionTextInputLayout = view.findViewById(R.id.expense_description_text_input_layout);
        dateTextView = view.findViewById(R.id.expense_date);
//        dateLinearLayout = view.findViewById(R.id.dateLinerLayout);
        categorySpinner = view.findViewById(R.id.expense_category);
        saveMaterialButton = view.findViewById(R.id.expense_save_button);

        categories = new ArrayList<>();
        categories.add("Paycheck");
        categories.add("Interest");
        categories.add("Rental Income");
        categories.add("Profit/Capital Gain");
        categories.add("Reimbursement");
        categories.add("Bonus");
        categories.add("Royalty");
        categories.add("Others");

        categorySpinner.setAdapter(new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, categories));


        saveMaterialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveExpense();
            }
        });

        expenseViewModel = ViewModelProviders.of(this).get(ExpenseViewModel.class);
        return view;
    }

    private void saveExpense() {

        String description = descriptionTextInputLayout.getEditText().getText().toString().trim();
        String amountDummy = amountTextInputLayout.getEditText().getText().toString().trim();


        if (validateAmount() && validateDescription()) {
            int amount = Integer.parseInt(amountDummy);
            ExpenseEntity entity = new ExpenseEntity(amount, "Category", description, new Date(), CONSTANT_EXPENSE);
//        Log.d(TAG, "saveExpense: " + ' ' + entity.getDate() + entity.getId() + ' ' + entity.getCategory() + ' ' + entity.getDescription() + ' ' + entity.getAmount() + ' ' + entity.getTransactionType());
            expenseViewModel.insert(entity);
            Toast.makeText(getContext(), "Entry saved", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean validateDescription() {
        if (descriptionTextInputLayout.getEditText().getText().toString().trim().isEmpty()) {
            descriptionTextInputLayout.setError("Description can't be empty");
            return false;
        } else if (descriptionTextInputLayout.getEditText().getText().toString().trim().length() > 100) {
            descriptionTextInputLayout.setError("Description too long!!");
            return false;
        }
        return true;
    }

    private boolean validateAmount() {
        if (amountTextInputLayout.getEditText().getText().toString().trim().isEmpty()) {
            amountTextInputLayout.setError("Amount can't be empty");
            return false;
        }
        return true;
    }
}
