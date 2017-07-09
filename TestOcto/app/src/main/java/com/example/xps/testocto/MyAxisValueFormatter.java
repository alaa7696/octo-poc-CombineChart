package com.example.xps.testocto;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.text.DecimalFormat;

/**
 * Created by xps on 07/07/2017.
 */
public class MyAxisValueFormatter implements IAxisValueFormatter
{
    private String[] tab;

    private DecimalFormat mFormat;

    public MyAxisValueFormatter(String[] tab)
    {
    this.tab=tab;
    }



    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        // "value" represents the position of the label on the axis (x or y)
        return tab[(int) value];
    }
}

