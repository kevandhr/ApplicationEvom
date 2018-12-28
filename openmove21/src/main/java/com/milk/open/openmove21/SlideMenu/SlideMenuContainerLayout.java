package com.milk.open.openmove21.SlideMenu;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import com.milk.open.openmove21.R;

public class SlideMenuContainerLayout extends RelativeLayout {

    private SlideMenuMenuView mMenuView;
    private SlideMenuContentView mContentView;
    /**
     * Constant value for use with setTouchModeAbove(). Allows the SlidingMenu to be opened with a swipe
     * gesture on the screen's margin
     */
    public static final int TOUCHMODE_MARGIN = 0;

    /**
     * Constant value for use with setTouchModeAbove(). Allows the SlidingMenu to be opened with a swipe
     * gesture anywhere on the screen
     */
    public static final int TOUCHMODE_FULLSCREEN = 1;

    /**
     * Constant value for use with setTouchModeAbove(). Denies the SlidingMenu to be opened with a swipe
     * gesture
     */
    public static final int TOUCHMODE_NONE = 2;


    public SlideMenuContainerLayout(Context context) {
        this(context, null);
    }

    public SlideMenuContainerLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public SlideMenuContainerLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        LayoutParams behindParams = new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT);
        mMenuView = new SlideMenuMenuView(context);
        addView(mMenuView, behindParams);

        LayoutParams aboveParams = new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT);
        mContentView = new SlideMenuContentView(context);
        addView(mContentView, aboveParams);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.XMenu);
        try {
            Drawable leftShadowDrawable = a
                    .getDrawable(R.styleable.XMenu_LeftShadowDrawable);
            if (null == leftShadowDrawable) {
                leftShadowDrawable = new GradientDrawable(
                        GradientDrawable.Orientation.LEFT_RIGHT, new int[]{Color.TRANSPARENT,
                        Color.argb(99, 0, 0, 0)});
            }
            mContentView.setLeftShadowDrawable(leftShadowDrawable);
            int touchModeAbove = a.getInt(R.styleable.XMenu_touchModeAbove, TOUCHMODE_MARGIN);
            setTouchModeAbove(touchModeAbove);
//            int edgeWidth = a.getInt(R.styleable.XMenu_edgeWidth, DisplayUtil.dip2px(getContext(), 10));
//            setEdgeWidth(edgeWidth);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            a.recycle();
        }
        int mMarginThreshold = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                20, getResources().getDisplayMetrics());
    }



    public void setMenu(int layoutId) {
        setMenu(LayoutInflater.from(getContext()).inflate(layoutId, null));
    }

    public void setMenu(View v) {
        mMenuView.setView(v);
    }

    public void setContent(int layoutId) {
        setContent(LayoutInflater.from(getContext()).inflate(layoutId, null));
    }

    public void setContent(View v) {
        mContentView.setView(v);
    }

    public void setMenuWidth(int menuWidth) {
        mContentView.setMenuWidth(menuWidth);
        mMenuView.setMenuWidth(menuWidth);
    }

    public void toggle() {
        mContentView.toggle();
    }

    public boolean isMenuShowing() {
        return mContentView.isMenuShowing();
    }

    public void showContent() {
        mContentView.showContent();
    }

    public void setLeftShadowWidth(int width) {
        mContentView.setLeftShadowWidth(width);
    }

    /**
     * Controls whether the SlidingMenu can be opened with a swipe gesture.
     * Options are {@link #TOUCHMODE_MARGIN TOUCHMODE_MARGIN}, {@link #TOUCHMODE_FULLSCREEN TOUCHMODE_FULLSCREEN},
     * or {@link #TOUCHMODE_NONE TOUCHMODE_NONE}
     *
     * @param i the new touch mode
     */
    public void setTouchModeAbove(int i) {
        if (i != SlideMenuContainerLayout.TOUCHMODE_FULLSCREEN && i != SlideMenuContainerLayout.TOUCHMODE_MARGIN
                && i != SlideMenuContainerLayout.TOUCHMODE_NONE) {
            throw new IllegalStateException("TouchMode must be set to either" +
                    "TOUCHMODE_FULLSCREEN or TOUCHMODE_MARGIN or TOUCHMODE_NONE.");
        }
        mContentView.setTouchMode(i);
    }

    /**
     * 当touchModeAbove为TOUCHMODE_MARGIN时,设置屏幕边缘的宽度，建议使用系统的配置
     */
    @Deprecated
    public void setEdgeWidth(int edgeWidth) {
        mContentView.setEdgeWidth(edgeWidth);
    }
}