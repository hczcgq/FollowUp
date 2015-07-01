package com.shbestwin.followupmanager.view.adapter.followup;

import java.util.List;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.interfaces.ListItemClickHelp;
import com.shbestwin.followupmanager.model.followup.Insulin;
import com.shbestwin.followupmanager.view.adapter.ArrayListAdapter;

public class InsulinListAdapter extends ArrayListAdapter<Insulin> {
    private List<Insulin> list;
	public InsulinListAdapter(Context context, List<Insulin> list) {
		super(context, list);
		this.list=list;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.view_followup_diabetes_mellitus_insulin_list_item, parent, false);
		}
		if (position % 2 == 0) {
			convertView.setBackgroundColor(cardinalBg);
		} else {
			convertView.setBackgroundColor(evenBg);
		}
		
		 TextView tv_ywzl=(TextView) convertView.findViewById(R.id.tv_ywzl);
         TextView tv_sypl=(TextView) convertView.findViewById(R.id.tv_sypl);
         TextView tv_syjl=(TextView) convertView.findViewById(R.id.tv_syjl);
         ImageView im_edit=(ImageView) convertView.findViewById(R.id.im_edit);
         ImageView im_delete=(ImageView) convertView.findViewById(R.id.im_delete);
        
         Insulin Insulin=list.get(position);
         tv_ywzl.setText(Insulin.getYds_ywzl());
         tv_sypl.setText(Insulin.getYds_sypl());
         tv_syjl.setText(Insulin.getYds_syjl());
         
         im_edit.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
                clickHelp.onClick(position, R.id.im_edit);
            }
        });
         
         im_delete.setOnClickListener(new OnClickListener() {
                
                @Override
                public void onClick(View v) {
                    clickHelp.onClick(position, R.id.im_delete);
                }
            });

		return convertView;
	}
	
	private ListItemClickHelp clickHelp;
    public void setListItemClickHelp(ListItemClickHelp clickHelp){
        this.clickHelp=clickHelp;
    }

}
