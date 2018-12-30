package com.milk.open.openmove21.activity;

import android.os.Bundle;
import com.milk.open.openmove21.R;
import com.milk.open.openmove21.SlideMenu.SlideMenuContainerLayout;
import com.milk.open.openmove21.fragment.FragmentContent01SearchTickets;
import com.milk.open.openmove21.fragment.FragmentMenu;

public class ActivityMain extends ActivityBase implements FragmentMenu.OnFragmentInteractionListener {

    /**
     * 滑动容器
     */
    private SlideMenuContainerLayout mSlideContainer;


    private FragmentMenu mMenuFragment;

    private FragmentContent01SearchTickets mContentFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setupView();

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
        mSlideContainer.hide_menu();

        //configContent();
        mContentFragment = FragmentContent01SearchTickets.getInstance();
        getSupportFragmentManager().beginTransaction().replace(R.id.slidemenu_content_frame, mContentFragment).commit();
        mSlideContainer.setContent(R.layout.slidemenu_content);
    }

//    public void toggle() {
//        mSlideContainer.toggle();
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()){
//            case android.R.id.home:
//                toggle();
//                break;
//        }
//        return super.onOptionsItemSelected(item);
//    }

    public void onFragmentInteraction(String msg) {
        mSlideContainer.onFragmentInteraction(msg);
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
