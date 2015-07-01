package com.shbestwin.followupmanager.view.adapter;

import java.util.Collections;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.shbestwin.followupmanager.common.util.CollectionUtils;


/**
 * 集合适配器，要求元素实现Comparable
 * 此适配器适合需要监听数据更新的情况
 *
 * @param <T>
 * @author junbin.he
 * @version V1.0
 * @date 2014-4-2 上午9:12:31
 */
public abstract class ArrayListAdapterSort<T extends Comparable<? super T>> extends BaseAdapter {
    protected Context mContext;
    protected List<T> mList;// 集合数据
    protected LayoutInflater mInflater;
    public ArrayListAdapterSort(Context context) {
        this.mContext = context;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public ArrayListAdapterSort(Context context, List<T> list) {
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
     * 如果数据存在，则替换原先的数据，如果不存在则增加数据
     * 对象是否相等是通过equals方法比较的，根据业务逻辑，重写equals方法
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
        Collections.sort(mList);//将数据进行排序
        notifyDataSetChanged();
    }

    /**
     * 删除集合中的数据
     * 对象是否相等是通过equals方法比较的，根据业务逻辑，重写equals方法
     *
     * @param list
     */
    public void removeList(List<T> list) {
        if (CollectionUtils.isEmpty(list) || CollectionUtils.isEmpty(mList)) {
            return;
        }
        mList.removeAll(list);//删除不会改变次序，所以无需重新排序
        notifyDataSetChanged();
    }

    /**
     * 设置集合元素
     *
     * @param list
     */
    public void setList(List<T> list) {
        this.mList = list;
        notifyDataSetInvalidated();
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
