package com.milk.open.openmove21.fragment;

import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.milk.open.openmove21.Util.UtilLog;

public class FragmentBase extends Fragment {
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
            toast = Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT);
        else
            toast.setText(msg);
        toast.show();
    }

    public void toast(int id) {
        if (null == toast)
            toast = Toast.makeText(getActivity(), id, Toast.LENGTH_SHORT);
        else
            toast.setText(id);
        toast.show();
    }

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null)
            return;

        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.UNSPECIFIED);
        int totalHeight = 0;
        View view = null;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            view = listAdapter.getView(i, view, listView);
            if (i == 0)
                view.setLayoutParams(new ViewGroup.LayoutParams(desiredWidth, ViewGroup.LayoutParams.WRAP_CONTENT));

            view.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += view.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }


    public FragmentBase(){
        super();
        UtilLog.i("call FragmentBase()");
    }

    private static FragmentBase fragment;
    public static FragmentBase getInstance(){
        if (null == fragment){
            fragment = new FragmentBase();
        }
        return fragment;
    }
}
