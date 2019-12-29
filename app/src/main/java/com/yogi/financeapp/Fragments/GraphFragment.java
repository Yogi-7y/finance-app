package com.yogi.financeapp.Fragments;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.yogi.financeapp.R;
import com.yogi.financeapp.RoomDb.ExpenseEntity;
import com.yogi.financeapp.RoomDb.ExpenseViewModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import lecho.lib.hellocharts.formatter.AxisValueFormatter;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.view.LineChartView;

public class GraphFragment extends Fragment {

    private static final String[] days = new String[]{"SUNDAY", "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY"};

    private static final String TAG = GraphFragment.class.getSimpleName();
    LineChart lineChart;
    private ExpenseViewModel expenseViewModel;
    ArrayList<Entry> incomeValues, expenseValues;
    Date dayAfter;
    List<ExpenseEntity> expenseEntityList;
    LineChartView lineChartView;
    int dayNumber;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_graph, container, false);

        lineChart = view.findViewById(R.id.line_chart);
        incomeValues = new ArrayList<>();
        expenseValues = new ArrayList<>();
        expenseViewModel = ViewModelProviders.of(this).get(ExpenseViewModel.class);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -7);
        Log.d(TAG, "onCreateView: date: " + calendar.getTime().toString());
        dayAfter = calendar.getTime();


        expenseEntityList = expenseViewModel.getAllAmount();


        LineDataSet incomeLineDataSet = new LineDataSet(incomeValues(), "Income Values");
        incomeLineDataSet.setLineWidth(3f);
        incomeLineDataSet.setColor(Color.GREEN);
        incomeLineDataSet.setHighlightEnabled(true);
        incomeLineDataSet.setHighLightColor(Color.GREEN);
        incomeLineDataSet.setCircleRadius(4f);
        incomeLineDataSet.setFillAlpha(120);
        incomeLineDataSet.setValueTextSize(10f);


        LineDataSet expenseLineDataSet = new LineDataSet(getExpenseValues(), "Expense Values");
        expenseLineDataSet.setLineWidth(3f);
        expenseLineDataSet.setColor(Color.RED);
        expenseLineDataSet.setHighlightEnabled(true);
        expenseLineDataSet.setHighLightColor(Color.RED);
        expenseLineDataSet.setCircleRadius(4f);
        expenseLineDataSet.setValueTextSize(10f);


        ArrayList<ILineDataSet> iLineDataSets = new ArrayList<>();
        iLineDataSets.add(incomeLineDataSet);
        iLineDataSets.add(expenseLineDataSet);

        LineData lineData = new LineData(iLineDataSets);
        lineChart.setData(lineData);
        lineChart.invalidate();

        lineChart.setBackgroundColor(Color.WHITE);
        lineChart.setNoDataText("No Data Available");
        lineChart.setNoDataTextColor(Color.RED);


        lineChart.setDrawBorders(true);
        lineChart.setBorderColor(Color.RED);

        Description description = new Description();
        description.setText("Stats for the last 7 days");
        description.setTextColor(Color.BLUE);
        description.setTextSize(14);
        lineChart.setDescription(description);


        lineChart.animateX(3000, Easing.EaseInOutSine);
        lineChart.animateY(3000, Easing.EaseInOutSine);

        lineChart.setScaleEnabled(true);
        lineChart.setDragEnabled(true);

        Legend legend = lineChart.getLegend();
        legend.setFormSize(10f);
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setTextSize(12f);
        legend.setTextColor(Color.BLACK);
        legend.setXEntrySpace(5f); // set the space between the legend entries on the x-axis
        legend.setYEntrySpace(5f);

        final ArrayList<String> xLabel = new ArrayList<>();
        xLabel.add("9");
        xLabel.add("15");
        xLabel.add("21");
        xLabel.add("27");
        xLabel.add("33");
        xLabel.add("33");
        xLabel.add("33");

        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return xLabel.get((int) value);
            }
        });

        Log.d(TAG, "onCreateView: day of week " + Calendar.getInstance().get(Calendar.DAY_OF_WEEK));


        return view;

    }


    private ArrayList<Entry> incomeValues() {
        ArrayList<Entry> entries = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();


        int sunday = 0, monday = 0, tuesday = 0, wednesday = 0, thursday = 0, friday = 0, saturday = 0;
        int income = 0;


        for (int i = 0; i < expenseEntityList.size(); i++) {
            if (expenseEntityList.get(i).getTransactionType().equals(AddIncomeFragment.CONSTANT_INCOME)
                    && expenseEntityList.get(i).getDate().after(dayAfter)) {

                calendar.setTime(expenseEntityList.get(i).getDate());
                dayNumber = calendar.get(Calendar.DAY_OF_WEEK) - 1;
                String day = days[dayNumber];


                switch (day) {
                    case "SUNDAY":
                        sunday += expenseEntityList.get(i).getAmount();
                        break;
                    case "MONDAY":
                        monday += expenseEntityList.get(i).getAmount();

                        break;
                    case "TUESDAY":
                        tuesday += expenseEntityList.get(i).getAmount();

                        Log.d(TAG, "incomeValues: tue:" + tuesday);
                        break;
                    case "WEDNESDAY":
                        wednesday = expenseEntityList.get(i).getAmount();
//                        wednesday += inWednesday;
                        break;
                    case "THURSDAY":
                        thursday += expenseEntityList.get(i).getAmount();

                        break;
                    case "FRIDAY":
                        friday += expenseEntityList.get(i).getAmount();

                        break;
                    case "SATURDAY":
                        saturday += expenseEntityList.get(i).getAmount();

                        break;
                    default:
                        break;
                }

            }




//            if (tuesday == 0) tuesday = monday;
//            if (wednesday == 0) {
//                wednesday = tuesday;
//                Log.d(TAG, "incomeValues: wednes: " + wednesday);
//            }
//            if (thursday == 0) {
//                thursday += wednesday;
//                Log.d(TAG, "incomeValues: wed: " + wednesday);
//            }
//
//            if (friday == 0) friday = thursday;
//            if (saturday == 0) saturday = friday;
//            if (sunday == 0) sunday = saturday;
//
//            if (saturday > sunday) sunday = saturday;

//            Log.d(TAG, "incomeValues: day after: " + dayAfter);
//            Log.d(TAG, "incomeValues: daysvalues mon " + monday);
//            Log.d(TAG, "incomeValues: daysvalues tue " + tuesday);
//            Log.d(TAG, "incomeValues: daysvalues wed " + wednesday);
//            Log.d(TAG, "incomeValues: daysvalues thurs " + thursday);
//            Log.d(TAG, "incomeValues: daysvalues fri " + friday);
//            Log.d(TAG, "incomeValues: daysvalues sat " + saturday);
//            Log.d(TAG, "incomeValues: daysvalues sun " + sunday);

        }

        tuesday += monday;
        wednesday += tuesday;
        thursday += wednesday;
        friday += thursday;
        saturday += friday;
        sunday += saturday;





        entries.add(new Entry(1, monday));
        entries.add(new Entry(2, tuesday));
        entries.add(new Entry(3, wednesday));
        entries.add(new Entry(4, thursday));
        entries.add(new Entry(5, friday));
        entries.add(new Entry(6, saturday));
        entries.add(new Entry(7, sunday));
        return entries;
    }

    private ArrayList<Entry> getExpenseValues() {
        ArrayList<Entry> entries = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();


        int sunday = 0, monday = 0, tuesday = 0, wednesday = 0, thursday = 0, friday = 0, saturday = 0;


        for (int i = 0; i < expenseEntityList.size(); i++) {

            if (expenseEntityList.get(i).getTransactionType().equals(AddExpenseFragment.CONSTANT_EXPENSE)
                    && expenseEntityList.get(i).getDate().after(dayAfter)) {

                calendar.setTime(expenseEntityList.get(i).getDate());
                int dayNumber = calendar.get(Calendar.DAY_OF_WEEK) - 1;
                String day = days[dayNumber];


                switch (day) {
                    case "SUNDAY":
                        sunday += expenseEntityList.get(i).getAmount();
                        break;
                    case "MONDAY":
                        monday += expenseEntityList.get(i).getAmount();
                        break;
                    case "TUESDAY":
                        tuesday += expenseEntityList.get(i).getAmount();
                        break;
                    case "WEDNESDAY":
                        wednesday += expenseEntityList.get(i).getAmount();
                        break;
                    case "THURSDAY":
                        thursday += expenseEntityList.get(i).getAmount();
                        break;
                    case "FRIDAY":
                        friday += expenseEntityList.get(i).getAmount();
                        break;
                    case "SATURDAY":
                        saturday += expenseEntityList.get(i).getAmount();
                        break;
                    default:
                        break;
                }

            }

            if (tuesday == 0) tuesday = monday;
            if (wednesday == 0) wednesday = tuesday;
            if (thursday == 0) thursday = wednesday;
            if (friday == 0) friday = thursday;
            if (saturday == 0) saturday = friday;
            if (sunday == 0) sunday = saturday;
//
//            Log.d(TAG, "incomeValuesValues: day after: " + dayAfter);
//            Log.d(TAG, "incomeValues: daysvalues mon " + monday);
//            Log.d(TAG, "incomeValues: daysvalues tue " + tuesday);
//            Log.d(TAG, "incomeValues: daysvalues wed " + wednesday);
//            Log.d(TAG, "incomeValues: daysvalues thurs " + thursday);
//            Log.d(TAG, "incomeValues: daysvalues fri " + friday);
//            Log.d(TAG, "incomeValues: daysvalues sat " + saturday);
//            Log.d(TAG, "incomeValues: daysvalues sun " + sunday);


        }


        entries.add(new Entry(1, monday));
        entries.add(new Entry(2, tuesday));
        entries.add(new Entry(3, wednesday));
        entries.add(new Entry(4, thursday));
        entries.add(new Entry(5, friday));
        entries.add(new Entry(6, saturday));
        entries.add(new Entry(7, sunday));
        return entries;
    }


}