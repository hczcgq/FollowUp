package com.shbestwin.followupmanager.fragment.setting;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.fragment.BaseFragment;

public class FunctionFragment extends BaseFragment{

	
	public static FunctionFragment newInstance() {
		FunctionFragment fragment = new FunctionFragment();
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_setting_function, container, false);
		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}
}
