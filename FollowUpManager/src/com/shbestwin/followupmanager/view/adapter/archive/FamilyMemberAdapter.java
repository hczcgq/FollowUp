package com.shbestwin.followupmanager.view.adapter.archive;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.interfaces.FamilyMemberListItemClickHelp;
import com.shbestwin.followupmanager.model.archive.FamilyMember;
import com.shbestwin.followupmanager.view.adapter.ArrayListAdapter;

public class FamilyMemberAdapter extends ArrayListAdapter<FamilyMember>{
	private List<FamilyMember> mList;
	private FamilyMemberListItemClickHelp callback;
	public FamilyMemberAdapter(Context context, List<FamilyMember> list,FamilyMemberListItemClickHelp callback) {
		super(context, list);
		mList = list;
		this.callback = callback;
	}

	@Override
	public View getView(int position, View convertView, final ViewGroup parent) {
		Holder holder = new Holder();
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.item_jtcy_layout, parent, false);
			
			holder.yhzgx = (TextView) convertView.findViewById(R.id.yhzgxTextView);
			holder.xm  = (TextView) convertView.findViewById(R.id.xmTextView);
			holder.xb = (TextView) convertView.findViewById(R.id.xbTextView);
			holder.csrq  = (TextView) convertView.findViewById(R.id.csrqTextView);
			holder.whcd = (TextView) convertView.findViewById(R.id.whcdTextView);
			holder.zy  = (TextView) convertView.findViewById(R.id.zyTextView);
			holder.hyzk = (TextView) convertView.findViewById(R.id.hyzkTextView);
			holder.grzt  = (TextView) convertView.findViewById(R.id.grzkTextView);
			
			
			holder.detail = (ImageView) convertView.findViewById(R.id.detail_btn);
			holder.update = (ImageView) convertView.findViewById(R.id.edit_btn);
			holder.delete = (ImageView) convertView.findViewById(R.id.delete_btn);
			convertView.setTag(holder);
		}else{
			holder = (Holder) convertView.getTag();
		}
		holder.yhzgx.setText(mList.get(position).getRelationship());
		holder.xm.setText(mList.get(position).getName());
		holder.xb.setText(mList.get(position).getGender());
		holder.csrq.setText(mList.get(position).getBirthday());
		holder.whcd.setText(mList.get(position).getEducation());
		holder.zy.setText(mList.get(position).getJob());
		holder.hyzk.setText(mList.get(position).getMarriage());
		holder.grzt.setText(mList.get(position).getPersonalStatus());
		
		
		final View view = convertView;
        final int p = position;
        final int one = holder.detail.getId();
        final int two = holder.update.getId();
		final int three = holder.delete.getId();
		
		holder.detail.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				callback.onClick(view, parent, p, one);
			}
		});
        
		holder.update.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				callback.onClick(view, parent, p, two);
			}
		});
		holder.delete.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				callback.onClick(view, parent, p, three);
			}
		});
		return convertView;
	}

	public class Holder{
		public TextView yhzgx;
		public TextView xm;
		public TextView xb;
		public TextView csrq;
		public TextView whcd;
		public TextView zy;
		public TextView hyzk;
		public TextView grzt;
		
		public ImageView detail; 
		public ImageView update;
		public ImageView delete;
	}	
}
