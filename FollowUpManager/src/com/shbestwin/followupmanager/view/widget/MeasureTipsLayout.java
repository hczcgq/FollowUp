package com.shbestwin.followupmanager.view.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.shbestwin.followupmanager.R;

public class MeasureTipsLayout extends FrameLayout {
	private TextView tipsTextView;

	public MeasureTipsLayout(Context context) {
		this(context, null);
	}

	public MeasureTipsLayout(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public MeasureTipsLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		View root = LayoutInflater.from(context).inflate(R.layout.view_measure_tips, this, true);
		tipsTextView = (TextView) root.findViewById(R.id.tipsTextView);
	}

	public void setTips(int tipsResId) {
		tipsTextView.setText(tipsResId);
	}
}
