package com.shbestwin.followupmanager.view.dialog;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.view.dialog.BaseDialogFragment;


public class DeviceNameDialog extends BaseDialogFragment {
	private EditText et_name;
	
	public static DeviceNameDialog newInstance() {
		DeviceNameDialog dialog = new DeviceNameDialog();
		return dialog;
	}
	
	public DeviceNameDialog(){
		
	}
	
	
	@Override
	protected View getBodyView(LayoutInflater inflater) {
		View bodyView = View.inflate(getActivity(), R.layout.dialog_device, null);
		et_name = (EditText) bodyView.findViewById(R.id.et_name);
		return bodyView;
	}
	
	public String getName(){
		return et_name.getText().toString();
	}
}
