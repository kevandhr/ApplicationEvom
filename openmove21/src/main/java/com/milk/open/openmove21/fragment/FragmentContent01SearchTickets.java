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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.milk.open.openmove21.R;
import com.milk.open.openmove21.slidemenu.OnMenuClickListener;
import com.milk.open.openmove21.diyview.TopItemNavbar;
import com.milk.open.openmove21.util.Utils;
import com.milk.open.openmove21.adapter.AdapterSearchTickets;
import com.milk.open.openmove21.model.ModelTicket;
import org.w3c.dom.Text;

import java.util.ArrayList;

public class FragmentContent01SearchTickets extends FragmentBase {

    private int R_id_layout = R.layout.fragment_content_01;
    private int R_id_topbar = R.id.fcontent01_topitembar;

    private TopItemNavbar topItemNavbar;
    private ListView mListView;
    private ArrayList<ModelTicket> arraydata;
    private AdapterSearchTickets adapter;
    private ImageView iv_proDialog;
    private TextView tv_arr_to;
    private int is_clickable_tv_arr_to = 4;

    private static final int MESSAGE_NET_CONNECTION_ERROR = 108;
    private static final int MESSAGE_UPDATE_PRINT = 101;
    private static final int MESSAGE_UPDATE_PRINT_TESTDATA = 102;
    private static final int MESSAGE_CLOSE_PRODIALOG = 103;
    private static final int MESSAGE_TV_ARR_CLICKABLE = 104;
    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (animation_proDialog != null) {
                animation_proDialog.stop();
                iv_proDialog.setVisibility(View.GONE);
            }
            if (msg.what == MESSAGE_UPDATE_PRINT_TESTDATA) {
                if (Utils.IS_TEST_DATA){
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
                }
                print();
            } else if (msg.what == MESSAGE_UPDATE_PRINT) {
                print();
            } else if (msg.what == MESSAGE_NET_CONNECTION_ERROR) {
                toast(R.string.no_internet);
            } else if (msg.what == MESSAGE_TV_ARR_CLICKABLE) {
                is_clickable_tv_arr_to = 4;
            }
        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        iv_proDialog = view.findViewById(R.id.fcontent01_iv_proDialog);
        animation_proDialog = AnimationsContainer.getInstance(R.array.animation_prodialog1, 18, this.getContext()).createProgressDialogAnim(iv_proDialog);

        animation_proDialog.start();

        topItemNavbar = (TopItemNavbar) view.findViewById(R_id_topbar);
        topItemNavbar.setTitle("SEATCH TICKETS");
        topItemNavbar.setOnMenuClickListener((OnMenuClickListener)view.getParent().getParent().getParent());

        arraydata = new ArrayList<ModelTicket>();
        adapter = new AdapterSearchTickets(getActivity(), arraydata);

        mListView = (ListView) view.findViewById(R.id.fcontent01_lv);
        mListView.setAdapter(adapter);
//        mListView.setOnTouchListener(new View.OnTouchListener() {
//            // Setting on Touch Listener for handling the touch inside ScrollView
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                // Disallow the touch request for parent scroll on touch of child view
//                v.getParent().requestDisallowInterceptTouchEvent(true);
//                return false;
//            }
//        });
        setListViewHeightBasedOnChildren(mListView);

        tv_arr_to = (TextView) view.findViewById(R.id.vs_tv_arr);
        tv_arr_to.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(is_clickable_tv_arr_to > 3) {
                    if (null != animation_proDialog) {
                        mListView.setVisibility(View.INVISIBLE);
                        iv_proDialog.setVisibility(View.VISIBLE);
                        animation_proDialog.start();
                        new Thread(new Rungetdata()).start();
                    }
                    is_clickable_tv_arr_to = 3;
                } else if(is_clickable_tv_arr_to >= 2){
                    is_clickable_tv_arr_to -= 2;
                } else {
                    toast("You clicked too fast!");
                    is_clickable_tv_arr_to = 3;
                }
            }
        });
        new Thread(new Rungetdata()).start();
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
//    @Override
//    public void onHiddenChanged(boolean hidden) {
//        super.onHiddenChanged(hidden);
//        if (hidden) {
//
//        } else {
////            ((LinearLayout)mListView.getParent()).addView(iv_proDialog);
////            animation_proDialog.start();
////            handler.sendEmptyMessageDelayed(MESSAGE_CLOSE_PRODIALOG, 500);
//        }
//    }

    private void print() {
        mListView.setVisibility(View.VISIBLE);
        adapter.setDataSource(arraydata);
        adapter.notifyDataSetChanged();
        handler.sendEmptyMessageDelayed(MESSAGE_TV_ARR_CLICKABLE, 1000);
    }

    private void getData() {
        if (Utils.IS_TEST_DATA){
            // test data
//            arraydata.clear();
//            for (int i = 0; i < Utils.n_data; i++) {
//                ModelTicket aticket = new ModelTicket(
//                        Utils.ticketid[i],
//                        Utils.states[i],
//                        Utils.scopes[i],
//                        Utils.timelimits[i],
//                        Utils.money[i]);
//                arraydata.add(aticket);
//            }

            handler.sendEmptyMessageDelayed(MESSAGE_UPDATE_PRINT_TESTDATA, 3400);
//            try {
//
//            } catch (Exception e) {
//                handler.sendEmptyMessage(MESSAGE_NET_CONNECTION_ERROR);
//            } finally {
//                handler.sendEmptyMessageDelayed(MESSAGE_UPDATE_PRINT, 800);
//            }
        }
    }

    private static FragmentContent01SearchTickets fragment;
    public static FragmentContent01SearchTickets getInstance(){
        if (null == fragment){
            fragment = new FragmentContent01SearchTickets();
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
