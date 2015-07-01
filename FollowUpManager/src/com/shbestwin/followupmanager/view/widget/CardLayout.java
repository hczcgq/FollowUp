package com.shbestwin.followupmanager.view.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.shbestwin.followupmanager.R;

public class CardLayout extends LinearLayout {
	private int currentExpandIndex = -1;

	public CardLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public CardLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public CardLayout(Context context) {
		super(context);
	}

	@Override
	protected void onAttachedToWindow() {
		super.onAttachedToWindow();
		setEvent();
	}

	private void setEvent() {
		for (int i = 0; i < getChildCount(); i++) {
			View item = getChildAt(i);
			if (item instanceof ViewGroup) {
				final ViewGroup viewGroup = (ViewGroup) item;
				if (viewGroup.getChildCount() >= 2) {
					viewGroup.getChildAt(0).setTag(i);
					viewGroup.getChildAt(0).setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							int index = (Integer) v.getTag();
							if (currentExpandIndex == index) {// 如果当前展开的条目已经展开，则关闭
								currentExpandIndex = -1;
								for (int j = 1; j < viewGroup.getChildCount(); j++) {
									viewGroup.getChildAt(j).setVisibility(View.GONE);
								}
								View switchView = viewGroup.getChildAt(0).findViewWithTag("switch");
								if (switchView != null && switchView instanceof ImageView) {
									((ImageView) switchView).setImageResource(R.drawable.icon_expand);
								}
							} else {
								if (currentExpandIndex >= 0) {// 将已经打开的条目关闭
									View expandedItem = getChildAt(currentExpandIndex);
									if (expandedItem instanceof ViewGroup) {
										final ViewGroup expandedViewGroup = (ViewGroup) expandedItem;
										if (expandedViewGroup.getChildCount() >= 2) {
											for (int j = 1; j < expandedViewGroup.getChildCount(); j++) {
												expandedViewGroup.getChildAt(j).setVisibility(View.GONE);
											}
										}
										View switchView = expandedViewGroup.getChildAt(0).findViewWithTag("switch");
										if (switchView != null && switchView instanceof ImageView) {
											((ImageView) switchView).setImageResource(R.drawable.icon_expand);
										}
									}

								}
								// 打开条目
								currentExpandIndex = index;
								View expandedItem = getChildAt(currentExpandIndex);
								if (expandedItem instanceof ViewGroup) {
									final ViewGroup expandedViewGroup = (ViewGroup) expandedItem;
									if (expandedViewGroup.getChildCount() >= 2) {
										for (int j = 1; j < expandedViewGroup.getChildCount(); j++) {
											expandedViewGroup.getChildAt(j).setVisibility(View.VISIBLE);
										}
									}
									View switchView = expandedViewGroup.getChildAt(0).findViewWithTag("switch");
									if (switchView != null && switchView instanceof ImageView) {
										((ImageView) switchView).setImageResource(R.drawable.icon_collapse);
									}
								}
							}
						}
					});
				}
			}

		}
	}

	public void reload() {
		setEvent();
	}

	@Override
	protected void onDetachedFromWindow() {
		super.onDetachedFromWindow();
	}

}
