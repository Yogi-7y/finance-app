package com.yogi.financeapp.Fragments;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class AddExpenseFragment extends Fragment implements DatePickerDialog.OnDateSetListener {
    public static final String TAG = AddExpenseFragment.class.getSimpleName();
    public static final String CONSTANT_EXPENSE = "EXPENSE";

    TextInputLayout amountTextInputLayout, descriptionTextInputLayout;
    TextView dateTextView, currentDateTextView;
    Spinner categorySpinner;
    ArrayList<String> categories;
    Calendar calendar;
    private ExpenseViewModel expenseViewModel;
    MaterialButton saveMaterialButton;
    Date currentDate;
    String enteredDate;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_expense, container, false);

        amountTextInputLayout = view.findViewById(R.id.expense_amount_text_input_layout);
        descriptionTextInputLayout = view.findViewById(R.id.expense_description_text_input_layout);
        dateTextView = view.findViewById(R.id.expense_date);
        currentDateTextView = view.findViewById(R.id.expense_current_date_text_view);

        calendar = Calendar.getInstance();



        expenseViewModel = ViewModelProviders.of(this).get(ExpenseViewModel.class);

        categorySpinner = view.findViewById(R.id.expense_category);
        saveMaterialButton = view.findViewById(R.id.expense_save_button);
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


        saveMaterialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    saveExpense();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });

        dateTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        currentDateTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDateToTextView();
            }
        });


        return view;
    }

    private void saveExpense() throws ParseException {

        String description = descriptionTextInputLayout.getEditText().getText().toString().trim();
        String amountDummy = amountTextInputLayout.getEditText().getText().toString().trim();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date dateToEnter = dateFormat.parse(dateTextView.getText().toString());


        if (validateAmount() && validateDescription()) {
            int amount = Integer.parseInt(amountDummy);
            ExpenseEntity entity = new ExpenseEntity(amount,
                    categorySpinner.getSelectedItem().toString(),
                    description,
                    dateToEnter,
                    CONSTANT_EXPENSE);
            expenseViewModel.insert(entity);
            Toast.makeText(getContext(), "Entry saved", Toast.LENGTH_SHORT).show();

            amountTextInputLayout.getEditText().setText("");
            descriptionTextInputLayout.getEditText().setText("");
            setDateToTextView();
        }
    }

    private boolean validateDescription() {
        if (descriptionTextInputLayout.getEditText().getText().toString().trim().isEmpty()) {
            descriptionTextInputLayout.setError("Description can't be empty");
            return false;
        } else if (descriptionTextInputLayout.getEditText().getText().toString().trim().length() > 60) {
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

    private void showDatePickerDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                getContext(),
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        int correctMonth = month + 1;
        enteredDate = dayOfMonth + "-" + correctMonth + "-" + year;
        dateTextView.setText(enteredDate);
    }


    private void setDateToTextView() {
        currentDate = calendar.getTime();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String dateToBeSet = sdf.format(currentDate);
        dateTextView.setText(dateToBeSet);
    }
}
