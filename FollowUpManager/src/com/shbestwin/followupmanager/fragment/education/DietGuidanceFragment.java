package com.shbestwin.followupmanager.fragment.education;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.fragment.BaseFragment;

/**
 * 
 * 膳食指导
 *
 * @version
 */
public class DietGuidanceFragment extends BaseFragment {

	private ListView leftMenuListView;
	private ViewPager contentViewPager;

	public static DietGuidanceFragment newInstance() {
		DietGuidanceFragment fragment = new DietGuidanceFragment();
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_education_diet_guidance, container, false);
		leftMenuListView = (ListView) rootView.findViewById(R.id.leftMenuListView);
		contentViewPager = (ViewPager) rootView.findViewById(R.id.contentViewPager);
		initLeftMenu();
		initContentView();
		return rootView;
	}

	private void initLeftMenu() {
		String menuItems[] = getResources().getStringArray(R.array.DietGuidanceMenuItem);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.view_education_diet_guide_menu_item, menuItems);
		leftMenuListView.setAdapter(adapter);
		leftMenuListView.setItemChecked(0, true);
		leftMenuListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				contentViewPager.setCurrentItem(position);
			}
		});
	}

	private ArrayList<View> contentViewList = null;
	private ContentViewPagerAdapter contentViewPagerAdapter;
	private int[] contentImages = { R.drawable.education_diet_guide_01, R.drawable.education_diet_guide_02, R.drawable.education_diet_guide_03, R.drawable.education_diet_guide_04, R.drawable.education_diet_guide_05,
			R.drawable.education_diet_guide_06, R.drawable.education_diet_guide_07, R.drawable.education_diet_guide_08, R.drawable.education_diet_guide_09 };

	private void initContentView() {
		contentViewList = new ArrayList<View>();
		contentViewList.add(View.inflate(getActivity(), R.layout.view_education_diet_guide_content_item, null));
		contentViewList.add(View.inflate(getActivity(), R.layout.view_education_diet_guide_content_item, null));
		contentViewList.add(View.inflate(getActivity(), R.layout.view_education_diet_guide_content_item, null));
		contentViewList.add(View.inflate(getActivity(), R.layout.view_education_diet_guide_content_item, null));
		contentViewList.add(View.inflate(getActivity(), R.layout.view_education_diet_guide_content_item, null));
		contentViewList.add(View.inflate(getActivity(), R.layout.view_education_diet_guide_content_item, null));
		contentViewList.add(View.inflate(getActivity(), R.layout.view_education_diet_guide_content_item, null));
		contentViewList.add(View.inflate(getActivity(), R.layout.view_education_diet_guide_content_item, null));
		contentViewList.add(View.inflate(getActivity(), R.layout.view_education_diet_guide_content_item, null));

		for (int i = 0; i < contentViewList.size(); i++) {
			((ImageView) contentViewList.get(i)).setImageResource(contentImages[i]);
		}

		contentViewPagerAdapter = new ContentViewPagerAdapter();
		contentViewPager.setAdapter(contentViewPagerAdapter);

		contentViewPager.setOnPageChangeListener(new OnPageChangeListener() {
			@Override
			public void onPageSelected(int position) {
				leftMenuListView.setItemChecked(position, true);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {

			}
		});
	}

	private class ContentViewPagerAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			return contentViewList.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public void destroyItem(View container, int position, Object object) {
			((ViewPager) container).removeView(contentViewList.get(position));

		}

		@Override
		public Object instantiateItem(View container, int position) {
			((ViewPager) container).addView(contentViewList.get(position), 0);
			return contentViewList.get(position);
		}
	}
}
