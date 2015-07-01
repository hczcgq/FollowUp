package com.shbestwin.followupmanager.view.adapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.common.util.ArrayUtils;
import com.shbestwin.followupmanager.common.util.CollectionUtils;

/**
 * 数组适配器
 *
 * @param <T>
 * @author junbin.he
 * @version V1.0
 * @date 2014-4-2 上午9:12:31
 */
public abstract class ArrayListAdapter<T> extends BaseAdapter {
	protected Context mContext;
	protected List<T> mList;// 集合数据
	protected LayoutInflater mInflater;
	protected int cardinalBg, evenBg;

	public ArrayListAdapter(Context context) {
		this.mContext = context;
		mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		cardinalBg = mContext.getResources().getColor(R.color.global_list_cardinal_bg);
		evenBg = mContext.getResources().getColor(R.color.global_list_even_bg);
	}

	public ArrayListAdapter(Context context, List<T> list) {
		this(context);
		this.mList = list;
	}

	@Override
	public int getCount() {
		return mList == null ? 0 : mList.size();
	}

	@Override
	public T getItem(int position) {
		return mList == null ? null : mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	/**
	 * 如果数据存在，则替换原先的数据，如果不存在则增加数据 对象是否相等是通过equals方法比较的，根据业务逻辑，重写equals方法
	 *
	 * @param list
	 */
	public void replaceList(List<T> list) {
		if (CollectionUtils.isEmpty(list)) {
			return;
		}
		if (CollectionUtils.isEmpty(mList)) {
			this.mList = list;
		} else {
			for (T t : list) {
				if (mList.contains(t)) {
					mList.remove(t);
				}
				mList.add(t);
			}
		}
		notifyDataSetChanged();
	}

	/**
	 * 删除集合中的数据 对象是否相等是通过equals方法比较的，根据业务逻辑，重写equals方法
	 *
	 * @param list
	 */
	public void removeList(List<T> list) {
		if (CollectionUtils.isEmpty(list) || CollectionUtils.isEmpty(mList)) {
			return;
		}
		mList.removeAll(list);
		notifyDataSetChanged();
	}

	/**
	 * 设置集合元素
	 *
	 * @param list
	 */
	public void setList(List<T> list) {
		this.mList = list;
		notifyDataSetChanged();
	}

	/**
	 * 设置集合元素
	 * 
	 * @param list
	 *            数据集合
	 * @param isDataInvalidate
	 */
	public void setList(List<T> list, boolean isDataInvalidate) {
		this.mList = list;
		if (isDataInvalidate) {
			notifyDataSetInvalidated();
		} else {
			notifyDataSetChanged();
		}
	}

	/**
	 * 设置数组元素
	 *
	 * @param array
	 */
	public void setArray(T[] array) {
		mList = ArrayUtils.isEmpty(array) ? null : new ArrayList<T>(Arrays.asList(array));
		notifyDataSetChanged();
	}

	/**
	 * 设置数组元素
	 * 
	 * @param array
	 * @param isDataInvalidate
	 */
	public void setArray(T[] array, boolean isDataInvalidate) {
		mList = ArrayUtils.isEmpty(array) ? null : new ArrayList<T>(Arrays.asList(array));
		if (isDataInvalidate) {

			notifyDataSetInvalidated();
		} else {
			notifyDataSetChanged();
		}
	}

	/**
	 * 追加集合元素
	 *
	 * @param list
	 */
	public void appendList(List<T> list) {
		if (CollectionUtils.isEmpty(list)) {// 如果追加的数据为空，则没有必要进行追加数据和刷新页面
			return;
		}
		if (CollectionUtils.isEmpty(mList)) {
			this.mList = list;
		} else {
			this.mList.addAll(list);
		}
		notifyDataSetChanged();
	}

	/**
	 * 追加数组元素
	 *
	 * @param array
	 */
	public void appendArray(T[] array) {
		if (ArrayUtils.isEmpty(array)) {// 如果追加的数据为空，则没有必要进行追加数据和刷新页面
			return;
		}

		if (CollectionUtils.isEmpty(mList)) {
			mList = new ArrayList<T>(Arrays.asList(array));
		} else {
			mList.addAll(Arrays.asList(array));
		}
		notifyDataSetChanged();
	}

	/**
	 * 清除所有元素
	 */
	public void clear() {
		setList(null);
	}

	@Override
	abstract public View getView(int position, View convertView, ViewGroup parent);

	public List<T> getList() {
		return mList;
	}

	public Context getContext() {
		return mContext;
	}

}
