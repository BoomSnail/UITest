package com.xiaoshihua.thinkpad.uitest;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Created by ThinkPad on 2016/9/7.
 */
public class DemoView extends View {

    private String mTitleText;//文字
    private int mTitleTextColor;//文字颜色
    private int mTitleTextSize;//文字大小

    private Rect mBound;//绘制范围
    private Paint mPaint;

    public DemoView(Context context) {
        this(context, null, 0);
    }

    public DemoView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DemoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.DemoView, defStyleAttr, 0);
        try {

            //获取自定义样式
            int n = typedArray.getIndexCount();
            for (int i = 0; i < n; i++) {
                int attr = typedArray.getIndex(i);
                switch (attr) {
                    case R.styleable.DemoView_titleText:
                        mTitleText = typedArray.getString(attr);
                        break;
                    case R.styleable.DemoView_titleTExtColor:
                        mTitleTextColor = typedArray.getColor(attr, Color.BLACK);
                        break;
                    case R.styleable.DemoView_titleTextSize:
                        mTitleTextSize = typedArray.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                                16, getResources().getDisplayMetrics()));
                        break;
                }
            }
            //绘制文本宽和高
            mPaint = new Paint();
            mPaint.setTextSize(mTitleTextSize);
            mPaint.setAntiAlias(true);
            mPaint.setColor(mTitleTextColor);//画笔颜色即为文字的颜色
            mBound = new Rect();
            mPaint.getTextBounds(mTitleText, 0, mTitleText.length(), mBound);


            this.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    mTitleText = randomText();
                    postInvalidate();
                }
            });
//            typedArray.recycle();
        }finally {
            typedArray.recycle();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width;
        int height;
        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else {
            mPaint.setTextSize(mTitleTextSize);
            mPaint.getTextBounds(mTitleText, 0, mTitleText.length(), mBound);

            float textWidth = mBound.width();
            int desired = (int) (getPaddingLeft() + getPaddingRight() + textWidth);
            width = desired;
        }
        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else {
            mPaint.setTextSize(mTitleTextSize);
            mPaint.getTextBounds(mTitleText, 0, mTitleText.length(), mBound);
            float textHeight = mBound.height();
            int desired = (int) (getPaddingBottom() + getPaddingTop() + textHeight);
            height = desired;
        }
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mPaint.setColor(Color.YELLOW);
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint);

        mPaint.setColor(mTitleTextColor);
        canvas.drawText(mTitleText, getWidth() / 2 - mBound.width() / 2, getHeight() / 2 + mBound.height() / 2, mPaint);


    }


    private String randomText() {
        Random random = new Random();
        Set<Integer> set = new HashSet<>();
        while (set.size() < 4) {
            int randomInt = random.nextInt(10);
            set.add(randomInt);
        }

        StringBuffer stringBuffer = new StringBuffer();
        for (Integer in : set) {
            stringBuffer.append("" + in);
        }
        return stringBuffer.toString();
    }
}
