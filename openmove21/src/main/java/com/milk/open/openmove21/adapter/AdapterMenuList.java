package com.milk.open.openmove21.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.milk.open.openmove21.R;
import com.milk.open.openmove21.model.ModelMenuItem;
import com.milk.open.openmove21.util.Utils;

import java.util.List;

public class AdapterMenuList extends BaseAdapter {

	private List<ModelMenuItem> l;
	private Activity activityParent;
//	private ListView mlv;

	class Holder {
		ImageView iv_icon;
		TextView tv_itemstr;
		TextView tv_myticketsnumber;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final int _position = position;
		Holder h = null;
		if (convertView == null) {
			convertView = activityParent.getLayoutInflater().inflate(R.layout.adapter_menulist, null);
//			convertView.setOnClickListener(new OnClickListener() {
//				@Override
//				public void onClick(View v) {
//					Toast.makeText(activityParent,l.get(_position).getSeq(), Toast.LENGTH_SHORT).show();
//				}
//			});
		}

		h = new Holder();
		h.tv_itemstr = (TextView) convertView.findViewById(R.id.adp_menulist_tv_item);
		h.iv_icon = (ImageView) convertView.findViewById(R.id.adp_menulist_iv_icon);

		ModelMenuItem _data = l.get(position);
		convertView.setTag(position);
		h.tv_itemstr.setText(_data.getItemstr());
		h.iv_icon.setBackgroundResource(_data.getImagerid());

		if(4 == position){
			h.tv_myticketsnumber = (TextView) convertView.findViewById(R.id.adp_menulist_tv_myticketsnumber);
//			h.tv_myticketsnumber.setTextSize(10);
			h.tv_myticketsnumber.setText(""+Utils.n_mytickets);
			h.tv_myticketsnumber.setBackgroundResource(R.drawable.fmenu_ic_whitecircle);
		}

		return convertView;
	}

	public AdapterMenuList() {
		super();
	}

	public AdapterMenuList(Activity activity, List<ModelMenuItem> data) {
		super();
		this.activityParent = activity;
		this.l = data;
	}

	@Override
	public int getCount() {
		return l.size();
	}

	@Override
	public Object getItem(int position) {
		if (position >= getCount()) {
			return null;
		}
		return l.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public AdapterMenuList setParent(Activity activity) {
		this.activityParent = activity;
		return this;
	}

	public AdapterMenuList setDataSource(List<ModelMenuItem> data) {
		this.l = data;
		return this;
	}

//	public AdapterMenuList setMlv(ListView mlv) {
//		this.mlv = mlv;
//		return this;
//	}
//
//	public ListView getMlv() {
//		return mlv;
//	}

}