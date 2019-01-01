package com.milk.open.openmove21.slidemenu;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.*;

public class SlideMenuContentView extends ViewGroup {

    private View mContentView;

    public SlideMenuContentView(Context context) {
        super(context);
        init();
    }

    public SlideMenuContentView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SlideMenuContentView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (mContentView.getVisibility() != GONE) {
            mContentView.measure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (mContentView.getVisibility() != GONE) {
            l = getPaddingLeft();
            t = getPaddingTop();
            r = l + mContentView.getMeasuredWidth();
            b = l + mContentView.getMeasuredHeight();
            mContentView.layout(l, t, r, b);
        }
    }

    private void init() {
        setWillNotDraw(false);
    }

    public void setView(View v) {
        if (getChildCount() > 0) {
            removeAllViews();
        }
        if (v.getParent() != null) {
            throw new RuntimeException(
                    "the view has parent,please detach this view first");
        }
        mContentView = v;
        addView(mContentView);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        final int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
//                UtilLog.i("ACTION_DOWN");
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
//                UtilLog.i("ACTION_DOWN");
                return true;
        }
        return false;
    }

}
