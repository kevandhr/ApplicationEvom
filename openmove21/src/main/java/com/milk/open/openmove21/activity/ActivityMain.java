package com.milk.open.openmove21.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import com.milk.open.openmove21.R;
import com.milk.open.openmove21.fragment.FragmentContent02;
import com.milk.open.openmove21.slidemenu.SlideMenuContainerLayout;
import com.milk.open.openmove21.fragment.FragmentContent01SearchTickets;
import com.milk.open.openmove21.fragment.FragmentMenu;

public class ActivityMain extends ActivityBase implements FragmentMenu.OnFragmentInteractionListener {

    /**
     * 滑动容器
     */
    private SlideMenuContainerLayout mSlideContainer;

    private FragmentMenu mMenuFragment;

    private Fragment currentFragment;

    private FragmentContent01SearchTickets mContent01Fragment;

    private FragmentContent02 mContent02Fragment;

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
//        mSlideContainer.hide_menu();

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
            if(null == mContent01Fragment){
                mContent01Fragment = FragmentContent01SearchTickets.getInstance();
            }
            switchContentFragment(mContent01Fragment).commit();
        } else if(1 == seq){
            if(null == mContent02Fragment){
                mContent02Fragment = FragmentContent02.getInstance();
                mContent02Fragment.setOnFragmentInteractionListener(this);
            }
            switchContentFragment(mContent02Fragment).commit();
        } else if(2 == seq){

        } else if(3 == seq){

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
}
