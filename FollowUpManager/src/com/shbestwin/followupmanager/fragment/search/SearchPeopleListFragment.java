package com.shbestwin.followupmanager.fragment.search;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.fragment.BaseFragment;

public class SearchPeopleListFragment extends BaseFragment {

	public static SearchPeopleListFragment newInstance() {
		SearchPeopleListFragment fragment = new SearchPeopleListFragment();
		return fragment;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_search_people_list, container, false);
		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}
}
