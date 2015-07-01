package com.shbestwin.followupmanager.view.widget;

import java.util.ArrayList;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.shbestwin.followupmanager.R;

public class ExaminationIndicator extends FrameLayout {
	private ArrayList<View> numberViewList = new ArrayList<View>();
	private ArrayList<View> lineViewList = new ArrayList<View>();

	private int totalRecord;
	private int pageSize = 10;
	private int currentIndex = 0;

	public ExaminationIndicator(Context context) {
		this(context, null);
	}

	public ExaminationIndicator(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public ExaminationIndicator(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		View root = LayoutInflater.from(context).inflate(R.layout.view_examination_indicator, this, true);
		numberViewList.add(root.findViewById(R.id.number01TextView));
		numberViewList.add(root.findViewById(R.id.number02TextView));
		numberViewList.add(root.findViewById(R.id.number03TextView));
		numberViewList.add(root.findViewById(R.id.number04TextView));
		numberViewList.add(root.findViewById(R.id.number05TextView));
		numberViewList.add(root.findViewById(R.id.number06TextView));
		numberViewList.add(root.findViewById(R.id.number07TextView));
		numberViewList.add(root.findViewById(R.id.number08TextView));
		numberViewList.add(root.findViewById(R.id.number09TextView));
		numberViewList.add(root.findViewById(R.id.number10TextView));

		lineViewList.add(root.findViewById(R.id.line01TextView));
		lineViewList.add(root.findViewById(R.id.line02TextView));
		lineViewList.add(root.findViewById(R.id.line03TextView));
		lineViewList.add(root.findViewById(R.id.line04TextView));
		lineViewList.add(root.findViewById(R.id.line05TextView));
		lineViewList.add(root.findViewById(R.id.line06TextView));
		lineViewList.add(root.findViewById(R.id.line07TextView));
		lineViewList.add(root.findViewById(R.id.line08TextView));
		lineViewList.add(root.findViewById(R.id.line09TextView));
	}

	public void renderView(int totalRecord) {
		this.totalRecord = totalRecord;
		currentIndex = 0;
		updateView();
	}

	public void updateIndex(int index) {
		currentIndex = index;
		updateView();
	}

	private void updateView() {
		// 当前页
		int pageIndex = currentIndex / pageSize;
		// 当前页的总记录数,控制显示数字的个数和线段的条数
		int pageCount = (pageIndex + 1) * pageSize <= totalRecord ? pageSize : totalRecord % pageSize;

		int position = currentIndex % pageSize;
		for (int i = 0; i < numberViewList.size(); i++) {
			View numberView = numberViewList.get(i);
			int number = pageSize * pageIndex + (i + 1);
			((TextView) numberView).setText("" + number);
			if (i < pageCount) {
				numberView.setVisibility(View.VISIBLE);
				if (i <= position) {
					numberView.setBackgroundResource(R.drawable.examination_indicator_number_bg_active);
				} else {
					numberView.setBackgroundResource(R.drawable.examination_indicator_number_bg_normal);
				}
			} else {
				numberView.setVisibility(View.INVISIBLE);
			}

			if (i <= 0) {
				continue;
			}
			
			View lineView = lineViewList.get(i - 1);
			if (i < pageCount) {
				lineView.setVisibility(View.VISIBLE);
				if (i <= position) {
					lineView.setBackgroundResource(R.drawable.examination_indicator_line_active);
				} else {
					lineView.setBackgroundResource(R.drawable.examination_indicator_line_normal);
				}
			} else {
				lineView.setVisibility(View.INVISIBLE);
			}
		}
	}

	public void next() {
		currentIndex++;
		if (currentIndex >= totalRecord) {
			currentIndex = 0;
		}
		updateView();
	}

	public void pre() {
		currentIndex--;
		if (currentIndex < 0) {
			currentIndex = totalRecord - 1;
		}
		updateView();
	}
}
