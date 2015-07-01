package com.shbestwin.followupmanager.view.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.shbestwin.followupmanager.R;

/**
 * Created by hejunbin on 14-8-18.
 */
public class TopTabItemView extends FrameLayout {
    private Context mContext;
    private TopTabItem mTopTabItem;

    private TextView mTvTabItem;

    public TopTabItemView(Context context, TopTabItem topTabItem) {
        super(context);
        this.mContext = context;
        this.mTopTabItem = topTabItem;
        init();
    }

    private void init() {
        this.setTag(mTopTabItem.getTag());
        View root = LayoutInflater.from(mContext).inflate(R.layout.view_top_tab_item, this, true);
        mTvTabItem = (TextView) root.findViewById(R.id.tvTabItem);
        mTvTabItem.setText(mTopTabItem.getTitleResId());
        setTabItemStyle();
    }

    private void setTabItemStyle() {
        mTvTabItem.setCompoundDrawablesWithIntrinsicBounds(0, mTopTabItem.isSelected ? mTopTabItem.getSelectedResId() : mTopTabItem.getNormalResId(), 0, 0);
		mTvTabItem.setTextColor(mTopTabItem.isSelected() ? getResources().getColor(R.color.top_bar_item_text_selected) : getResources().getColor(R.color.top_bar_item_text_normal));
    }


    public void setSelected(boolean selected) {
        if (selected == mTopTabItem.isSelected) {
            return;
        }
        mTopTabItem.setSelected(selected);
        setTabItemStyle();

    }

    public static class TopTabItem {
        private int titleResId;
        private int normalResId;
        private int selectedResId;
        private int tag;
        private boolean isSelected;



        public TopTabItem(int titleResId, int normalResId, int selectedResId, int tag, boolean isSelected) {
            this.titleResId = titleResId;
            this.normalResId = normalResId;
            this.selectedResId = selectedResId;
            this.tag = tag;
            this.isSelected = isSelected;
        }

        public int getTitleResId() {
            return titleResId;
        }

        public void setTitleResId(int titleResId) {
            this.titleResId = titleResId;
        }

        public int getNormalResId() {
            return normalResId;
        }

        public void setNormalResId(int normalResId) {
            this.normalResId = normalResId;
        }

        public int getSelectedResId() {
            return selectedResId;
        }

        public void setSelectedResId(int selectedResId) {
            this.selectedResId = selectedResId;
        }

        public int getTag() {
            return tag;
        }

        public void setTag(int tag) {
            this.tag = tag;
        }

        public boolean isSelected() {
            return isSelected;
        }

        public void setSelected(boolean isSelected) {
            this.isSelected = isSelected;
        }
    }

}
