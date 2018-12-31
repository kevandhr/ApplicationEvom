package com.milk.open.openmove21.diyview;

import android.widget.FrameLayout;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.milk.open.openmove21.R;
import com.milk.open.openmove21.SlideMenu.OnMenuClickListener;

/**
 * 功能描述：自定义顶部菜单栏
 *
 */
public class TopMenuNavbar extends FrameLayout {

	// 打开左侧菜单的组件
	private LinearLayout llShowMenu;

	/**
	 * 下拉列表
	 */
	public RelativeLayout mLlDownList;

	/**
	 * 当前的标题
	 */
	public TextView tvTitle;

	/**
	 * 下拉标识
	 */
	public ImageView ivDownListIcon;

	/**
	 * 刷新图标
	 */
	public ImageView ivRefresh;

	/**
	 * 右侧操作（动作）的名称
	 */
	public TextView tvRightOperationName;

	/**
	 * 右侧竖直分割线
	 */
	public ImageView ivRightLine;

	/**
	 * 右侧的操作触控组件
	 */
	public LinearLayout mLlRefresh;

	/**
	 * 打开左侧菜单的组件的事件监听器
	 */
	private OnMenuClickListener mOnClickListener;

	public TopMenuNavbar(Context context) {
		super(context);
		setupViews();
	}

	public TopMenuNavbar(Context context, AttributeSet attrs) {
		super(context, attrs);
		setupViews();
	}

	public void setOnClickListener(OnMenuClickListener onClickListener) {
		mOnClickListener = onClickListener;
	}

	private void setupViews() {
		final LayoutInflater mLayoutInflater = LayoutInflater
				.from(getContext());
		RelativeLayout rlTopNavbar = (RelativeLayout) mLayoutInflater.inflate(
				R.layout.top_menu_navbar, null);
		addView(rlTopNavbar);

		llShowMenu = (LinearLayout) rlTopNavbar.findViewById(R.id.ll_back);
		mLlDownList = (RelativeLayout) rlTopNavbar
				.findViewById(R.id.ll_down_list);
		mLlRefresh = (LinearLayout) rlTopNavbar.findViewById(R.id.ll_refresh);

		tvTitle = (TextView) rlTopNavbar.findViewById(R.id.tv_title);
		ivDownListIcon = (ImageView) rlTopNavbar
				.findViewById(R.id.iv_down_list_icon);
		ivRightLine = (ImageView) rlTopNavbar.findViewById(R.id.iv_right_line);

		llShowMenu.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (mOnClickListener != null) {
					mOnClickListener.onClick();
				}
			}
		});
	}

}
