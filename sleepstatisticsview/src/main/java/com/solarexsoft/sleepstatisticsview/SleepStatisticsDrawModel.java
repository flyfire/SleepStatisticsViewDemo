package com.solarexsoft.sleepstatisticsview;

import java.util.List;

/**
 * <pre>
 *    Author: houruhou
 *    CreatAt: 10:49/2019/3/21
 *    Desc:
 * </pre>
 */

public class SleepStatisticsDrawModel {
    String start;
    String end;
    List<SleepDurationModel> models;

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public List<SleepDurationModel> getModels() {
        return models;
    }

    public void setModels(List<SleepDurationModel> models) {
        this.models = models;
    }
}
