package com.shbestwin.followupmanager.fragment.archive;

import com.shbestwin.followupmanager.fragment.BaseFragment;
import com.shbestwin.followupmanager.model.ArchiveInfo;

public abstract class BaseArchiveFragment extends BaseFragment {

	public abstract boolean validate();

	public abstract ArchiveInfo getArchiveInfo();
	
	public abstract void refreshData();
}
