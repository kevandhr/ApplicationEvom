package com.milk.open.openmove21.diyview;

import android.widget.*;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import com.milk.open.openmove21.R;
import com.milk.open.openmove21.slidemenu.OnMenuClickListener;

/**
 * 功能描述：自定义顶部菜单栏
 *
 */
public class TopItemNavbar extends FrameLayout {

	private OnMenuClickListener onMenuClickListener;
	private RelativeLayout rl_gomenu;
	private ImageView iv_gomenu;
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

		rl_gomenu = (RelativeLayout) rlTopNavbar.findViewById(R.id.rl_menu_titembar);
		rl_gomenu.setOnClickListener(new OnClickListener() {
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
