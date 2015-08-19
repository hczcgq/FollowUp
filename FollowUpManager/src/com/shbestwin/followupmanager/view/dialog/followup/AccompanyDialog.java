package com.shbestwin.followupmanager.view.dialog.followup;

import java.util.List;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.model.Accompany;
import com.shbestwin.followupmanager.view.adapter.followup.AccompanyAdapter;
import com.shbestwin.followupmanager.view.dialog.BaseDialogReportFragment;

public class AccompanyDialog extends
		BaseDialogReportFragment {
    private TextView cancelView;
    private ListView accompanyListView;
    private List<Accompany> accompanyList;
    private TextView TitleTextView;
    private int index;

	public static AccompanyDialog newInstance() {
		AccompanyDialog dialog = new AccompanyDialog();
		return dialog;
	}
	
	public AccompanyDialog() {

    }

	public AccompanyDialog(List<Accompany> accompanyList,int index) {
	    this.accompanyList=accompanyList;
	    this.index=index;
	}

	@Override
	protected View getBodyView(LayoutInflater inflater) {
		View rootView = View.inflate(getActivity(),
				R.layout.dialog_accompany, null);
		cancelView = (TextView) rootView.findViewById(R.id.cancelView);
		cancelView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                hide();
                if (null != onCancelClickListener) {
                    onCancelClickListener.onCancelClick();
                }
            }
        });
		
		TitleTextView=(TextView) rootView.findViewById(R.id.TitleTextView);
		if(index==0) {
		    TitleTextView.setText("随访管理明细列表（本月应随访）");
		}else if(index==1) {
		    TitleTextView.setText("随访管理明细列表（已随访）");
        }else if(index==2) {
            TitleTextView.setText("随访管理明细列表（未随访）");
        }
		
		accompanyListView=(ListView) rootView.findViewById(R.id.accompanyListView);
		accompanyListView.setAdapter(new AccompanyAdapter(getActivity(), accompanyList));
		accompanyListView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                    long arg3) {
                onItemListener.onItemClick(accompanyList.get(arg2));
                hide();
            }
        });
		return rootView;
	}
	

    public interface OnCancelClickListener {
        public void onCancelClick();
    }

    private OnCancelClickListener onCancelClickListener;

    public void setOnCancelClickListener(
            OnCancelClickListener onCancelClickListener) {
        this.onCancelClickListener = onCancelClickListener;
    }
    
    public interface OnItemListener {
        public void onItemClick(Accompany accompany);
    }

    private OnItemListener onItemListener;

    public void setOnItemListener(OnItemListener onItemListener) {
        this.onItemListener = onItemListener;
    }
}
