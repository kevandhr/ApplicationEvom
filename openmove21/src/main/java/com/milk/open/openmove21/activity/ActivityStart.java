package com.milk.open.openmove21.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.Toast;
import com.milk.open.openmove21.R;
import com.milk.open.openmove21.util.Tools;

public class ActivityStart extends ActivityBase {

    int islogin = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        ImageView iv_screen = (ImageView) findViewById(R.id.iv_1_activity_start);
        AlphaAnimation an = new AlphaAnimation(0.1f, 1.0f);
        an.setDuration(500);
        iv_screen.setAnimation(an);
        an.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
                init();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (islogin == 0) {
                    startActivity(new Intent(ActivityStart.this,
                            ActivityMain.class));
                } else {
                    startActivity(new Intent(ActivityStart.this,
                            ActivityMain.class));
                }
                ActivityStart.this.setResult(0);
                ActivityStart.this.finish();
            }
        });
    }

    private void init() {
        if (Tools.isNetworkAvailable(ActivityStart.this)) {
            Toast.makeText(ActivityStart.this, "network accessed",
                    Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(ActivityStart.this, "no network", Toast.LENGTH_SHORT)
                    .show();
        }
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
