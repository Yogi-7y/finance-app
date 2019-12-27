package com.yogi.financeapp.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.card.MaterialCardView;
import com.yogi.financeapp.R;

public class ToolsFragment extends Fragment {

    MaterialCardView carInsuranceMaterialCardView, termInsuranceMaterialCardView,
            healthInsuranceMaterialCardView, twoWheelerInsuranceMaterialCardView;

    private int[] images;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tools, container, false);

        carInsuranceMaterialCardView = view.findViewById(R.id.car_insurance_list);
        termInsuranceMaterialCardView = view.findViewById(R.id.term_insurance_list);
        healthInsuranceMaterialCardView = view.findViewById(R.id.health_insurance_list);
        twoWheelerInsuranceMaterialCardView = view.findViewById(R.id.two_wheeler_insurance_list);


        carInsuranceMaterialCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                images = new int[]{R.drawable.car_insurance_one, R.drawable.car_insurance_two, R.drawable.car_insurance_three};
                Toast.makeText(getContext(), "Car Insurance", Toast.LENGTH_SHORT).show();
                InsuranceDetailFragment insuranceDetailFragment = InsuranceDetailFragment.newInstance(images, "Car Insurance");

                getFragmentManager().beginTransaction().replace(R.id.frame_container, insuranceDetailFragment).commit();
            }
        });

        termInsuranceMaterialCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                images = new int[]{R.drawable.term_insurance_one, R.drawable.term_insurance_two};

                InsuranceDetailFragment insuranceDetailFragment = InsuranceDetailFragment.newInstance(images, "Term Insurance");
                getFragmentManager().beginTransaction().replace(R.id.frame_container, insuranceDetailFragment).commit();


                Toast.makeText(getContext(), "Term", Toast.LENGTH_SHORT).show();
            }
        });

        healthInsuranceMaterialCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                images = new int[]{R.drawable.health_insurance_one, R.drawable.health_insurance_two, R.drawable.health_insurance_three, R.drawable.health_insurance_four};

                InsuranceDetailFragment insuranceDetailFragment = InsuranceDetailFragment.newInstance(images, "Health Insurance");

                getFragmentManager().beginTransaction().replace(R.id.frame_container, insuranceDetailFragment).commit();
                Toast.makeText(getContext(), "Health", Toast.LENGTH_SHORT).show();
            }
        });

        twoWheelerInsuranceMaterialCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                images = new int[]{R.drawable.two_wheeler_insurance_one, R.drawable.two_wheeler_insurance_two};
                InsuranceDetailFragment insuranceDetailFragment = InsuranceDetailFragment.newInstance(images, "Two-Wheeler Insurance");

                getFragmentManager().beginTransaction().replace(R.id.frame_container, insuranceDetailFragment).commit();
                Toast.makeText(getContext(), "Two wheeler", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
