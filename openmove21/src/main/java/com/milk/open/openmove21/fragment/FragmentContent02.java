package com.milk.open.openmove21.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.milk.open.openmove21.R;
import com.milk.open.openmove21.SlideMenu.OnMenuClickListener;
import com.milk.open.openmove21.diyview.TopItemNavbar;

public class FragmentContent02 extends FragmentBase {

    private int R_id_layout = R.layout.fragment_content_02;
    private int R_id_topbar = R.id.topbar_fragment_content_02;

    private TopItemNavbar topItemNavbar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("curChoice", 1);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R_id_layout, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        topItemNavbar = (TopItemNavbar) view.findViewById(R_id_topbar);
        topItemNavbar.setTitle("fragment content 01");
        topItemNavbar.setOnMenuClickListener((OnMenuClickListener)view.getParent().getParent().getParent());

    }

    private static FragmentContent02 fragment;
    public static FragmentContent02 getInstance(){
        if (null == fragment){
            fragment = new FragmentContent02();
        }
        return fragment;
    }
}
