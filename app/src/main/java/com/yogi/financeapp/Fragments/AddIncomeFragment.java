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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AddIncomeFragment extends Fragment {

    public static final String TAG = AddIncomeFragment.class.getSimpleName();
    public static final String CONSTANT_INCOME = "INCOME";


    TextInputLayout amountTextInputLayout, descriptionTextInputLayout;
    TextView dateTextView;
    Spinner categorySpinner;
    ArrayList<String> categories;
    Calendar calendar;
    LinearLayout dateLinearLayout;
    private ExpenseViewModel expenseViewModel;
    MaterialButton saveMaterialButton;
    Date currentDate;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_income, container, false);

        amountTextInputLayout = view.findViewById(R.id.income_amount_text_input_layout);
        descriptionTextInputLayout = view.findViewById(R.id.income_description_text_input_layout);
        dateTextView = view.findViewById(R.id.income_date);
//        dateLinearLayout = view.findViewById(R.id.dateLinerLayout);
        categorySpinner = view.findViewById(R.id.income_category);
        saveMaterialButton = view.findViewById(R.id.income_save_button);

        categories = new ArrayList<>();
        categories.add("Food");
        categories.add("Travel");
        categories.add("Entertainment");
        categories.add("Shopping");
        categories.add("Commute");
        categories.add("Clothing");
        categories.add("Grocery");
        categories.add("Recharges and Bills");
        categories.add("Rent");
        categories.add("Health");
        categories.add("Repair and Maintenance ");
        categories.add("Loan");
        categories.add("Health");
        categories.add("Education");
        categories.add("Miscellaneous");

        categorySpinner.setAdapter(new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, categories));

        calendar = Calendar.getInstance();

        setDateToTextView();

        expenseViewModel = ViewModelProviders.of(this).get(ExpenseViewModel.class);

        saveMaterialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveExpense();
            }
        });


        return view;
    }

    private void saveExpense() {
        String description = descriptionTextInputLayout.getEditText().getText().toString().trim();
        String amountDummy = amountTextInputLayout.getEditText().getText().toString().trim();

        if (validateAmount() && validateDescription()) {
            int amount = Integer.parseInt(amountDummy);
            ExpenseEntity entity = new ExpenseEntity(amount, categorySpinner.getSelectedItem().toString(), description, currentDate, CONSTANT_INCOME);
            Log.d(TAG, "saveExpense: " + new SimpleDateFormat("dd-MM-yyyy").format(currentDate));
//        Log.d(TAG, "saveExpense: " + ' ' + entity.getDate() + entity.getId() + ' ' + entity.getCategory() + ' ' + entity.getDescription() + ' ' + entity.getAmount() + ' ' + entity.getTransactionType());
            expenseViewModel.insert(entity);
            Toast.makeText(getContext(), "Entry saved", Toast.LENGTH_SHORT).show();
        }


    }

    private boolean validateDescription() {
        if (descriptionTextInputLayout.getEditText().getText().toString().trim().isEmpty()) {
            descriptionTextInputLayout.setError("Description can't be empty");
            return false;
        } else if (descriptionTextInputLayout.getEditText().toString().trim().length() > 100) {
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

    public String setDateToTextView() {
        currentDate = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String dateToBeSet = sdf.format(currentDate);
        dateTextView.setText(dateToBeSet);
        return dateToBeSet;
    }



}
