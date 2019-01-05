package com.milk.open.openmove21.adapter;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.*;
import com.google.zxing.integration.android.IntentIntegrator;
import com.milk.open.openmove21.R;
import com.milk.open.openmove21.activity.ActivityMain;
import com.milk.open.openmove21.model.ModelTicket;
import com.milk.open.openmove21.util.Utils;

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
		ImageView iv_category;
		TextView tv_arr;
		ImageView iv_che;
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

		if(Utils.TC_CIVEZZANO == _data.getCategory()){
            h.iv_category = (ImageView) convertView.findViewById(R.id.adp_mytickets_iv_deparr);
            h.iv_category.setAdjustViewBounds(true);
            h.iv_category.setMaxWidth(36);
            h.iv_category.setImageResource(R.drawable.ticket_deparr);

            h.tv_scope.setTextSize(14);

            h.tv_arr = (TextView) convertView.findViewById(R.id.adp_mytickets_tv_arr);
            h.tv_arr.setText(_data.getArr());
            h.tv_arr.setTextSize(17);
            h.tv_arr.setTextColor(0xFF777777);

            RelativeLayout.LayoutParams Params =  (RelativeLayout.LayoutParams)h.tv_timelimit.getLayoutParams();
            Params.topMargin = 4+h.tv_arr.getHeight();
            h.tv_timelimit.setLayoutParams(Params);
        }
		if(_data.isIsvalid()) {
            h.tv_state.setTextColor(0xFF379637);
            h.tv_timelimit.setTextColor(0xFF379637);

            h.iv_che = (ImageView) convertView.findViewById(R.id.adp_mytickets_iv_che);
            h.iv_che.setImageResource(R.drawable.fcontent_04_ic_bustype_green1);

            h.iv_isvalid_bg = (ImageView) convertView.findViewById(R.id.adp_mytickets_isvalid_bg);
            h.iv_isvalid_bg.setImageResource(R.drawable.animation_adpmticket_isvalid_bg);
            anDrawable_isvalid_bg = (AnimationDrawable) h.iv_isvalid_bg.getDrawable();
            anDrawable_isvalid_bg.start();

            h.tv_validate.setText("Validate again");
        }

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