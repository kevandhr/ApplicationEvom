package com.milk.open.openmove21.SlideMenu;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.*;
import android.widget.Scroller;
import com.milk.open.openmove21.Util.UtilLog;

import java.util.ArrayList;
import java.util.List;

public class SlideMenuMenuView extends ViewGroup {

    private int menuWidth = 400;
    private int duration = 500;
    private Scroller mScroller;

    private int oldScrollX = 0;
    private boolean isMenuShowing = true;
    private SlideMenuContainerLayout parentLayout;

    private int mShadowWidth = 20;
    private Drawable mShadowDrawable;

    private VelocityTracker mVelocityTracker;
    //速率采样间隔
    private static final int SNAP_VELOCITY = 1000;
    private final static int TOUCH_STATE_REST = 0;
    private final static int TOUCH_STATE_SCROLLING = 1;

    public SlideMenuMenuView(Context context) {
        super(context);
        init();
    }

    public SlideMenuMenuView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SlideMenuMenuView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        final int action = ev.getAction();
        final int x = (int) ev.getX();
        final int y = (int) ev.getY();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                if (mVelocityTracker == null) {
                    mVelocityTracker = VelocityTracker.obtain();
                }
                mVelocityTracker.addMovement(ev);
                if (!mScroller.isFinished()) {
//                    mScroller.abortAnimation();
                }
                break;
            case MotionEvent.ACTION_MOVE:

            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                if (x >= menuWidth) {
                    parentLayout.hide_menu();
                    parentLayout.show_content();
                }
                if (mVelocityTracker != null) {
                    mVelocityTracker.recycle();
                    mVelocityTracker = null;
                }

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

    public void scrollToShow() {
        if(!isMenuShowing) {
            oldScrollX = getScrollX();
            smoothScrollTo(-oldScrollX);
            isMenuShowing = true;
//            if (oldScrollX >= 0) {
////                UtilLog.i("scrollToShow scrollto="+-menuWidth);
//                smoothScrollTo(0);
//                isMenuShowing = true;
//            } else if (oldScrollX <= 0){
//                smoothScrollTo(0);
//                isMenuShowing = true;
//            }
        }
    }

    public void scrollToHide() {
        if (isMenuShowing) {
            oldScrollX = getScrollX();
            int dx=menuWidth-oldScrollX;
//            UtilLog.i("scrollToHide scrollto="+dx);
            smoothScrollTo(dx);
            isMenuShowing = false;
        }
    }

    private void smoothScrollTo(int dx) {
        int oldScrollX = getScrollX();
//        UtilLog.i("scroll from ="+oldScrollX+" to ="+dx+" duration="+duration);
        mScroller.startScroll(oldScrollX, getScrollY(), dx, getScrollY(), duration);
        invalidate();
    }

    @Override
    public void computeScroll() {
        if (!mScroller.isFinished()) {
            if (mScroller.computeScrollOffset()) {
                int oldX = getScrollX();
                int oldY = getScrollY();
                int x = mScroller.getCurrX();
                int y = mScroller.getCurrY();
                if (oldX != x || oldY != y) {
                    scrollTo(x, y);
                }
                // Keep on drawing until the animation has finished.
                postInvalidate();
            }
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = getDefaultSize(0, widthMeasureSpec);
        int height = getDefaultSize(0, heightMeasureSpec);
        setMeasuredDimension(width, height);
        final int menuWidthSpec = getChildMeasureSpec(widthMeasureSpec, 0,
                this.menuWidth);
        final int menuHeightSpec = getChildMeasureSpec(heightMeasureSpec, 0,
                height);
        final int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            if(child.getVisibility()!=GONE){
                child.measure(menuWidthSpec, menuHeightSpec);
            }
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        final int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            if(child.getVisibility()!=GONE){
                l = getPaddingLeft();
                t = getPaddingTop();
                r = l + child.getMeasuredWidth();
                b = l + child.getMeasuredHeight();
                child.layout(l,t,r,b);
            }
        }
    }

    private void init() {
        //默认情况下ViewGroup不会回调onDraw方法，但是为了绘制侧滑的阴影部分，
        //通过设置setWillNotDraw来让viewgroup回调onDraw
        setWillNotDraw(false);
        mScroller = new Scroller(getContext());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (null != mShadowDrawable) {
            int left = menuWidth;
            int right = left + mShadowWidth;
            int top = 0;
            int bottom = top + getHeight();
            mShadowDrawable.setBounds(left, top, right, bottom);
            mShadowDrawable.draw(canvas);
        }
    }
    public void setMenuWidth(int menuWidth) {
        this.menuWidth = menuWidth;
    }

    public void setView(View v) {
        if (getChildCount() > 0) {
            removeAllViews();
        }
        addView(v);
    }

    public void setRightShadowDrawable(Drawable drawable) {
        mShadowDrawable = drawable;
    }
    //设置阴影的宽度
    public void setLeftShadowWidth(int width) {
        mShadowWidth = width;
    }

    public void setParentLayout(SlideMenuContainerLayout parentLayout){
        this.parentLayout = parentLayout;
    }

    /**
     //	 * 设置选中的Item事件监听器
     //	 *
     //	 * @param seletedListener
     //	 */
	public void setOnSeletedListener(onSeletedListener seletedListener) {
//		mOnSeletedListener = seletedListener;
	}

	/**
	 * 选中的Item事件监听器
	 *
	 * @author android_ls
	 */
	public interface onSeletedListener {
		/**
		 * 当前选中的Item事件处理器
		 *
		 * @param groupPosition
		 *            所属组Id
		 * @param childPosition
		 *            在所属组内的位置
		 */
		public abstract void seletedChildView(int groupPosition,
                                              int childPosition);
	}
}
