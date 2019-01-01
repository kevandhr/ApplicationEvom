package com.milk.open.openmove21.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.milk.open.openmove21.R;
import com.milk.open.openmove21.model.ModelKeyValue;

import java.util.List;

public class AdapterTicketInfoUserRecord extends BaseAdapter {

	private List<ModelKeyValue> l;
	private Activity activityParent;

	class Holder {
		TextView tv_str1;
		TextView tv_str2;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final int _position = position;
		Holder h = null;
		if (convertView == null) {
			convertView = activityParent.getLayoutInflater().inflate(R.layout.adapter_ticketinfo_keyvalue, null);
		}

		h = new Holder();
		h.tv_str1 = (TextView) convertView.findViewById(R.id.adp_kv_tv_str1);
		h.tv_str2 = (TextView) convertView.findViewById(R.id.adp_kv_tv_str2);

		ModelKeyValue _data = l.get(position);
		h.tv_str1.setText(_data.getStr1());
		h.tv_str2.setText(_data.getStr2());

		return convertView;
	}

	public AdapterTicketInfoUserRecord() {
		super();
	}

	public AdapterTicketInfoUserRecord(Activity activity, List<ModelKeyValue> data) {
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

	public AdapterTicketInfoUserRecord setParent(Activity activity) {
		this.activityParent = activity;
		return this;
	}

	public AdapterTicketInfoUserRecord setDataSource(List<ModelKeyValue> data) {
		this.l = data;
		return this;
	}

}