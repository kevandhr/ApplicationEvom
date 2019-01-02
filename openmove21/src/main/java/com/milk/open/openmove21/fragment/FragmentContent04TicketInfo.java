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
import android.widget.ListView;
import android.widget.TextView;
import com.milk.open.openmove21.R;
import com.milk.open.openmove21.adapter.AdapterTicketInfoUserRecord;
import com.milk.open.openmove21.diyview.TopItemNavbar;
import com.milk.open.openmove21.model.ModelKeyValue;
import com.milk.open.openmove21.slidemenu.OnMenuClickListener;
import com.milk.open.openmove21.util.Utils;

import java.util.ArrayList;

public class FragmentContent04TicketInfo extends FragmentBase {

    private int R_id_layout = R.layout.fragment_content_04;
    private int R_id_topbar = R.id.fcontent04_topitembar;

    private TopItemNavbar topItemNavbar;
    private ImageView iv_home;
    private ImageView iv_back;
    private TextView tv_qrinfo;

    private FragmentMenu.OnFragmentInteractionListener mListenerActivity;

    private ListView lv_userinfo;
    private ListView lv_ticketrecord;
    private ArrayList<ModelKeyValue> arraydata_userinfo;
    private ArrayList<ModelKeyValue> arraydata_ticketrecord;
    private AdapterTicketInfoUserRecord adapter_userinfo;
    private AdapterTicketInfoUserRecord adapter_ticketrecord;

    private int parentFragment_nickname;
    private String qrinfostr="";

    private static final int MESSAGE_NET_CONNECTION_ERROR = 408;
    private static final int MESSAGE_UPDATE_PRINT = 401;
    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == MESSAGE_UPDATE_PRINT) {
                print();
            } else if (msg.what == MESSAGE_NET_CONNECTION_ERROR) {
                toast(R.string.no_internet);
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
        arraydata_userinfo = new ArrayList<ModelKeyValue>();
        arraydata_ticketrecord = new ArrayList<ModelKeyValue>();
        adapter_userinfo = new AdapterTicketInfoUserRecord(getActivity(), arraydata_userinfo);
        adapter_ticketrecord = new AdapterTicketInfoUserRecord(getActivity(), arraydata_ticketrecord);
        new Thread(new Rungetdata()).start();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        topItemNavbar = (TopItemNavbar) view.findViewById(R_id_topbar);
        topItemNavbar.setTitle("DETAILS");
        topItemNavbar.setOnMenuClickListener((OnMenuClickListener)view.getParent().getParent().getParent());

        iv_home = (ImageView) view.findViewById(R.id.fcontent04_iv_home);
        iv_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                toast("home");
                mListenerActivity.onFragmentInteraction(0);
            }
        });

        iv_back = (ImageView) view.findViewById(R.id.fcontent04_iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListenerActivity.onFragmentInteraction(parentFragment_nickname);
            }
        });

        lv_userinfo = (ListView) view.findViewById(R.id.fcontent04_lv_userinfo);
        lv_userinfo.setAdapter(adapter_userinfo);
        lv_ticketrecord = (ListView) view.findViewById(R.id.fcontent04_lv_trecord);
        lv_ticketrecord.setAdapter(adapter_ticketrecord);

//        lv_userinfo.setOnTouchListener(new View.OnTouchListener() {
//            // Setting on Touch Listener for handling the touch inside ScrollView
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                // Disallow the touch request for parent scroll on touch of child view
//                v.getParent().requestDisallowInterceptTouchEvent(true);
//                return false;
//            }
//        });
//        lv_ticketrecord.setOnTouchListener(new View.OnTouchListener() {
//            // Setting on Touch Listener for handling the touch inside ScrollView
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                // Disallow the touch request for parent scroll on touch of child view
//                v.getParent().requestDisallowInterceptTouchEvent(true);
//                return false;
//            }
//        });
        setListViewHeightBasedOnChildren(lv_userinfo);
        setListViewHeightBasedOnChildren(lv_ticketrecord);

        tv_qrinfo = (TextView) view.findViewById(R.id.fcontent04_tv_qrinfo);
        tv_qrinfo.setText(qrinfostr);
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
        if (!hidden) {
            tv_qrinfo.setText(qrinfostr);
        }
    }

    private void print() {
        adapter_userinfo.setDataSource(arraydata_userinfo);
        adapter_userinfo.notifyDataSetChanged();
        adapter_ticketrecord.setDataSource(arraydata_ticketrecord);
        adapter_ticketrecord.notifyDataSetChanged();
    }

    public void setOnFragmentInteractionListener(FragmentMenu.OnFragmentInteractionListener mListenerActivity){
        this.mListenerActivity = mListenerActivity;
    }

    private void getData() {
        if (Utils.IS_TEST_DATA){
            // test data
            arraydata_userinfo.clear();
            for (int i = 0; i < Utils.n_userinfo; i++) {
                ModelKeyValue a = new ModelKeyValue(
                        Utils.userinfostr1[i],
                        Utils.userinfostr2[i]);
                arraydata_userinfo.add(a);
            }

            arraydata_ticketrecord.clear();
            for (int i = 0; i < Utils.n_ticketrecords; i++) {
                ModelKeyValue arecord = new ModelKeyValue(
                        Utils.ticketrecordstr1[i],
                        Utils.ticketrecordstr2[i]);
                arraydata_ticketrecord.add(arecord);
            }
            try {
                Thread.sleep(200);

            } catch (Exception e) {
                handler.sendEmptyMessage(MESSAGE_NET_CONNECTION_ERROR);
            } finally {
                handler.sendEmptyMessage(MESSAGE_UPDATE_PRINT);
            }
        }
    }

    public String getQrinfo() {
        return qrinfostr;
    }

    public void setQrinfo(String qrinfostr) {
        this.qrinfostr = qrinfostr;
    }

    public int getParentFragment_nickname() {
        return parentFragment_nickname;
    }

    public void setParentFragment_nickname(int parentFragment_nickname) {
        this.parentFragment_nickname = parentFragment_nickname;
    }

    private static FragmentContent04TicketInfo fragment;
    public static FragmentContent04TicketInfo getInstance(){
        if (null == fragment){
            fragment = new FragmentContent04TicketInfo();
        }
        return fragment;
    }

    public static FragmentContent04TicketInfo getInstance(String qrinfo){
        fragment = getInstance();
        fragment.setQrinfo(qrinfo);
        return fragment;
    }

    public static FragmentContent04TicketInfo getInstance(int parentFragment_nickname, String qrinfo){
        fragment = getInstance(qrinfo);
        fragment.setParentFragment_nickname(parentFragment_nickname);
        return fragment;
    }

    class Rungetdata implements Runnable {
        @Override
        public void run() {
            getData();
        }
    }
}
