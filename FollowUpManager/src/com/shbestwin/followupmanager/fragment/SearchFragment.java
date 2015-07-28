package com.shbestwin.followupmanager.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.fragment.search.SearchBackgroundFragment;
import com.shbestwin.followupmanager.fragment.search.SearchUploadFragment;

/**
 * 
 * @ClassName: SearchFragment
 * @Description: 搜索页
 *
 */
public class SearchFragment extends BaseFragment implements OnClickListener{
	
	private Button btn_background,btn_upload;

	public static SearchFragment newInstance() {
		SearchFragment fragment = new SearchFragment();
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_search, container, false);
		btn_background=(Button) rootView.findViewById(R.id.btn_background);
		btn_upload=(Button) rootView.findViewById(R.id.btn_upload);
		btn_background.setOnClickListener(this);
		btn_upload.setOnClickListener(this);
		
		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		changePage(0);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_background:
		    btn_background.setEnabled(false);
			btn_upload.setEnabled(true);
			changePage(0);
			break;

		case R.id.btn_upload:
			btn_background.setEnabled(true);
			btn_upload.setEnabled(false);
			changePage(1);
			break;
		}
	}
	
	private void changePage(int index) {
		FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
		ft.replace(R.id.mainlayout, newInstance(index));
		ft.commitAllowingStateLoss();
	}

	private Fragment newInstance(int index) {
		switch (index) {
		case 0:
		    return SearchBackgroundFragment.newInstance();
		case 1:
			return SearchUploadFragment.newInstance();
		}
		return null;
	}
	
	
}
