package com.milk.open.openmove21.activity;

import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import com.milk.open.openmove21.fragment.UtilConstants;
import com.milk.open.openmove21.Util.Utils;

public class ActivityBase extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getScreenSize();
    }

    /**
     * get screen size
     */
    public void getScreenSize() {
        Point point = Utils.getScreenSize(this);
        UtilConstants.sHeight = point.x;
        UtilConstants.sWidth = point.y;
    }
    private Toast toast;

    /**
     * 格式化字符串
     *
     * @param format
     * @param args
     */
    public String format(String format, String args) {
        return String.format(format, args);
    }

    public void toast(String msg) {
        if (null == msg) {
            return;
        }
        if (null == toast)
            toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
        else
            toast.setText(msg);
        toast.show();
    }

    public void toast(int id) {
        if (null == toast)
            toast = Toast.makeText(getApplicationContext(), id, Toast.LENGTH_SHORT);
        else
            toast.setText(id);
        toast.show();
    }
}
