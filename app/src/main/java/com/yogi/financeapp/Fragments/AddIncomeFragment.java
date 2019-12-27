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

public class AddIncomeFragment extends Fragment implements DatePickerDialog.OnDateSetListener {

    public static final String TAG = AddIncomeFragment.class.getSimpleName();
    public static final String CONSTANT_INCOME = "INCOME";


    private TextInputLayout amountTextInputLayout, descriptionTextInputLayout;
    private TextView dateTextView, currentDateTextView;
    private Spinner categorySpinner;
    private ArrayList<String> categories;
    private Calendar calendar;
    private LinearLayout dateLinearLayout;
    private ExpenseViewModel expenseViewModel;
    private MaterialButton saveMaterialButton;
    private Date currentDate;
    String enteredDate;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_income, container, false);

        amountTextInputLayout = view.findViewById(R.id.income_amount_text_input_layout);
        descriptionTextInputLayout = view.findViewById(R.id.income_description_text_input_layout);
        dateTextView = view.findViewById(R.id.income_date);
        categorySpinner = view.findViewById(R.id.income_category);
        saveMaterialButton = view.findViewById(R.id.income_save_button);
        currentDateTextView = view.findViewById(R.id.income_current_date_text_view);

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

        calendar = Calendar.getInstance();



        expenseViewModel = ViewModelProviders.of(this).get(ExpenseViewModel.class);

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

        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date dateToEnter = dateFormat.parse(dateTextView.getText().toString());

        if (validateAmount() && validateDescription()) {
            int amount = Integer.parseInt(amountDummy);
            ExpenseEntity entity = new ExpenseEntity(amount,
                    categorySpinner.getSelectedItem().toString(),
                    description,
                    dateToEnter,
                    CONSTANT_INCOME);
            expenseViewModel.insert(entity);
            Toast.makeText(getContext(), "Entry saved", Toast.LENGTH_SHORT).show();
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

    @SuppressLint("SimpleDateFormat")
    private void setDateToTextView() {
        currentDate = calendar.getTime();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String dateToBeSet = sdf.format(currentDate);
        dateTextView.setText(dateToBeSet);

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
        enteredDate = dayOfMonth + "-" + correctMonth  + "-" + year;
        dateTextView.setText(enteredDate);
    }
}
