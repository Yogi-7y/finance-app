package com.yogi.financeapp.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.google.android.material.button.MaterialButton;
import com.yogi.financeapp.GetQuoteDialog;
import com.yogi.financeapp.R;

public class InsuranceDetailFragment extends Fragment {

    private static final String TAG = "InsuranceDetailFragment";

    private static final String ARGS_IMAGES_ARRAY = "ARGS_IMAGES_ARRAY";
    private static final String ARGS_DESCRIPTION = "ARGS_DESCRIPTION";

    MaterialButton getQuoteMaterialButton;

    private int[] images;
    private String description;

    public static InsuranceDetailFragment newInstance(int[] images, String description) {
        InsuranceDetailFragment insuranceDetailFragment = new InsuranceDetailFragment();
        Bundle args = new Bundle();
        args.putIntArray(ARGS_IMAGES_ARRAY, images);
        args.putString(ARGS_DESCRIPTION, description);
        insuranceDetailFragment.setArguments(args);
        return insuranceDetailFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.insurance_detail, container, false);

        getQuoteMaterialButton = view.findViewById(R.id.get_quote_button);

        SliderLayout sliderShow = view.findViewById(R.id.slider);

        description = getArguments().getString(ARGS_DESCRIPTION);
        images = getArguments().getIntArray(ARGS_IMAGES_ARRAY);



        for (int i = 0; i < images.length; i++) {
            TextSliderView textSliderView = new TextSliderView(getContext());
            textSliderView.description(description)
                    .setScaleType(BaseSliderView.ScaleType.FitCenterCrop)
                    .image(images[i]);

            sliderShow.addSlider(textSliderView);
        }
        sliderShow.stopAutoCycle();


        getQuoteMaterialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });


        return view;
    }

    private void openDialog() {
        GetQuoteDialog getQuoteDialog = new GetQuoteDialog(description, getContext());
        getQuoteDialog.show(getFragmentManager(), "Get Quote Dialog");
    }



}
