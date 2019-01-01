package com.milk.open.openmove21.fragment;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.milk.open.openmove21.R;
import com.milk.open.openmove21.slidemenu.OnMenuClickListener;
import com.milk.open.openmove21.diyview.TopItemNavbar;
import com.milk.open.openmove21.Util.Utils;
import com.milk.open.openmove21.adapter.AdapterSearchTickets;
import com.milk.open.openmove21.model.ModelTicket;

import java.util.ArrayList;

public class FragmentContent01SearchTickets extends FragmentBase {

    private int R_id_layout = R.layout.fragment_content_01;
    private int R_id_topbar = R.id.fcontent01_topitembar;

    private TopItemNavbar topItemNavbar;
    private ListView mListView;
    private ArrayList<ModelTicket> arraydata;
    private AdapterSearchTickets adapter;

    private static final int MESSAGE_NET_CONNECTION_ERROR = 108;
    private static final int MESSAGE_UPDATE_PRINT = 101;
    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == MESSAGE_UPDATE_PRINT) {
                print();
            } else if (msg.what == MESSAGE_NET_CONNECTION_ERROR) {
                toast(R.string.tag_no_internet);
            }
            if (proDialog != null) {
                proDialog.dismiss();
            }
        }
    };
    private ProgressDialog proDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        proDialog = ProgressDialog
                .show(this.getContext(), getString(R.string.lianjiezhong)
                        , getString(R.string.lianjiezhong_shaohou), true, true);
        arraydata = new ArrayList<ModelTicket>();
        adapter = new AdapterSearchTickets(getActivity(), arraydata);
        new Thread(new Rungetdata()).start();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        topItemNavbar = (TopItemNavbar) view.findViewById(R_id_topbar);
        topItemNavbar.setTitle("SEATCH TICKETS");
        topItemNavbar.setOnMenuClickListener((OnMenuClickListener)view.getParent().getParent().getParent());

        mListView = (ListView) view.findViewById(R.id.fcontent01_lv);
        mListView.setAdapter(adapter);
        mListView.setOnTouchListener(new View.OnTouchListener() {
            // Setting on Touch Listener for handling the touch inside ScrollView
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // Disallow the touch request for parent scroll on touch of child view
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });
        setListViewHeightBasedOnChildren(mListView);
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

    private void print() {
        adapter.setDataSource(arraydata);
        adapter.notifyDataSetChanged();
    }

    private static FragmentContent01SearchTickets fragment;
    public static FragmentContent01SearchTickets getInstance(){
        if (null == fragment){
            fragment = new FragmentContent01SearchTickets();
        }
        return fragment;
    }

    private void getData() {
        if (Utils.IS_TEST_DATA){
            // test data
            arraydata.clear();
            for (int i = 0; i < Utils.n_data; i++) {
                ModelTicket aticket = new ModelTicket(
                        Utils.ticketid[i],
                        Utils.states[i],
                        Utils.scopes[i],
                        Utils.timelimits[i],
                        Utils.money[i]);
                arraydata.add(aticket);
            }

            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                handler.sendEmptyMessage(MESSAGE_NET_CONNECTION_ERROR);
            } finally {
                handler.sendEmptyMessage(MESSAGE_UPDATE_PRINT);
            }
            return;
        }
    }

    class Rungetdata implements Runnable {
        @Override
        public void run() {
            getData();
        }
    }
}
