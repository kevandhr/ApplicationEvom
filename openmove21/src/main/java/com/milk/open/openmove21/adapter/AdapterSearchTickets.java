package com.milk.open.openmove21.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.*;
import com.milk.open.openmove21.R;
import com.milk.open.openmove21.model.ModelTicket;

import java.util.List;

public class AdapterSearchTickets extends BaseAdapter {

	private List<ModelTicket> l;
	private Activity activityParent;
//	private ListView mlv;

	class Holder {
		TextView tv_state;
		TextView tv_scope;
		TextView tv_timelimit;
		TextView tv_money;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final int _position = position;
		Holder h = null;
		if (convertView == null) {
			convertView = activityParent.getLayoutInflater().inflate(R.layout.adapter_searchtickets, null);
			convertView.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
//					UtilLog.i(l.get(_position).getTicketid());
					Toast.makeText(activityParent,l.get(_position).getTicketid(), Toast.LENGTH_SHORT).show();
				}
			});
		}

		h = new Holder();
		h.tv_state = (TextView) convertView.findViewById(R.id.adp_stickets_tv_state);
		h.tv_scope = (TextView) convertView.findViewById(R.id.adp_stickets_tv_scope);
		h.tv_timelimit = (TextView) convertView.findViewById(R.id.adp_stickets_tv_timelimit);
		h.tv_money = (TextView) convertView.findViewById(R.id.adp_stickets_tv_money);

		ModelTicket _data = l.get(position);
		convertView.setTag(position);
		h.tv_state.setText(_data.getState());
		h.tv_scope.setText(_data.getScope());
		h.tv_timelimit.setText(_data.getTimelimit());
		h.tv_money.setText(_data.getMoneystr());
//		h.iv01.setBackgroundResource(R.drawable.downloading_photo);
//		syncImageLoader.loadImage(position, _data.getPicurl(),
//				imageLoadListener);

		return convertView;
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

	public AdapterSearchTickets() {
		super();
	}

	public AdapterSearchTickets(Activity activity) {
		super();
		this.activityParent = activity;
	}

	public AdapterSearchTickets(Activity activity, List<ModelTicket> data) {
		super();
		this.activityParent = activity;
		this.l = data;
	}

	public AdapterSearchTickets setParent(Activity activity) {
		this.activityParent = activity;
		return this;
	}

	public AdapterSearchTickets setDataSource(List<ModelTicket> data) {
		this.l = data;
		return this;
	}

}