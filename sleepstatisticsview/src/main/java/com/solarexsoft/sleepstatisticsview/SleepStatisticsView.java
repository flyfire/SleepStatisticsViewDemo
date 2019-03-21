package com.solarexsoft.sleepstatisticsview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 *    Author: houruhou
 *    CreatAt: 10:28/2019/3/21
 *    Desc:
 * </pre>
 */

public class SleepStatisticsView extends View {

    private int withDataColor = Color.parseColor("#1AD9CA");
    private int noDataColor = Color.parseColor("#F0F1F2");
    private int textColor = Color.parseColor("#808184");
    private Paint mPaint;
    private Paint mTextPaint;
    private static final int DEFAULT_HEIGHT = (int) ViewUtils.dp2px(50);
    private static final int DEFAULT_RECT_HEIGHT = (int) ViewUtils.dp2px(25);
    private static final int DEFAULT_TEXT_TOP = (int) ViewUtils.dp2px(35);
    private static final float RADIUS = ViewUtils.dp2px(2);
    private int mWidth, mHeight;
    private SleepStatisticsDrawModel mModel;
    ArrayList<SleepStatisticsLocationModel> mLocations;
    List<SleepDurationModel> mDurations;
    SleepDurationModel mDuration4CalRect;
    OnClickListener mListener;
    int mDurationSize;
    float startX;
    float endX;
    float rectWidth;
    int rectColor;
    RectF rectF;
    float touchX;
    int touchIndex;

    {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
        mLocations = new ArrayList<>();
        rectF = new RectF();

        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(textColor);
        mTextPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mTextPaint.setTextSize(ViewUtils.dp2px(13));
    }

    public SleepStatisticsView(Context context) {
        super(context);
    }

    public SleepStatisticsView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SleepStatisticsView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int height = ViewUtils.measure(heightMeasureSpec, DEFAULT_HEIGHT);
        setMeasuredDimension(widthMeasureSpec, height);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        mWidth = w - getPaddingLeft() - getPaddingRight();
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mModel == null) {
            return;
        }
        mLocations.clear();
        startX = 0;
        mDurations = mModel.getModels();
        mDurationSize = mDurations.size();
        for (int i = 0; i < mDurationSize; i++) {
            mDuration4CalRect = mDurations.get(i);
            rectWidth = mWidth * mDuration4CalRect.getPercent();
            endX = startX + rectWidth;
            rectColor = mDuration4CalRect.isSleep() ? withDataColor : noDataColor;
            drawRect(canvas, startX, endX, rectColor, mDuration4CalRect.isSleep());
            SleepStatisticsLocationModel location = new SleepStatisticsLocationModel();
            location.setStartX(startX);
            location.setEndX(endX);
            mLocations.add(location);
            startX = endX;
        }
        drawBottomText(canvas, mModel.getStart(), mModel.getEnd());
    }

    private void drawBottomText(Canvas canvas, String start, String end) {
        Log.d("solarex", "paint ascent = " + mTextPaint.ascent());
        float baseline = DEFAULT_TEXT_TOP - mTextPaint.ascent();
        mTextPaint.setTextAlign(Paint.Align.LEFT);
        canvas.drawText(start, ViewUtils.dp2px(1), baseline, mTextPaint);
        mTextPaint.setTextAlign(Paint.Align.RIGHT);
        canvas.drawText(end, getPaddingLeft() + mWidth - ViewUtils.dp2px(1), baseline, mTextPaint);
    }

    private void drawRect(Canvas canvas, float startX, float endX, int rectColor, boolean issleep) {
        rectF.set(startX, 0, endX, DEFAULT_RECT_HEIGHT);
        mPaint.setColor(rectColor);
        if (issleep) {
            canvas.drawRoundRect(rectF, RADIUS, RADIUS, mPaint);
        } else {
            canvas.drawRect(rectF, mPaint);
        }
    }

    public void setModel(SleepStatisticsDrawModel model) {
        mModel = model;
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                touchX = event.getX();
                touchIndex = -1;
                break;
            case MotionEvent.ACTION_UP:
                for (int i = 0; i < mLocations.size(); i++) {
                    SleepStatisticsLocationModel model = mLocations.get(i);
                    if (model.getStartX() < touchX && touchX < model.getEndX()) {
                        touchIndex = i;
                        break;
                    }
                }
                if (touchIndex != -1) {
                    if (mModel != null) {
                        SleepDurationModel sleepDurationModel = mModel.getModels().get(touchIndex);
                        if (sleepDurationModel.isSleep()) {
                            if (mListener != null) {
                                mListener.onClick(sleepDurationModel);
                            }
                        }
                    }
                }
                break;
        }
        return true;
    }

    public void setOnClickListener(OnClickListener listener) {
        mListener = listener;
    }
    public interface OnClickListener {
        void onClick(SleepDurationModel model);
    }
}
