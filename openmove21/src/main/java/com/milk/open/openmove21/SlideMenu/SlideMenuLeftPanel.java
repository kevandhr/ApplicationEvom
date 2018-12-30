package com.milk.open.openmove21.SlideMenu;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.milk.open.openmove21.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能描述：主界面之左侧面板
 *
 */
public class SlideMenuLeftPanel extends FrameLayout {

//	/**
//	 * 用户图标显示组件
//	 */
//	public ImageView ivUserIcon;
//
//	/**
//	 * 用户名称显示组件
//	 */
//	public TextView tvNickname;
//
//	/**
//	 * 可展开的ListView组件
//	 */
//	private ExpandableListView mExpandableListView;
//
//	/**
//	 * ExpandableListView组件的数据适配器
//	 */
////	private LeftPanelExListViewAdapter mExListViewAdapter;
//
//	/**
//	 * ExpandableListView组件的数据源
//	 */
////	private List<LeftPanelListItem> mListItems = new ArrayList<LeftPanelListItem>();
//
//	/**
//	 * 分组名数组
//	 */
//	private String[] mGroupNames;
//
//	private onSeletedListener mOnSeletedListener;
//
//	@SuppressWarnings("unused")
//	private int mGroupPosition;
//
//	@SuppressWarnings("unused")
//	private int mChildPosition;
//
	public SlideMenuLeftPanel(Context context) {
		super(context);
//		setupViews();
	}
//
//	public SlideMenuLeftPanel(Context context, AttributeSet attrs) {
//		super(context, attrs);
//		setupViews();
//	}
//
//	private void setupViews() {
//		final LayoutInflater mInflater = LayoutInflater.from(getContext());
//		LinearLayout viewRoot = (LinearLayout) mInflater.inflate(
//				R.layout.left_panel, null);
//		addView(viewRoot);
//
//		ivUserIcon = (ImageView) viewRoot.findViewById(R.id.iv_user_icon);
//		tvNickname = (TextView) viewRoot.findViewById(R.id.tv_nickname);
//		mExpandableListView = (ExpandableListView) viewRoot
//				.findViewById(R.id.elv_list_view);
//
//		initialized();
//	}
//
//	private void initialized() {
//		Resources resources = this.getResources();
//		mGroupNames = resources.getStringArray(R.array.left_panel_group_names);
//
//		String[] firstGroupNames = resources
//				.getStringArray(R.array.left_panel_first_group_names);
//		String[] secondGroupNames = resources
//				.getStringArray(R.array.left_panel_second_group_names);
//
//		int[] firstGroupIcons = { R.drawable.left_panel_item_icon,
//				R.drawable.left_panel_item_icon,
//				R.drawable.left_panel_item_icon,
//				R.drawable.left_panel_item_icon,
//				R.drawable.left_panel_item_icon };
//
//		int[] secondGroupIcons = { R.drawable.left_panel_item_icon,
//				R.drawable.left_panel_item_icon,
//				R.drawable.left_panel_item_icon,
//				R.drawable.left_panel_item_icon };
//
//		addGroup(0, firstGroupNames, firstGroupIcons);
//		addGroup(1, secondGroupNames, secondGroupIcons);
//
//		mExListViewAdapter = new LeftPanelExListViewAdapter(getContext(), mListItems);
//		mExpandableListView.setAdapter(mExListViewAdapter);
//
//		// 设置默认让所有组都展开
//		for (int i = 0; i < mListItems.size(); i++) {
//			mExpandableListView.expandGroup(i);
//		}
//
//		// 设置OnGroupClick时，不再展开或收缩组内的子项
//		mExpandableListView.setOnGroupClickListener(new OnGroupClickListener() {
//
//			@Override
//			public boolean onGroupClick(ExpandableListView parent, View v,
//										int groupPosition, long id) {
//				// 表示GroupItem的单击事件已被处理
//				return true;
//			}
//
//		});
//
//		mExpandableListView.setOnChildClickListener(new OnChildClickListener() {
//
//			@Override
//			public boolean onChildClick(ExpandableListView parent, View v,
//										int groupPosition, int childPosition, long id) {
//				if (mOnSeletedListener == null) {
//					return false;
//				}
//
//				mGroupPosition = groupPosition;
//				mChildPosition = childPosition;
//
//				mOnSeletedListener.seletedChildView(groupPosition,
//						childPosition);
//				return true;
//			}
//		});
//	}
//
//	/**
//	 * 添加数据到指定的组
//	 *
//	 * @param groupId
//	 *            组ID
//	 * @param names
//	 *            子项的名字数组
//	 * @param icons
//	 *            子项的图标数组
//	 */
//	private void addGroup(int groupId, String[] names, int[] icons) {
//		LeftPanelListItem listItem = new LeftPanelListItem();
//		listItem.setId(groupId);
//		listItem.setName(mGroupNames[groupId]);
//		// 组没有操作指示图标
//		// listItem.setDrawableId(drawableId);
//
//		ArrayList<LeftPanelListItem> firstGroup = new ArrayList<LeftPanelListItem>();
//		for (int i = 0; i < names.length; i++) {
//			LeftPanelListItem firstGroupItem = new LeftPanelListItem();
//			firstGroupItem.setId(i);
//			firstGroupItem.setName(names[i]);
//			firstGroupItem.setDrawableId(icons[i]);
//
//			// 可以无限延伸
//			// firstGroupItem.setGroups(null);
//			firstGroup.add(firstGroupItem);
//		}
//
//		listItem.setGroups(firstGroup);
//		mListItems.add(listItem);
//	}
//
//	/**
//	 * 设置选中的Item事件监听器
//	 *
//	 * @param seletedListener
//	 */
//	public void setOnSeletedListener(onSeletedListener seletedListener) {
//		mOnSeletedListener = seletedListener;
//	}
//
//	/**
//	 * 选中的Item事件监听器
//	 *
//	 * @author android_ls
//	 */
//	public interface onSeletedListener {
//		/**
//		 * 当前选中的Item事件处理器
//		 *
//		 * @param groupPosition
//		 *            所属组Id
//		 * @param childPosition
//		 *            在所属组内的位置
//		 */
//		public abstract void seletedChildView(int groupPosition,
//                                              int childPosition);
//	}

}