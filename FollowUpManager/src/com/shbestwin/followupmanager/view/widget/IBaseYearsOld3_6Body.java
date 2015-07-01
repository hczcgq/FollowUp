package com.shbestwin.followupmanager.view.widget;

import android.support.v4.app.FragmentManager;

import com.shbestwin.followupmanager.model.followup.FollowUpThreeSixNewborn;

public interface IBaseYearsOld3_6Body {
	/**
	 * 
	 * @Title: getData
	 * @Description: 保存数据时获取到页面数据
	 * @param @param followUpThreeSixNewborn
	 * @return void
	 * @throws
	 */
	public void getData(FollowUpThreeSixNewborn followUpThreeSixNewborn);

	/**
	 * 
	 * @Title: setData
	 * @Description: 初始化数据，默认加载显示数据
	 * @param @param followUpThreeSixNewborn
	 * @return void
	 * @throws
	 */
	public void setData(FollowUpThreeSixNewborn followUpThreeSixNewborn);

	/**
	 * 
	 * @Title: validate
	 * @Description: 验证
	 * @param @return
	 * @return boolean
	 * @throws
	 */
	public boolean validate();
	
	public void setFragment(FragmentManager fragmentManager);
}
