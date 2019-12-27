package com.yogi.financeapp.utils;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class ConvertDateToLocalDate {

    @RequiresApi(api = Build.VERSION_CODES.O)
    public LocalDate convertDateToLocatDate(Date date) {
        return date
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }
}
