package com.example.xps.testocto;

import android.graphics.Color;
import android.util.Log;

import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xps on 07/07/2017.
 */
public class Bar  extends CombinedData {
    ArrayList<Integer> arr=new ArrayList<Integer>();

    public Bar(ArrayList<Integer> arr){
    this.arr=arr;
    }

    public CombinedData init() {
        BarData barData= generatePNdata();          // remplissage de barData suivant arrylist du constructeur
        this.setData(barData);
        BarDataSet x = (BarDataSet) barData.getDataSetByIndex(0);
        List<BarEntry> y = x.getValues();
        this.setData(generateLineData(y));

        return this;
    }
    private BarData generatePNdata() {                              // remplissage du bar chart
        ArrayList<BarEntry> x = new ArrayList<>();
        int green = Color.rgb(110, 190, 102);
        int red = Color.rgb(211, 74, 88);


        for (int index = 0; index < arr.size(); index+=2) {
            x.add(new BarEntry(index/2,new float[]{arr.get(index),-arr.get(index+1)} ));
        }

        BarDataSet set = new BarDataSet(x,"");
        set.setColors(new int[]{green,red});
        set.setStackLabels(new String[]{"Revenu","Depense"});
        BarData data = new BarData(set);
        data.setBarWidth(0.9f);
        data.setDrawValues(false);
        return data;

    }


    private LineData generateLineData(List<BarEntry> y) {

        LineData d = new LineData();

        ArrayList<Entry> entries = new ArrayList<Entry>();
        for (int index = 0; index < y.size(); index++) {
            float[] t = y.get(index).getYVals();
            entries.add(new Entry(index, (t[0] + t[1]) / 2));
        }
        LineDataSet set = new LineDataSet(entries, "Line DataSet");
        set.setLabel("Balance");
        set.setColor(Color.rgb(249, 175, 47));
        set.setLineWidth(2.5f);
        set.setCircleColor(Color.rgb(240, 238, 70));
        set.setCircleRadius(5f);
        set.setFillColor(Color.rgb(240, 238, 70));
        set.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        set.setDrawValues(true);
        set.setValueTextSize(10f);
        set.setValueTextColor(Color.rgb(240, 238, 70));
        set.setAxisDependency(YAxis.AxisDependency.LEFT);
        set.setDrawValues(false);
        d.addDataSet(set);

        return d;
    }

    protected float getRandom(float range, float startsfrom) {
        return (float) (Math.random() * range) + startsfrom;
    }

}
