package com.shbestwin.followupmanager.view.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.common.util.ArrayUtils;

public class TabMenuLayout extends RadioGroup {
	protected LayoutInflater mInflater;

	public TabMenuLayout(Context context) {
		this(context, null);
	}

	public TabMenuLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		setBackgroundResource(R.drawable.tab_menu_bg);
		mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public void renderMenu(String[] menus) {
		if (ArrayUtils.isEmpty(menus)) {
			return;
		}
		for (String title : menus) {
			createRadioButton(title);
		}
	}

	private void createRadioButton(String title) {
		RadioButton item = (RadioButton) mInflater.inflate(R.layout.view_tab_item, this, false);
		item.setText(title);
		item.setId(this.getChildCount());
		this.addView(item);
	}

	public void checkItem(int position) {
		((RadioButton) this.getChildAt(position)).setChecked(true);
	}
}
