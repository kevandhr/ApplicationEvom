package com.milk.open.openmove21.slidemenu;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.*;
import android.widget.RelativeLayout;
import com.milk.open.openmove21.R;
import com.milk.open.openmove21.fragment.FragmentMenu;


/**
 * 功能描述：手指在屏幕上左右滑动时，该类的实例负责让其子View根据用户的手势左右偏移（滚动）
 *
 */
public class SlideMenuContainerLayout extends RelativeLayout implements OnMenuClickListener {

    private SlideMenuMenuView mMenuView;
    private SlideMenuContentView mContentView;
    private int menuWidth = 400;
    public static final int MESSAGE_TO_FRONT_MENU = 18;
    public static final int MESSAGE_TO_HIDE_MENU = 19;
    public static final int MESSAGE_TO_FRONT_CONTENT = 11;
    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == MESSAGE_TO_FRONT_MENU) {
                show_menu();
            } else if (msg.what == MESSAGE_TO_HIDE_MENU) {
                hide_menu();
            } else if (msg.what == MESSAGE_TO_FRONT_CONTENT) {
                show_content();
            }
        }
    };

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

        LayoutParams behindParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        mMenuView = new SlideMenuMenuView(context);
        mMenuView.setParentLayout(this);
        addView(mMenuView, behindParams);

        LayoutParams aboveParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        mContentView = new SlideMenuContentView(context);
        addView(mContentView, aboveParams);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.XMenu);
        try {
            Drawable rightShadowDrawable = typedArray.getDrawable(R.styleable.XMenu_RightShadowDrawable);
            if (null == rightShadowDrawable) {
                rightShadowDrawable = new GradientDrawable(
                        GradientDrawable.Orientation.RIGHT_LEFT,
                        new int[]{Color.TRANSPARENT, Color.argb(99, 0, 0, 0)});
            }
            mMenuView.setRightShadowDrawable(rightShadowDrawable);
//            int touchModeAbove = typedArray.getInt(R.styleable.XMenu_touchModeAbove, TOUCHMODE_MARGIN);
//            setTouchModeAbove(touchModeAbove);
//            int edgeWidth = typedArray.getInt(R.styleable.XMenu_edgeWidth, DisplayUtil.dip2px(getContext(), 10));
//            setEdgeWidth(edgeWidth);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            typedArray.recycle();
        }

    }

    public void show_menu(){
        bringChildToFront(getChildAt(0));
        mMenuView.scrollToShow();
    }

    public void hide_menu() {
        mMenuView.scrollToHide();
    }

    public void show_content(){
        View view=null;
        for(int i=0;i<getChildCount();i++){
            view = getChildAt(i);
            if(SlideMenuContentView.class == view.getClass()){
                break;
            }
        }
        bringChildToFront(view);
    }

//    /**
//     * Controls whether the SlidingMenu can be opened with a swipe gesture.
//     * Options are {@link #TOUCHMODE_MARGIN TOUCHMODE_MARGIN}, {@link #TOUCHMODE_FULLSCREEN TOUCHMODE_FULLSCREEN},
//     * or {@link #TOUCHMODE_NONE TOUCHMODE_NONE}
//     *
//     * @param i the new touch mode
//     */
//    public void setTouchModeAbove(int i) {
//        if (i != SlideMenuContainerLayout.TOUCHMODE_FULLSCREEN && i != SlideMenuContainerLayout.TOUCHMODE_MARGIN
//                && i != SlideMenuContainerLayout.TOUCHMODE_NONE) {
//            throw new IllegalStateException("TouchMode must be set to either" +
//                    "TOUCHMODE_FULLSCREEN or TOUCHMODE_MARGIN or TOUCHMODE_NONE.");
//        }
//        mContentView.setTouchMode(i);
//    }

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
        this.menuWidth=menuWidth;
        mMenuView.setMenuWidth(menuWidth);
    }

    public void onClick(View view){

    }

    public void onClick(){
        handler.sendEmptyMessage(MESSAGE_TO_FRONT_MENU);
    }

//    @Override
//    public void onFragmentInteraction(String msg) {
//        handler.sendEmptyMessage(MESSAGE_TO_HIDE_MENU);
//    }

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
}