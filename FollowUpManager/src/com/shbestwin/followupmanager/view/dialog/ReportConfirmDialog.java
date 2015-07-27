package com.shbestwin.followupmanager.view.dialog;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.common.util.SystemUtils;

public class ReportConfirmDialog extends DialogFragment {
	private TextView confirmView, cancelView,tv_name,tv_msg;
	private FrameLayout bodyLayout;
	private String name,msg;

	public static ReportConfirmDialog newInstance() {
		ReportConfirmDialog dialog = new ReportConfirmDialog();
		return dialog;
	}
	
	public ReportConfirmDialog(String name,String msg){
		this.name=name;
		this.msg=msg;
	}
	
	public static ReportConfirmDialog newInstance(int messageResId) {
		ReportConfirmDialog fragment = new ReportConfirmDialog();
		Bundle args = new Bundle();
		args.putInt("messageResId", messageResId);
		fragment.setArguments(args);
		return fragment;
	}

	public static ReportConfirmDialog newInstance(String message) {
		ReportConfirmDialog fragment = new ReportConfirmDialog();
		Bundle args = new Bundle();
		args.putString("message", message);
		fragment.setArguments(args);
		return fragment;
	}

	public ReportConfirmDialog() {

	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setStyle(DialogFragment.STYLE_NO_TITLE, 0);
	}

	protected View getBodyView(LayoutInflater inflater) {
		return null;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_report_dialog,
				container, false);
		confirmView = (TextView) rootView.findViewById(R.id.confirmView);
		cancelView = (TextView) rootView.findViewById(R.id.cancelView);
		tv_name = (TextView) rootView.findViewById(R.id.tv_name);
		tv_msg = (TextView) rootView.findViewById(R.id.tv_msg);
		tv_name.setText(name);
		tv_msg.setText(msg);

		confirmView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (onConfirmClickListener != null) {
					onConfirmClickListener.onConfirmClick();
				}
			}
		});

		cancelView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				hide();
			}
		});
		return rootView;
	}

	public void hide() {
		SystemUtils.hiddenIME(bodyLayout);
		dismissAllowingStateLoss();
	}

	private OnConfirmClickListener onConfirmClickListener;

	public interface OnConfirmClickListener {
		public void onConfirmClick();
	}

	public void setOnConfirmClickListener(
			OnConfirmClickListener onConfirmClickListener) {
		this.onConfirmClickListener = onConfirmClickListener;
	}

}
