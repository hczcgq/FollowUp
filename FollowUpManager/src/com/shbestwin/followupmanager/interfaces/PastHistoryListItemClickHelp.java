package com.shbestwin.followupmanager.interfaces;

import android.view.View;

public interface PastHistoryListItemClickHelp {
	void onClickDisease(View item, View widget, int position, int which);
	
	void onClickOperation(View item, View widget, int position, int which);
	
	void onClickInjury(View item, View widget, int position, int which);
	
	void onClickTransfusion(View item, View widget, int position, int which);
}
