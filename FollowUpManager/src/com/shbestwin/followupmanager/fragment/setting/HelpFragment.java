package com.shbestwin.followupmanager.fragment.setting;

import java.io.File;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.shbestwin.followupmanager.MyApplication;
import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.common.util.ToastUtils;
import com.shbestwin.followupmanager.fragment.BaseFragment;

public class HelpFragment extends BaseFragment {
	private Button showHelpDocButton;

	public static HelpFragment newInstance() {
		HelpFragment fragment = new HelpFragment();
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_setting_help, container, false);
		showHelpDocButton = (Button) rootView.findViewById(R.id.showHelpDocButton);
		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		showHelpDocButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
//				startPdfFileIntent();
			}
		});
	}

	// android获取一个用于打开PDF文件的intent
	public void startPdfFileIntent() {
		String fileName = MyApplication.getInstance().getAppDir() + "help.pdf";
		System.out.println("Address:"+fileName);
		Intent intent = new Intent("android.intent.action.VIEW");
		intent.addCategory("android.intent.category.DEFAULT");
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		Uri uri = Uri.fromFile(new File(fileName));
		intent.setDataAndType(uri, "application/pdf");
		try {
			getActivity().startActivity(intent);
        } 
        catch (ActivityNotFoundException e) {
        	ToastUtils.showToast(getActivity(), "没有找到打开pdf文件的相关软件");
        }
	}
}
