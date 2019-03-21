package com.solarexsoft.sleepstatisticsview;

/**
 * <pre>
 *    Author: houruhou
 *    CreatAt: 10:47/2019/3/21
 *    Desc:
 * </pre>
 */

public class SleepDurationModel {
    long starttime;
    long endtime;
    long duration;
    float percent;
    boolean isSleep;

    public long getStarttime() {
        return starttime;
    }

    public void setStarttime(long starttime) {
        this.starttime = starttime;
    }

    public long getEndtime() {
        return endtime;
    }

    public void setEndtime(long endtime) {
        this.endtime = endtime;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public float getPercent() {
        return percent;
    }

    public void setPercent(float percent) {
        this.percent = percent;
    }

    public boolean isSleep() {
        return isSleep;
    }

    public void setSleep(boolean sleep) {
        isSleep = sleep;
    }
}
