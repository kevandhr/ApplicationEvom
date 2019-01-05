package com.milk.open.openmove21.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.milk.open.openmove21.R;
import com.milk.open.openmove21.fragment.*;
import com.milk.open.openmove21.model.ModelKeyValue;
import com.milk.open.openmove21.slidemenu.SlideMenuContainerLayout;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ActivityMain extends ActivityBase implements FragmentMenu.OnFragmentInteractionListener {

    /**
     * 滑动容器
     */
    private SlideMenuContainerLayout mSlideContainer;

    private FragmentMenu mMenuFragment;

    private Fragment currentFragment;
    private int currentFragmentNickname=0;

    private FragmentContent01SearchTickets fragment01;

    private FragmentContent02 fragment02;

    private FragmentContent03MyTickets fragment03;

    private FragmentContent04TicketInfo fragment04;
    public static int fragment04NICKNAME = 98;

    private String qrinfo;

    private static final int MESSAGE_NET_CONNECTION_ERROR = 9008;
    private static final int MESSAGE_GOTO_FRAGMENT04 = 9001;
    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == MESSAGE_GOTO_FRAGMENT04) {
                onFragmentInteraction(fragment04NICKNAME, (ModelKeyValue)msg.obj);
            }
        }
    };

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

    public void onFragmentInteraction(String msg) {
        mSlideContainer.hide_menu();
    }

    public void onFragmentInteraction(int seq){
        if(0 == seq){
            if(null == fragment01){
                fragment01 = FragmentContent01SearchTickets.getInstance();
            }
            switchContentFragment(fragment01).commit();
            currentFragmentNickname = seq;
        } else if(1 == seq){
            if(null == fragment02){
                fragment02 = FragmentContent02.getInstance();
                fragment02.setOnFragmentInteractionListener(this);
            }
            switchContentFragment(fragment02).commit();
            currentFragmentNickname = seq;
        } else if(4 == seq){
            if(null == fragment03){
                fragment03 = FragmentContent03MyTickets.getInstance();
                fragment03.setOnFragmentInteractionListener(this);
            }
            switchContentFragment(fragment03).commit();
            currentFragmentNickname = seq;
        } else if(fragment04NICKNAME == seq){
            if(null == fragment04){
                fragment04 = FragmentContent04TicketInfo.getInstance();
                fragment04.setOnFragmentInteractionListener(this);
            }
            fragment04.setParentFragment_nickname(currentFragmentNickname);
            switchContentFragment(fragment04).commit();
            currentFragmentNickname = seq;
        }
        mSlideContainer.hide_menu();
    }

    public void onFragmentInteraction(int seq, ModelKeyValue data_time_bus) {
        if (fragment04NICKNAME == seq) {
            if (null == fragment04) {
                fragment04 = FragmentContent04TicketInfo.getInstance(currentFragmentNickname, data_time_bus.getStr1(), data_time_bus.getStr2());
                fragment04.setOnFragmentInteractionListener(this);
            } else {
                fragment04.setParentFragment_nickname(currentFragmentNickname);
                fragment04.setQrinfo(data_time_bus.getStr1(), data_time_bus.getStr2());
            }
            switchContentFragment(fragment04).commit();
            currentFragmentNickname = seq;
        }
        mSlideContainer.hide_menu();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // 获取解析结果
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
//                toast("取消扫描");
            } else {
                // HH:mm:ss
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yy - HH:mm:ss");
                //获取当前时间
                Date date = new Date(System.currentTimeMillis());

                qrinfo = result.getContents();
                String resultqrinfo = "";

                Pattern pattern = Pattern.compile("partner=(.*?)&");
                Matcher matcher = pattern.matcher(qrinfo);
                if(matcher.find()) {
                    resultqrinfo = resultqrinfo + matcher.group(1);
                }

                pattern = Pattern.compile("&id=(.*?)$");
                matcher = pattern.matcher(qrinfo);
                if (matcher.find()) {
                    resultqrinfo = resultqrinfo + matcher.group(1);
                }

//                toast("Validated: "+
//                        simpleDateFormat.format(date)+ "-"+
//                        resultqrinfo);

//                qrinfo = "Validated: "+
//                        simpleDateFormat.format(date)+
//                        " - "+
//                        resultqrinfo;
//                handler.sendEmptyMessage(MESSAGE_GOTO_FRAGMENT04);
//                handler.sendEmptyMessageDelayed(MESSAGE_GOTO_FRAGMENT04, 1100);
                ModelKeyValue data_time_bus = new ModelKeyValue(simpleDateFormat.format(date), resultqrinfo);
                Message message = Message.obtain();
                message.obj = data_time_bus;
                message.what = MESSAGE_GOTO_FRAGMENT04;
                handler.sendMessageDelayed(message, 500);
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    protected void initialized() {

    }

    protected int getLayoutId() {
        return 0;
    }
}
