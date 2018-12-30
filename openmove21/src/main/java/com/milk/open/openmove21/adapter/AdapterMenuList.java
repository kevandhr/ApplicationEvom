package com.milk.open.openmove21.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.media.Image;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.*;
import com.milk.open.openmove21.R;
import com.milk.open.openmove21.Util.UtilLog;
import com.milk.open.openmove21.model.ModelMenuItem;

import java.util.List;

public class AdapterMenuList extends BaseAdapter {

	private List<ModelMenuItem> l;
	private Activity activityParent;
	private ListView mlv;

	class Holder {
		ImageView iv_icon;
		TextView tv_itemstr;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final int _position = position;
		Holder h = null;
		if (convertView == null) {
			convertView = activityParent.getLayoutInflater().inflate(R.layout.adapter_menulist, null);
			convertView.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					Toast.makeText(activityParent,l.get(_position).getSeq(), Toast.LENGTH_SHORT).show();
				}
			});
		}

		h = new Holder();
		h.tv_itemstr = (TextView) convertView.findViewById(R.id.tv_item_adp_menulist);

		ModelMenuItem _data = l.get(position);
		convertView.setTag(position);
		h.tv_itemstr.setText(_data.getItemstr());
		h.tv_itemstr.setTextColor(Color.WHITE);

		return convertView;
	}

	public AdapterMenuList() {
		super();
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
		l = data;
		return this;
	}

	public AdapterMenuList setMlv(ListView mlv) {
		this.mlv = mlv;
		return this;
	}

	public ListView getMlv() {
		return mlv;
	}

}