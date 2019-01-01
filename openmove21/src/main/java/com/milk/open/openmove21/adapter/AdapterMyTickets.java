package com.milk.open.openmove21.adapter;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;
import com.milk.open.openmove21.R;
import com.milk.open.openmove21.model.ModelTicket;

import java.util.List;

public class AdapterMyTickets extends BaseAdapter {

	private List<ModelTicket> l;
	private Activity activityParent;

	class Holder {
		TextView tv_state;
		TextView tv_scope;
		TextView tv_timelimit;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final int _position = position;
		Holder h = null;
		if (convertView == null) {
			convertView = activityParent.getLayoutInflater().inflate(R.layout.adapter_mytickets, null);
			convertView.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
//					UtilLog.i(l.get(_position).getTicketid());
					Toast.makeText(activityParent,l.get(_position).getTicketid(), Toast.LENGTH_SHORT).show();
				}
			});
		}

		h = new Holder();
		h.tv_state = (TextView) convertView.findViewById(R.id.adp_mytickets_tv_state);
		h.tv_scope = (TextView) convertView.findViewById(R.id.adp_mytickets_tv_scope);
		h.tv_timelimit = (TextView) convertView.findViewById(R.id.adp_mytickets_tv_timelimit);

		ModelTicket _data = l.get(position);
		convertView.setTag(position);
		h.tv_state.setText(_data.getState());
		h.tv_scope.setText(_data.getScope());
		h.tv_timelimit.setText(_data.getTimelimit());
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

	public AdapterMyTickets() {
		super();
	}

	public AdapterMyTickets(Activity activity, List<ModelTicket> data) {
		super();
		this.activityParent = activity;
		this.l = data;
	}

	public AdapterMyTickets setParent(Activity activity) {
		this.activityParent = activity;
		return this;
	}

	public AdapterMyTickets setDataSource(List<ModelTicket> data) {
		this.l = data;
		return this;
	}

//	public AdapterSearchTickets setMlv(ListView mlv) {
//		this.mlv = mlv;
//		return this;
//	}
//
//	public ListView getMlv() {
//		return mlv;
//	}

}