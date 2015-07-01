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
import com.shbestwin.followupmanager.model.followup.Medication;
import com.shbestwin.followupmanager.view.adapter.ArrayListAdapter;

public class MedicationListAdapter extends ArrayListAdapter<Medication> {
    private List<Medication> list;
	public MedicationListAdapter(Context context, List<Medication> list) {
		super(context, list);
		this.list=list;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.view_followup_medication_list_item, parent, false);
		}
		if (position % 2 == 0) {
			convertView.setBackgroundColor(cardinalBg);
		} else {
			convertView.setBackgroundColor(evenBg);
		}
		
		TextView tv_xh=(TextView) convertView.findViewById(R.id.tv_xh);
		TextView tv_ywmc=(TextView) convertView.findViewById(R.id.tv_ywmc);
		TextView tv_ywlx=(TextView) convertView.findViewById(R.id.tv_ywlx);
		TextView tv_yl=(TextView) convertView.findViewById(R.id.tv_yl);
		TextView tv_dw=(TextView) convertView.findViewById(R.id.tv_dw);
		TextView tv_yf=(TextView) convertView.findViewById(R.id.tv_yf);
		TextView tv_syzjl=(TextView) convertView.findViewById(R.id.tv_syzjl);
		TextView tv_jyfs=(TextView) convertView.findViewById(R.id.tv_jyfs);

		
		 ImageView im_edit=(ImageView) convertView.findViewById(R.id.im_edit);
         ImageView im_delete=(ImageView) convertView.findViewById(R.id.im_delete);
        
         Medication medication=list.get(position);
         tv_xh.setText(String.valueOf(position+1));
         tv_ywmc.setText(medication.getYyqk_ywmc());
         tv_ywlx.setText(medication.getYyqk_ywlx());
         tv_yl.setText(medication.getYyqk_yl());
         tv_dw.setText(medication.getYyqk_dw());
         tv_yf.setText(medication.getYyqk_yf());
         tv_syzjl.setText(medication.getYyqk_syzjl());
         tv_jyfs.setText(medication.getYyqk_gyfs());
       
         
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
