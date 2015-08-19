package com.shbestwin.followupmanager.view.adapter.followup;

import java.util.List;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.model.Accompany;
import com.shbestwin.followupmanager.view.adapter.ArrayListAdapter;

public class AccompanyAdapter extends ArrayListAdapter<Accompany>{
	private List<Accompany> mList;
	public AccompanyAdapter(Context context, List<Accompany> list) {
		super(context, list);
		mList = list;
	}

	@Override
	public View getView(int position, View convertView, final ViewGroup parent) {
		Holder holder = new Holder();
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.item_accompany_layout, parent, false);
			holder.NameTextView=(TextView) convertView.findViewById(R.id.NameTextView);
			holder.SexTextView=(TextView) convertView.findViewById(R.id.SexTextView);
			holder.AgeTextView=(TextView) convertView.findViewById(R.id.AgeTextView);
			holder.MobileTextView=(TextView) convertView.findViewById(R.id.MobileTextView);
			holder.IdCardTextView=(TextView) convertView.findViewById(R.id.IdCardTextView);
			holder.AddressTextView=(TextView) convertView.findViewById(R.id.AddressTextView);
			holder.SickTextView=(TextView) convertView.findViewById(R.id.SickTextView);
			convertView.setTag(holder);
		}else{
			holder = (Holder) convertView.getTag();
		}
		Accompany item=mList.get(position);
		holder.NameTextView.setText(item.getName());
		holder.SexTextView.setText(item.getGender());
		holder.AgeTextView.setText(item.getBirthday());
		holder.MobileTextView.setText(item.getTelephone());
		holder.IdCardTextView.setText(item.getIdcard());
		holder.AddressTextView.setText(item.getAddress());
		holder.SickTextView.setText(item.getAccompany_item());
		
		return convertView;
	}

	public class Holder{
	    private TextView NameTextView;
	    private TextView SexTextView;
	    private TextView AgeTextView;
	    private TextView MobileTextView;
	    private TextView IdCardTextView;
	    private TextView AddressTextView;
	    private TextView SickTextView;
	}	
}
