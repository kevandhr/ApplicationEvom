package com.milk.open.openmove21.SlideMenu;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import com.milk.open.openmove21.R;

/**
 * 功能描述：新鲜事视图
 *
 */
public class FreshNewsLayout extends FrameLayout implements OnClickListener {

	private TopMenuNavbar topMenuNavbar;
	private Context context;

	public TopMenuNavbar getTopMenuNavbar() {
		return topMenuNavbar;
	}

	public FreshNewsLayout(Context context) {
		super(context);
		this.context = context;
		setupViews();
	}

	public FreshNewsLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		setupViews();
	}

	private void setupViews() {
		final LayoutInflater mLayoutInflater = LayoutInflater
				.from(getContext());
		LinearLayout rlTopNavbar = (LinearLayout) mLayoutInflater.inflate(
				R.layout.frame_freshnews, null);
		addView(rlTopNavbar);

		topMenuNavbar = (TopMenuNavbar) rlTopNavbar
				.findViewById(R.id.rl_top_menu_navbar);
		topMenuNavbar.mLlDownList.setOnClickListener(this);
		topMenuNavbar.mLlRefresh.setOnClickListener(this);
		topMenuNavbar.tvTitle.setText(context.getString(R.string.app_name));
		topMenuNavbar.ivRightLine.setVisibility(View.GONE);
//		topMenuNavbar.tvRightOperationName.setVisibility(View.GONE);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.ll_down_list:

				break;
			case R.id.ll_refresh:

				break;
			default:
				break;
		}

	}

}
