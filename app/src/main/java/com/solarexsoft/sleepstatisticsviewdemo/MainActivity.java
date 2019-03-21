package com.solarexsoft.sleepstatisticsviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.solarexsoft.sleepstatisticsview.SleepDurationModel;
import com.solarexsoft.sleepstatisticsview.SleepStatisticsDrawModel;
import com.solarexsoft.sleepstatisticsview.SleepStatisticsView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    SleepStatisticsView ssv1;
    SleepStatisticsView ssv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ssv1 = findViewById(R.id.ssv1);
        ssv2 = findViewById(R.id.ssv2);
        ssv1.setOnClickListener(new SleepStatisticsView.OnClickListener() {
            @Override
            public void onClick(SleepDurationModel model) {
                Log.d("solarex", "ssv1 touched start = " + model.getStarttime() + ",end = " + model.getEndtime() + ",duration = " + model.getDuration());
            }
        });

        ssv2.setOnClickListener(new SleepStatisticsView.OnClickListener() {
            @Override
            public void onClick(SleepDurationModel model) {
                Log.d("solarex", "ssv2 touched start = " + model.getStarttime() + ",end = " + model.getEndtime() + ",duration = " + model.getDuration());
            }
        });

        long y23pm = 1553007600;
        long t10am = 1553047200;
        long t12am = 1553054400;
        long t13pm = 1553058000;
        long total = t13pm - y23pm;

        SleepStatisticsDrawModel tmp2 = new SleepStatisticsDrawModel();
        tmp2.setStart("23:00");
        tmp2.setEnd("13:00");
        List<SleepDurationModel> durations2 = new ArrayList<>();
        tmp2.setModels(durations2);
        SleepDurationModel duration4 = new SleepDurationModel();
        duration4.setStarttime(y23pm);
        duration4.setEndtime(t13pm);
        duration4.setDuration(total);
        duration4.setSleep(true);
        duration4.setPercent(1.0f);
        durations2.add(duration4);
        ssv2.setModel(tmp2);

        SleepStatisticsDrawModel tmp1 = new SleepStatisticsDrawModel();
        tmp1.setStart("23:00");
        tmp1.setEnd("13:00");
        List<SleepDurationModel> durations1 = new ArrayList<>();
        tmp1.setModels(durations1);
        SleepDurationModel duration1 = new SleepDurationModel();
        duration1.setStarttime(y23pm);
        duration1.setEndtime(t10am);
        duration1.setDuration(t10am - y23pm);
        duration1.setPercent(duration1.getDuration() * 1.0f / total);
        duration1.setSleep(true);
        durations1.add(duration1);
        SleepDurationModel duration2 = new SleepDurationModel();
        duration2.setStarttime(t10am);
        duration2.setEndtime(t12am);
        duration2.setDuration(t12am - t10am);
        duration2.setPercent(duration2.getDuration() * 1.0f / total);
        duration2.setSleep(false);
        durations1.add(duration2);
        SleepDurationModel duration3 = new SleepDurationModel();
        duration3.setStarttime(t12am);
        duration3.setEndtime(t13pm);
        duration3.setDuration(t13pm - t12am);
        duration3.setPercent(duration3.getDuration() * 1.0f / total);
        duration3.setSleep(true);
        durations1.add(duration3);

        ssv1.setModel(tmp1);
    }
}
