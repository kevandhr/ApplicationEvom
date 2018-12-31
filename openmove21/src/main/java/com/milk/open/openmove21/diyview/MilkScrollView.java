package com.milk.open.openmove21.diyview;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.ScrollView;

public class MilkScrollView extends ScrollView {
    public MilkScrollView(Context context) {
        super(context);
    }

    public MilkScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MilkScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /*解决嵌套listview listview抢占焦点 显示的问题*/
    protected int computeScrollDeltaToGetChildRectOnScreen(Rect rect) {
        return 0;
    }
}
