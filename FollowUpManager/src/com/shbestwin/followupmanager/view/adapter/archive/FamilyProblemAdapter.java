package com.shbestwin.followupmanager.view.adapter.archive;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.interfaces.FamilyProblemListItemClickHelp;
import com.shbestwin.followupmanager.model.archive.FamilyProblem;
import com.shbestwin.followupmanager.view.adapter.ArrayListAdapter;

public class FamilyProblemAdapter extends ArrayListAdapter<FamilyProblem>{
	private List<FamilyProblem> mList;
	private FamilyProblemListItemClickHelp callback;
	public FamilyProblemAdapter(Context context, List<FamilyProblem> list,FamilyProblemListItemClickHelp callback) {
		super(context, list);
		mList = list;
		this.callback = callback;
	}

	@Override
	public View getView(int position, View convertView, final ViewGroup parent) {
		Holder holder = new Holder();
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.item_jtwt_layout, parent, false);
			
		
			holder.id_ = (TextView) convertView.findViewById(R.id.xhTextView);
			holder.jd  = (TextView) convertView.findViewById(R.id.jdTextView);
			holder.fsr = (TextView) convertView.findViewById(R.id.fsrTextView);
			holder.fsrq  = (TextView) convertView.findViewById(R.id.fsrqTextView);
			holder.zywt = (TextView) convertView.findViewById(R.id.zywtTextView);
			holder.wtpg  = (TextView) convertView.findViewById(R.id.wtpgTextView);
			holder.cljjg = (TextView) convertView.findViewById(R.id.cljjgTextView);
			holder.zgzl  = (TextView) convertView.findViewById(R.id.zgzlTextView);
			
			
			holder.detail = (ImageView) convertView.findViewById(R.id.detail_button);
			holder.update = (ImageView) convertView.findViewById(R.id.edit_button);
			holder.delete = (ImageView) convertView.findViewById(R.id.delete_button);
			convertView.setTag(holder);
		}else{
			holder = (Holder) convertView.getTag();
		}
		holder.id_.setText(String.valueOf(position+1));
		holder.jd.setText(mList.get(position).getJd());
		holder.fsr.setText(mList.get(position).getFsr());
		holder.fsrq.setText(mList.get(position).getFsrq());
		holder.zywt.setText(mList.get(position).getZywt());
		holder.wtpg.setText(mList.get(position).getWtpg());
		holder.cljjg.setText(mList.get(position).getCljjg());
		holder.zgzl.setText(mList.get(position).getZgzl());
		
		
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
		public TextView id_;
		public TextView jd;
		public TextView fsr;
		public TextView fsrq;
		public TextView zywt;
		public TextView wtpg;
		public TextView cljjg;
		public TextView zgzl;
		
		public ImageView detail; 
		public ImageView update;
		public ImageView delete;
	}	
}
