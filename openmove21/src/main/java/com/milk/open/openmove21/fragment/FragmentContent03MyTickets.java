package com.milk.open.openmove21.fragment;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import com.milk.open.openmove21.R;
import com.milk.open.openmove21.util.UtilLog;
import com.milk.open.openmove21.util.Utils;
import com.milk.open.openmove21.adapter.AdapterMyTickets;
import com.milk.open.openmove21.diyview.TopItemNavbar;
import com.milk.open.openmove21.model.ModelTicket;
import com.milk.open.openmove21.slidemenu.OnMenuClickListener;

import java.util.ArrayList;

public class FragmentContent03MyTickets extends FragmentBase {

    private int R_id_layout = R.layout.fragment_content_03;
    private int R_id_topbar = R.id.fcontent03_topitembar;

    private TopItemNavbar topItemNavbar;
    private ImageView iv_home;

    private FragmentMenu.OnFragmentInteractionListener mListenerActivity;

    private ListView mListView;
    private ArrayList<ModelTicket> arraydata;
    private AdapterMyTickets adapter;

    private static final int MESSAGE_NET_CONNECTION_ERROR = 308;
    private static final int MESSAGE_UPDATE_PRINT = 301;
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
        adapter = new AdapterMyTickets(getActivity(), arraydata);
        new Thread(new Rungetdata()).start();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        topItemNavbar = (TopItemNavbar) view.findViewById(R_id_topbar);
        topItemNavbar.setTitle("MY TICKETS");
        topItemNavbar.setOnMenuClickListener((OnMenuClickListener)view.getParent().getParent().getParent());

        iv_home = (ImageView) view.findViewById(R.id.fcontent03_iv_home);
        iv_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                toast("home");
                mListenerActivity.onFragmentInteraction(0);
            }
        });

        mListView = (ListView) view.findViewById(R.id.fcontent03_lv);
        mListView.setAdapter(adapter);
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
        UtilLog.i("print()");
        adapter.setDataSource(arraydata);
        adapter.notifyDataSetChanged();
    }

    public void setOnFragmentInteractionListener(FragmentMenu.OnFragmentInteractionListener mListenerActivity){
        this.mListenerActivity = mListenerActivity;
    }

    private void getData() {
        if (Utils.IS_TEST_DATA){
            try {
                Thread.sleep(1000);
                // test data
                arraydata.clear();
                for (int i = 1; i < Utils.n_mytickets; i++) {
                    ModelTicket aticket = new ModelTicket(
                            Utils.ticketid[i],
                            Utils.states[i],
                            Utils.scopes[i],
                            Utils.timelimits[i],
                            Utils.money[i]);
                    arraydata.add(aticket);
                }
            } catch (Exception e) {
                handler.sendEmptyMessage(MESSAGE_NET_CONNECTION_ERROR);
            } finally {
                handler.sendEmptyMessage(MESSAGE_UPDATE_PRINT);
            }
        }
    }

    private static FragmentContent03MyTickets fragment;
    public static FragmentContent03MyTickets getInstance(){
        if (null == fragment){
            fragment = new FragmentContent03MyTickets();
        }
        return fragment;
    }

    class Rungetdata implements Runnable {
        @Override
        public void run() {
            getData();
        }
    }
}
