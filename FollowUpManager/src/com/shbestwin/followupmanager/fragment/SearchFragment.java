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
import com.shbestwin.followupmanager.fragment.search.SearchPeopleFragment;
import com.shbestwin.followupmanager.fragment.search.SearchUploadFragment;
import com.shbestwin.followupmanager.fragment.search.SearchWorkFragment;

/**
 * 
 * @ClassName: SearchFragment
 * @Description: 搜索页
 *
 */
public class SearchFragment extends BaseFragment implements OnClickListener{
	
	private Button btn_people,btn_work,btn_upload;

	public static SearchFragment newInstance() {
		SearchFragment fragment = new SearchFragment();
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_search, container, false);
		btn_people=(Button) rootView.findViewById(R.id.btn_people);
		btn_work=(Button) rootView.findViewById(R.id.btn_work);
		btn_upload=(Button) rootView.findViewById(R.id.btn_upload);
		btn_people.setOnClickListener(this);
		btn_work.setOnClickListener(this);
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
		case R.id.btn_people:
			btn_people.setEnabled(false);
			btn_work.setEnabled(true);
			btn_upload.setEnabled(true);
			changePage(0);
			break;
		case R.id.btn_work:
			btn_people.setEnabled(true);
			btn_work.setEnabled(false);
			btn_upload.setEnabled(true);
			changePage(1);
			break;
		case R.id.btn_upload:
			btn_people.setEnabled(true);
			btn_work.setEnabled(true);
			btn_upload.setEnabled(false);
			changePage(2);
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
			return SearchPeopleFragment.newInstance();
		case 1:
			return SearchWorkFragment.newInstance();
		case 2:
			return SearchUploadFragment.newInstance();
		}
		return null;
	}
	
	
}
