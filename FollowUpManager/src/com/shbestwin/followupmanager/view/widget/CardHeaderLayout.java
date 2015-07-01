package com.shbestwin.followupmanager.view.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.shbestwin.followupmanager.R;

public class CardHeaderLayout extends FrameLayout {
	private TextView titleTextView;

	public CardHeaderLayout(Context context) {
		this(context, null);
	}

	public CardHeaderLayout(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public CardHeaderLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		View rootView = LayoutInflater.from(context).inflate(R.layout.view_card_header, this, true);
		titleTextView = (TextView) rootView.findViewById(R.id.titleTextView);

		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CardHeaderLayout);
		String titleText = a.getString(R.styleable.CardHeaderLayout_titleText);
		titleTextView.setText(titleText);
		a.recycle();
	}

	public void setTitleText(int titleTextResId) {
		titleTextView.setText(titleTextResId);
	}

	public void setTitleText(String titleText) {
		titleTextView.setText(titleText);
	}

}
