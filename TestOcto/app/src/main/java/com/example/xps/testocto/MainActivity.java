package com.example.xps.testocto;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.RectF;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



    public class MainActivity extends AppCompatActivity {
        private final int itemcount_mois = 12;
        private final int itemcount_semaine = 7;
        private final int itemcount_3mois = 3;
        private Bar combinedData;
        String[] tab_semaine={"lundi","mardi","mercredi","jeudi","vendredi","samedi","dimanche"};
        String[] tab_3mois={"mois1","mois2","mois3"};
        String[] tab_mois=new String[30];
        ArrayList<Integer> arr_mois=new ArrayList<Integer>();

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.content_main);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
            setTitle("POC API CHARTS");

            CombinedChart chart = (CombinedChart) findViewById(R.id.chart);

            for(int i=0;i<30;i++)                   // axe des x :)
                tab_mois[i]="jour"+i;

            chart.setDrawGridBackground(true);
            chart.setDrawOrder(new CombinedChart.DrawOrder[]{CombinedChart.DrawOrder.BAR, CombinedChart.DrawOrder.LINE});
            chart.animateXY(2500, 2500);
            chart.getXAxis().setAxisMinimum(-0.5f);
            chart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);

            fill(arr_mois, 60);                   // remplissage des donnees

            chart.setData(new Bar(arr_mois).init());
            chart.getXAxis().setValueFormatter(new MyAxisValueFormatter(tab_mois));

            IAxisValueFormatter axisValueFormatter=new IAxisValueFormatter(){      // customizing y axis
                private DecimalFormat mFormat;
                @Override
                public String getFormattedValue(float value, AxisBase axis) {
                    if(value>-10 && value<10)
                    return new DecimalFormat("###,###,##0.0").format(value) + " Dh";
                    return new DecimalFormat("###,###,##0.0").format(value) + " Dhs";
                }
                public int getDecimalDigits() { return 1; }
            };

            chart.getAxisLeft().setValueFormatter(axisValueFormatter);
            chart.getAxisRight().setValueFormatter(axisValueFormatter);
            chart.getXAxis().setAxisMaximum(chart.getXChartMax() + 0.5f);

            chart.setDrawValueAboveBar(false);
            chart.setVisibleXRangeMaximum(7);

            Legend l = chart.getLegend();                                   // configuring legend
            l.setWordWrapEnabled(true);
            l.setDrawInside(false);
            l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
            l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
            l.setOrientation(Legend.LegendOrientation.HORIZONTAL);

            chart.getData().setHighlightEnabled(false);
            chart.invalidate();
        }

        protected void fill(ArrayList<Integer> arr,int x)
        {
            for(int i=0;i<x;i++)
                arr.add((int)getRandom(40f,1f));
        }
        protected float getRandom(float range, float startsfrom) {
            return (float) (Math.random() * range) + startsfrom;
        }

    }


