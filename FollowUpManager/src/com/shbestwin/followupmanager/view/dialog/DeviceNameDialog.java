package com.shbestwin.followupmanager.view.dialog;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.model.setting.Device;
import com.shbestwin.followupmanager.view.dialog.BaseDialogFragment;


public class DeviceNameDialog extends BaseDialogFragment {
	private EditText et_name;
	private Device device;
	
	public static DeviceNameDialog newInstance() {
		DeviceNameDialog dialog = new DeviceNameDialog();
		return dialog;
	}
	
	public DeviceNameDialog(){
		
	}
	
	public DeviceNameDialog(Device device){
		this.device=device;
	}
	
	
	@Override
	protected View getBodyView(LayoutInflater inflater) {
		View bodyView = View.inflate(getActivity(), R.layout.dialog_device, null);
		et_name = (EditText) bodyView.findViewById(R.id.et_name);
		setName(et_name);
		return bodyView;
	}
	
	public String getName(){
		return et_name.getText().toString();
	}
	
	private void setName(EditText view){
		String value="";
		if (device.getCode().equals("0201")) {
			value = "ReadIDCard";
		} else if (device.getCode().equals("0301")) {
			value = "Waistline";
		} else if (device.getCode().equals("0401")) {
			value = "BloodPressure_BP";
		} else if (device.getCode().equals("0402")) {
			value = "BloodPressure_HEM";
		} else if (device.getCode().equals("0501")) {
			value = "BloodGlucose";
		} else if (device.getCode().equals("0502")) {
			value = "BloodGlucose_BU";
		} else if (device.getCode().equals("0601")) {
			value = "BloodFat_BU";
		} else if (device.getCode().equals("0701")) {
			value = "FingerOximeter";
		} else if (device.getCode().equals("0801")) {
			value = "Electrocardiogram";
		} else if (device.getCode().equals("0901")) {
			value = "BodyComposition";
		} else if (device.getCode().equals("1001")) {
			value = "Print";
		}
		SharedPreferences preferences = getActivity().getSharedPreferences(
				"DEVICE_NAME", Context.MODE_PRIVATE);
		if(preferences.contains(value)){
			view.setText(preferences.getString(value, ""));
		}
	}
}
