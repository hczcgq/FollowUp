package com.shbestwin.followupmanager.view.widget;

import java.util.List;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AnticipateOvershootInterpolator;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.model.MenuItem;
import com.shbestwin.followupmanager.view.adapter.LeftMenuAdapter;

public class LeftMenuLayout extends LinearLayout {

	private ListView menuListView;
	private ImageView switchMenuImageView;

	private ObjectAnimator openAnim, closeAnim;

	private boolean menuOpend = true;

	private View marginView;

	public LeftMenuLayout(Context context) {
		this(context, null);
	}

	public LeftMenuLayout(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public LeftMenuLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		View rootView = LayoutInflater.from(context).inflate(R.layout.view_left_menu_layout, this, true);
		menuListView = (ListView) rootView.findViewById(R.id.menuListView);
		switchMenuImageView = (ImageView) rootView.findViewById(R.id.switchMenuImageView);

		switchMenuImageView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				toggle();
			}
		});

		menuListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

		menuListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				menuListView.setItemChecked(position, true);
				if (onMenuItemClickListener != null) {
					onMenuItemClickListener.onMenuItemClick(position);
				}
			}
		});
	}

	public void renderMenu(List<MenuItem> menuItems) {
		LeftMenuAdapter adapter = new LeftMenuAdapter(getContext(), menuItems);
		menuListView.setAdapter(adapter);
		menuListView.setItemChecked(0, true);// 默认选中第一条
	}

	public void checkItem(int position) {
		menuListView.setItemChecked(position, true);
	}

	public void setMarginView(View marginView) {
		this.marginView = marginView;
	}

	public void toggle() {
		if (!menuOpend) {
			openMenu();
		} else {
			closeMenu();
		}
	}

	public void openMenu() {
		if (menuOpend) {
			return;
		}
		if (closeAnim != null && closeAnim.isRunning()) {
			closeAnim.end();
		}

		if (openAnim == null || !openAnim.isRunning()) {
			openAnim = ObjectAnimator.ofFloat(this, "x", getX(), getX() + menuListView.getWidth()).setDuration(300);
			openAnim.setInterpolator(new AnticipateOvershootInterpolator());
			openAnim.start();
		}
		menuOpend = true;
		switchMenuImageView.setImageResource(R.drawable.left_menu_close);
		marginView.setVisibility(View.VISIBLE);
	}

	public void closeMenu() {
		if (!menuOpend) {
			return;
		}

		if (openAnim != null && openAnim.isRunning()) {
			openAnim.end();
		}
		if (closeAnim == null || !closeAnim.isRunning()) {
			closeAnim = ObjectAnimator.ofFloat(this, "x", getX(), getX() - menuListView.getWidth()).setDuration(300);
			closeAnim.setInterpolator(new AnticipateOvershootInterpolator());
			closeAnim.start();
		}
		menuOpend = false;
		switchMenuImageView.setImageResource(R.drawable.left_menu_open);
		marginView.setVisibility(View.GONE);
	}

	public interface OnMenuItemClickListener {
		public void onMenuItemClick(int position);
	}

	private OnMenuItemClickListener onMenuItemClickListener;

	public void setOnMenuItemClickListener(OnMenuItemClickListener onMenuItemClickListener) {
		this.onMenuItemClickListener = onMenuItemClickListener;
	}
}
