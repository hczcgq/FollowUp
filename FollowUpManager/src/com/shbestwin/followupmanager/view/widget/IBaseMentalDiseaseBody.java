package com.shbestwin.followupmanager.view.widget;

import android.support.v4.app.FragmentManager;

import com.shbestwin.followupmanager.model.followup.FollowUpMentalDisease;

public interface IBaseMentalDiseaseBody {
	/**
	 * 
	 * @Title: getData
	 * @Description: 保存数据时获取到页面数据
	 * @param @param followUpMentalDisease
	 * @return void
	 * @throws
	 */
	public void getData(FollowUpMentalDisease followUpMentalDisease);

	/**
	 * 
	 * @Title: setData
	 * @Description: 初始化数据，默认加载显示数据
	 * @param @param followUpMentalDisease
	 * @return void
	 * @throws
	 */
	public void setData(FollowUpMentalDisease followUpMentalDisease);

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
