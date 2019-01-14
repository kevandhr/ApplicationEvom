package com.milk.open.openmove21.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.milk.open.openmove21.R;

public class ActivityPopupTCode extends ActivityBase {

    private TextView tv_tcode;

    private TextView tv_cancel;

    private TextView tv_submit;

    public ActivityPopupTCode() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_tcode);

//        Intent intent = this.getIntent();
//        buildingId = (String) intent.getSerializableExtra("building");

        tv_submit = (TextView) findViewById(R.id.act_pop_tv_submit);
        tv_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();// 重新声明一个意图。
                int three = Integer.parseInt("re");// 获取输入的值。
                intent.putExtra("three", three); // 将three回传到意图中。
                // 通过Intent对象返回结果，调用setResult方法。
                setResult(ActivityMain.REQUESTCODE_TCODE, intent);// resultCode为大于1的数，随意选取，为2即可。
                finish();// 结束当前Activity的生命周期。
            }
        });

    }

    @Override
    protected void setupView() {

    }

    @Override
    protected void initialized() {

    }

    @Override
    protected int getLayoutId() {
        return 0;
    }
}
