package com.milk.open.openmove21.adapter;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.zxing.integration.android.IntentIntegrator;
import com.milk.open.openmove21.R;
import com.milk.open.openmove21.activity.ActivityMain;
import com.milk.open.openmove21.model.ModelTicket;

import java.util.List;

public class AdapterMyTickets extends BaseAdapter {

	private List<ModelTicket> l;
	private Activity activityParent;

	private AnimationDrawable anDrawable_isvalid_bg;

	class Holder {
		TextView tv_state;
		TextView tv_scope;
		TextView tv_timelimit;
		TextView tv_validate;
		ImageView iv_isvalid_bg;
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
//                    anDrawable_isvalid_bg.stop();
//					UtilLog.i(l.get(_position).getTicketid());
//					Toast.makeText(activityParent,l.get(_position).getTicketid(), Toast.LENGTH_SHORT).show();
                    ((ActivityMain)activityParent).onFragmentInteraction(ActivityMain.fragment04NICKNAME);
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

		if(_data.isIsvalid()) {
            h.iv_isvalid_bg = (ImageView) convertView.findViewById(R.id.adp_mytickets_isvalid_bg);
            h.iv_isvalid_bg.setImageResource(R.drawable.animation_iv_isvalid_bg);
            anDrawable_isvalid_bg = (AnimationDrawable) h.iv_isvalid_bg.getDrawable();
            anDrawable_isvalid_bg.start();
        }

        h.tv_validate = (TextView) convertView.findViewById(R.id.adp_mytickets_tv_validate);
        h.tv_validate.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // 创建IntentIntegrator对象
                IntentIntegrator intentIntegrator = new IntentIntegrator(activityParent);
                intentIntegrator.setOrientationLocked(false);
                // 开始扫描
                intentIntegrator.initiateScan();
            }
        });

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

}