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
import android.widget.LinearLayout;
import android.widget.ListView;
import com.milk.open.openmove21.R;
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

    private ImageView iv_proDialog;

    private static final int MESSAGE_NET_CONNECTION_ERROR = 308;
    private static final int MESSAGE_UPDATE_PRINT = 301;
    private static final int MESSAGE_CLOSE_PRODIALOG = 303;

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
//            if (proDialog != null) {
//                proDialog.dismiss();
//            }
            if (animation_proDialog != null) {
                animation_proDialog.stop();
                ((LinearLayout)mListView.getParent()).removeView(iv_proDialog);
            }
            if (msg.what == MESSAGE_UPDATE_PRINT) {

                print();
            } else if (msg.what == MESSAGE_NET_CONNECTION_ERROR) {
                toast(R.string.no_internet);
            }
        }
    };
    private ProgressDialog proDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        proDialog = ProgressDialog
//                .show(this.getContext(), getString(R.string.lianjiezhong)
//                        , getString(R.string.lianjiezhong_shaohou), true, true);
        arraydata = new ArrayList<ModelTicket>();
        new Thread(new Rungetdata()).start();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        iv_proDialog = view.findViewById(R.id.fcontent03_iv_proDialog);
        animation_proDialog = AnimationsContainer.getInstance(R.array.animation_prodialog1, 16, this.getContext()).createProgressDialogAnim(iv_proDialog);
        animation_proDialog.start();

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

        adapter = new AdapterMyTickets(getActivity(), arraydata);

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

    //重新回来fragment
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {

        } else {
            ((LinearLayout)mListView.getParent()).addView(iv_proDialog);
            animation_proDialog.start();
            handler.sendEmptyMessageDelayed(MESSAGE_CLOSE_PRODIALOG, 500);
        }
    }

    private void print() {
        adapter.setDataSource(arraydata);
        adapter.notifyDataSetChanged();
    }

    public void setOnFragmentInteractionListener(FragmentMenu.OnFragmentInteractionListener mListenerActivity){
        this.mListenerActivity = mListenerActivity;
    }

    private void getData() {
        if (Utils.IS_TEST_DATA){

                // test data
                arraydata.clear();
                int seq_validticket = 10;
                ModelTicket aticket0 = new ModelTicket(Utils.ticketid[seq_validticket],
                        Utils.states[seq_validticket],
                        Utils.scopes[seq_validticket],
                        Utils.timelimits[seq_validticket],
                        Utils.money[seq_validticket]);
                aticket0.setCategory(Utils.TC_CIVEZZANO);
                aticket0.setIsvalid(true);
                aticket0.setArr(Utils.myticket_arr);
                arraydata.add(aticket0);
//            for (int i = 1; i < Utils.n_mytickets; i++) {
//                ModelTicket aticket = new ModelTicket(
//                        Utils.ticketid[i],
//                        Utils.states[i],
//                        Utils.scopes[i],
//                        Utils.timelimits[i],
//                        Utils.money[i]);
//                arraydata.add(aticket);
//            }

            handler.sendEmptyMessageDelayed(MESSAGE_UPDATE_PRINT, 600);

//            try {
//
//            } catch (Exception e) {
//                handler.sendEmptyMessage(MESSAGE_NET_CONNECTION_ERROR);
//            } finally {
//                handler.sendEmptyMessageDelayed(MESSAGE_UPDATE_PRINT, 500);
//            }
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
