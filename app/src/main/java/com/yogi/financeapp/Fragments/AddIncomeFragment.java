package com.yogi.financeapp.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.yogi.financeapp.R;

import java.util.ArrayList;
import java.util.Calendar;

public class AddIncomeFragment extends Fragment {

    public static final String TAG = AddIncomeFragment.class.getSimpleName();

    TextInputLayout amountTextInputLayout, descriptionTextInputLayout;
    TextView dateTextView;
    Spinner categorySpinner;
    ArrayList<String> categories;
    Calendar calendar;
    LinearLayout dateLinearLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_income, container, false);

        amountTextInputLayout = view.findViewById(R.id.amountTextInputLayout);
        descriptionTextInputLayout = view.findViewById(R.id.descriptionTextInputLayout);
        dateTextView = view.findViewById(R.id.dateTextView);
        dateLinearLayout = view.findViewById(R.id.dateLinerLayout);
        categorySpinner = view.findViewById(R.id.categorySpinner);

        return view;
    }


}
