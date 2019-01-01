package com.milk.open.openmove21.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.milk.open.openmove21.R;
import com.milk.open.openmove21.fragment.*;
import com.milk.open.openmove21.slidemenu.SlideMenuContainerLayout;

public class ActivityMain extends ActivityBase implements FragmentMenu.OnFragmentInteractionListener {

    /**
     * 滑动容器
     */
    private SlideMenuContainerLayout mSlideContainer;

    private FragmentMenu mMenuFragment;

    private Fragment currentFragment;

    private FragmentContent01SearchTickets fragment01;

    private FragmentContent02 fragment02;

    private FragmentContent03MyTickets fragment03;

    private FragmentContent04TicketInfo fragment04;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setupView()
        setContentView(mSlideContainer);
    }

    @SuppressWarnings("deprecation")
    @Override
    protected void setupView() {
        mSlideContainer = new SlideMenuContainerLayout(mContext);

        //configMenu();
        mMenuFragment = FragmentMenu.getInstance();
        mMenuFragment.setOnFragmentInteractionListener(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.slidemenu_menu_frame, mMenuFragment).commit();
        mSlideContainer.setMenu(R.layout.slidemenu_menu);
        int width = Math.min(UtilDataConstants.sWidth, UtilDataConstants.sHeight);
        mSlideContainer.setMenuWidth(63 * width / 100);

        mSlideContainer.setContent(R.layout.slidemenu_content);

        onFragmentInteraction(0);
    }

    private FragmentTransaction switchContentFragment(Fragment targetFragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if (targetFragment.isAdded()) {
            transaction.hide(currentFragment).show(targetFragment);
        }else {
            if (null != currentFragment) {
                transaction.hide(currentFragment);
            }
            transaction.add(R.id.slidemenu_content_frame, targetFragment, targetFragment.getClass().getName());
        }

        currentFragment = targetFragment;
        return transaction;
    }

    public void onFragmentInteraction(int seq){
        if(0 == seq){
            if(null == fragment01){
                fragment01 = FragmentContent01SearchTickets.getInstance();
            }
            switchContentFragment(fragment01).commit();
        } else if(1 == seq){
            if(null == fragment02){
                fragment02 = FragmentContent02.getInstance();
                fragment02.setOnFragmentInteractionListener(this);
            }
            switchContentFragment(fragment02).commit();
        } else if(4 == seq){
            if(null == fragment03){
                fragment03 = FragmentContent03MyTickets.getInstance();
                fragment03.setOnFragmentInteractionListener(this);
            }
            switchContentFragment(fragment03).commit();
        } else if(3 == seq){
            if(null == fragment04){
                fragment04 = FragmentContent04TicketInfo.getInstance();
                fragment04.setOnFragmentInteractionListener(this);
            }
            switchContentFragment(fragment04).commit();
        }
        mSlideContainer.hide_menu();
    }

    public void onFragmentInteraction(String msg) {
        mSlideContainer.hide_menu();
    }

    protected void initialized() {

    }

    protected int getLayoutId() {
        return 0;
    }
//
//    public void onClick() {
//        mSlideContainer.slideToRight();
//    }
//
//    public void onClick(View view) {
//        mSlideContainer.show(view);
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // 获取解析结果
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                toast("取消扫描");
            } else {
                toast("扫描内容:" + result.getContents());
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
