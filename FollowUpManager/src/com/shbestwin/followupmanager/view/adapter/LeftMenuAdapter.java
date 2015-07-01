package com.shbestwin.followupmanager.view.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.model.MenuItem;

/**
 * 
 * @ClassName: LeftMenuAdapter
 * @Description: 左边导航菜单适配器
 * @author junbin.he
 * @date 2015年1月16日 上午11:18:34
 *
 */
public class LeftMenuAdapter extends ArrayListAdapter<MenuItem> {

	public LeftMenuAdapter(Context context, List<MenuItem> list) {
		super(context, list);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.view_left_menu_item, parent, false);
			viewHolder = new ViewHolder();
			viewHolder.menuItemTextView = (TextView) convertView.findViewById(R.id.menuItemTextView);
			viewHolder.menuIconTextView = (TextView) convertView.findViewById(R.id.menuIconTextView);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}

		MenuItem menuItem = getItem(position);
		viewHolder.menuItemTextView.setText(menuItem.getTitle());
		if (menuItem.getIconResId() > 0) {
			viewHolder.menuIconTextView.setBackgroundResource(menuItem.getIconResId());
		}
		return convertView;
	}

	private static class ViewHolder {
		private TextView menuItemTextView;
		private TextView menuIconTextView;
	}
}
