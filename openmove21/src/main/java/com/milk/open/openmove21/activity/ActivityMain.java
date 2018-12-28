package com.milk.open.openmove21.activity;

import android.os.Bundle;
import android.view.MenuItem;
import com.milk.open.openmove21.*;
import com.milk.open.openmove21.SlideMenu.SlideMenuContainerLayout;
import com.milk.open.openmove21.fragment.UtilConstants;
import com.milk.open.openmove21.fragment.FragmentContent;
import com.milk.open.openmove21.fragment.FragmentMenu;

public class ActivityMain extends ActivityBase implements FragmentMenu.OnFragmentInteractionListener {

    private SlideMenuContainerLayout xMenu;
    private FragmentMenu mMenuFragment;
    private FragmentContent mContentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        xMenu = new SlideMenuContainerLayout(this);
        setContentView(xMenu);
        configMenu();
        configContent();
    }

    private void configMenu() {
        mMenuFragment = new FragmentMenu();
        getSupportFragmentManager().beginTransaction().replace(R.id.slidemenu_menu_frame, mMenuFragment).commit();
        xMenu.setMenu(R.layout.slidemenu_menu_container);
        int width = Math.min(UtilConstants.sWidth, UtilConstants.sHeight);
        xMenu.setMenuWidth(3 * width / 5);
    }

    private void configContent() {
        mContentFragment = new FragmentContent();
        getSupportFragmentManager().beginTransaction().replace(R.id.slidemenu_content_frame, mContentFragment).commit();
        xMenu.setContent(R.layout.slidemenu_content);
    }

    @Override
    public void onFragmentInteraction(String id) {

    }

    public void toggle() {
        xMenu.toggle();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                toggle();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
