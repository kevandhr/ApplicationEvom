package com.milk.open.openmove21.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.milk.open.openmove21.R;
import com.milk.open.openmove21.slidemenu.OnMenuClickListener;
import com.milk.open.openmove21.diyview.TopItemNavbar;

public class FragmentContent02SearchTrips extends FragmentBase {

    private int R_id_layout = R.layout.fragment_content_02;
    private int R_id_topbar = R.id.fcontent02_topitembar;

    private TopItemNavbar topItemNavbar;
    private ImageView iv_home;

    private FragmentMenu.OnFragmentInteractionListener mListenerActivity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        topItemNavbar = (TopItemNavbar) view.findViewById(R_id_topbar);
        topItemNavbar.setTitle("Search trips");
        topItemNavbar.setOnMenuClickListener((OnMenuClickListener)view.getParent().getParent().getParent());

        iv_home = (ImageView) view.findViewById(R.id.fcontent02_iv_home);
        iv_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                toast("home");
                mListenerActivity.onFragmentInteraction(0);
            }
        });
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

    public void setOnFragmentInteractionListener(FragmentMenu.OnFragmentInteractionListener mListenerActivity){
        this.mListenerActivity = mListenerActivity;
    }

    private static FragmentContent02SearchTrips fragment;
    public static FragmentContent02SearchTrips getInstance(){
        if (null == fragment){
            fragment = new FragmentContent02SearchTrips();
        }
        return fragment;
    }
}
