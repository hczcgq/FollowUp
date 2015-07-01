package com.shbestwin.followupmanager.view.adapter.archive;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.shbestwin.followupmanager.manager.ArchiveInfoManager;
import com.shbestwin.followupmanager.model.ArchiveInfo;
import com.shbestwin.followupmanager.view.adapter.ArrayListAdapter;

//AutoCompleteTextView的适配器除了继承BaseAdapter外，还要实现Filterable接口。
//Filterable接口中有个getFilter方法，用于获取过滤器，我们需要自己写个继承Filter的过滤器，实现数据库查询。 
public class ArchiveInfoQueryAdapter extends ArrayListAdapter<ArchiveInfo> implements Filterable {
	public static final int QUERY_BY_NAME = 0x001;// 姓名查询
	public static final int QUERY_BY_IDCARD = 0x002;// 身份证号码查询
	public static final int QUERY_BY_CARDNO = 0x003;// 卡号查询

	private int query;

	private ArchiveInfoFilter archiveInfoFilter;

	public ArchiveInfoQueryAdapter(Context context, int query) {
		super(context);
		this.query = query;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		TextView item = null;
		if (convertView == null) {
			item = (TextView) mInflater.inflate(android.R.layout.simple_dropdown_item_1line, parent, false);
		} else {
			item = (TextView) convertView;
		}

		ArchiveInfo archiveInfo = getItem(position);
		switch (query) {
		case QUERY_BY_NAME:
			item.setText(archiveInfo.getName());
			break;
		case QUERY_BY_IDCARD:
			item.setText(archiveInfo.getIdcard());
			break;
		case QUERY_BY_CARDNO:
			item.setText(archiveInfo.getCardNo());
			break;
		}

		return item;
	}

	@Override
	public Filter getFilter() {
		if (archiveInfoFilter == null) {
			archiveInfoFilter = new ArchiveInfoFilter();
		}
		return archiveInfoFilter;
	}

	/**
	 * 
	 * @ClassName: ArchiveInfoFilter
	 * @Description: 档案信息数据查询过滤器
	 * @author junbin.he
	 * @date 2015年3月12日 上午10:27:17
	 *
	 */
	private class ArchiveInfoFilter extends Filter {

		/**
		 * 查询数据（当前为数据库查询数据）
		 */
		@Override
		protected FilterResults performFiltering(CharSequence constraint) {
			// 查询结果保存到FilterResults对象里
			FilterResults results = new FilterResults();
			String name = "";
			String idcard = "";
			String cardNo = "";
			switch (query) {
			case QUERY_BY_NAME:
				name = constraint.toString();
				break;
			case QUERY_BY_IDCARD:
				idcard = constraint.toString();
				break;
			case QUERY_BY_CARDNO:
				cardNo = constraint.toString();
				break;
			}
			List<ArchiveInfo> archiveInfoList = ArchiveInfoManager.getInstance(getContext()).getArchiveInfoList(name, idcard, cardNo);
			results.values = archiveInfoList;
			results.count = archiveInfoList.size();
			return results;
		}

		/**
		 * 更新UI
		 */
		@SuppressWarnings("unchecked")
		@Override
		protected void publishResults(CharSequence constraint, FilterResults results) {
			List<ArchiveInfo> archiveInfoList = (List<ArchiveInfo>) results.values;
			// 把结果读取出复制到users里
			setList(archiveInfoList);
		}

	}
}
