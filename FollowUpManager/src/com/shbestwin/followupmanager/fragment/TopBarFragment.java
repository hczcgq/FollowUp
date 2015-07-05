package com.shbestwin.followupmanager.fragment;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.activity.LoginActivity;
import com.shbestwin.followupmanager.activity.SearchActivity;
import com.shbestwin.followupmanager.activity.SettingActivity;
import com.shbestwin.followupmanager.view.widget.TopTabItemView;

/**
 * 
 * @ClassName: TopBarFragment
 * @Description: 顶部导航
 * 
 */
public class TopBarFragment extends BaseFragment implements
		View.OnClickListener {
	// R.string.top_tab_item1_title,
	private static final int[] tabItemTitleResIds = {
			R.string.top_tab_item3_title, R.string.top_tab_item4_title,
			R.string.top_tab_item5_title, R.string.top_tab_item2_title,
			R.string.top_tab_item6_title };
	// R.drawable.top_tab_menu1_icon_normal,
	private static final int[] tabItemNormalResIds = {
			R.drawable.top_tab_menu3_icon_normal,
			R.drawable.top_tab_menu4_icon_normal,
			R.drawable.top_tab_menu5_icon_normal,
			R.drawable.top_tab_menu2_icon_normal,
			R.drawable.top_tab_menu6_icon_normal };
	// R.drawable.top_tab_menu1_icon_active,
	private static final int[] tabItemSelectedResIds = {
			R.drawable.top_tab_menu3_icon_active,
			R.drawable.top_tab_menu4_icon_active,
			R.drawable.top_tab_menu5_icon_active,
			R.drawable.top_tab_menu2_icon_active,
			R.drawable.top_tab_menu6_icon_active };

	private LinearLayout llTab;
	private View tabItemBg;
	private TextView loginNameTextView;

	private int tabSelectedIndex = 0;
	private int topTabItemWidth;
	private ValueAnimator tabMoveAnim;

	private View logoutButton, searchButton, setttingButton;

	private SharedPreferences preferences;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		onTabSelectedListener = (OnTabSelectedListener) activity;
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View root = inflater.inflate(R.layout.fragment_top_bar, container,
				false);
		preferences = getActivity().getSharedPreferences("USER_INFO",
				Activity.MODE_PRIVATE);

		llTab = (LinearLayout) root.findViewById(R.id.llTab);
		tabItemBg = root.findViewById(R.id.tabItemBg);

		initTab();

		loginNameTextView = (TextView) root
				.findViewById(R.id.loginNameTextView);
		logoutButton = root.findViewById(R.id.logoutButton);
		searchButton = root.findViewById(R.id.searchButton);
		setttingButton = root.findViewById(R.id.setttingButton);

		return root;
	}

	private void initTab() {
		topTabItemWidth = (int) getResources().getDimension(
				R.dimen.top_tab_item_width);
		for (int i = 0; i < tabItemTitleResIds.length; i++) {
			LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(0,
					LinearLayout.LayoutParams.MATCH_PARENT);
			lp.weight = 1;

			TopTabItemView itemView = new TopTabItemView(getActivity(),
					new TopTabItemView.TopTabItem(tabItemTitleResIds[i],
							tabItemNormalResIds[i], tabItemSelectedResIds[i],
							i, i == 0 ? true : false));
			itemView.setOnClickListener(this);
			llTab.addView(itemView, lp);
		}
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		if (null != onTabSelectedListener) {
			onTabSelectedListener.onTabSelected(0);
		}

		String username = preferences.getString("Username", "");
		loginNameTextView.setText(username);

		logoutButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				LoginActivity.launch(getActivity());
			}
		});
		searchButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				SearchActivity.launch(getActivity());
			}
		});
		setttingButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				SettingActivity.launch(getActivity());
			}
		});
	}

	@Override
	public void onClick(View v) {
		int index = (Integer) v.getTag();
		if (this.tabSelectedIndex == index) {
			return;
		}
		startTabItemBgAnim(index);

		if (null != onTabSelectedListener) {
			onTabSelectedListener.onTabSelected(index);
		}
	}

	private void startTabItemBgAnim(final int index) {
		int cur = topTabItemWidth * index;
		int pre;

		if (tabMoveAnim != null) {
			pre = (Integer) tabMoveAnim.getAnimatedValue();
			tabMoveAnim.cancel();
		} else {
			pre = topTabItemWidth * this.tabSelectedIndex;
		}

		tabMoveAnim = ValueAnimator.ofInt(pre, cur);

		tabMoveAnim
				.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
					@Override
					public void onAnimationUpdate(final ValueAnimator animation) {
						ViewCompat.postOnAnimation(llTab, new Runnable() {
							@Override
							public void run() {
								RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) tabItemBg
										.getLayoutParams();
								lp.leftMargin = (Integer) animation
										.getAnimatedValue();
								tabItemBg.setLayoutParams(lp);
							}
						});

					}
				});
		tabMoveAnim.addListener(new Animator.AnimatorListener() {
			@Override
			public void onAnimationStart(Animator animation) {
				TopTabItemView preTabItemView = (TopTabItemView) llTab
						.getChildAt(tabSelectedIndex);
				preTabItemView.setSelected(false);
				tabSelectedIndex = index;
			}

			@Override
			public void onAnimationRepeat(Animator animation) {

			}

			@Override
			public void onAnimationEnd(Animator animation) {
				TopTabItemView curTabItemView = (TopTabItemView) llTab
						.getChildAt(index);
				curTabItemView.setSelected(true);
			}

			@Override
			public void onAnimationCancel(Animator animation) {
			}
		});
		tabMoveAnim.setDuration(250);
		tabMoveAnim.setInterpolator(new AccelerateInterpolator());
		tabMoveAnim.start();
	}

	private OnTabSelectedListener onTabSelectedListener;

	public interface OnTabSelectedListener {

		public void onTabSelected(int index);
	}
}
