package com.milk.open.openmove21.SlideMenu;

import android.widget.*;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import com.milk.open.openmove21.R;
import com.milk.open.openmove21.Util.UtilLog;

/**
 * 功能描述：自定义顶部菜单栏
 *
 */
public class TopItemNavbar extends FrameLayout {

	private OnMenuClickListener onMenuClickListener;
	private ImageView iv_menu;
	private TextView tv_title;

	public TopItemNavbar(Context context) {
		super(context);
		setupViews();
	}

	public TopItemNavbar(Context context, AttributeSet attrs) {
		super(context, attrs);
		setupViews();
	}

	private void setupViews() {
		final LayoutInflater mLayoutInflater = LayoutInflater.from(getContext());
		RelativeLayout rlTopNavbar = (RelativeLayout) mLayoutInflater.inflate(R.layout.top_item_navbar, null);
		addView(rlTopNavbar);

		iv_menu = (ImageView) rlTopNavbar.findViewById(R.id.iv_menu_titembar);
		iv_menu.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
//				UtilLog.i("onclick");
				if(null != onMenuClickListener) {
					onMenuClickListener.onClick();
				}
			}
		});

		tv_title = (TextView) rlTopNavbar.findViewById(R.id.tv_title_titembar);
	}

	public void setTitle(String strtitle){
		tv_title.setText(strtitle);
	}

	public void setOnMenuClickListener(OnMenuClickListener onMenuClickListener){
		this.onMenuClickListener = onMenuClickListener;
	}

}
