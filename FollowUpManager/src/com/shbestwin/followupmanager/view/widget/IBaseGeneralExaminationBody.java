package com.shbestwin.followupmanager.view.widget;

import com.shbestwin.followupmanager.model.examination.GeneralExamination;

public interface IBaseGeneralExaminationBody {

	/**
	 * 
	 * @Title: getData
	 * @Description: 保存数据时获取到页面数据
	 * @param @param generalExamination
	 * @return void
	 * @throws
	 */
	public void getData(GeneralExamination generalExamination);

	/**
	 * 
	 * @Title: setData
	 * @Description: 初始化数据，默认加载显示数据
	 * @param @param generalExamination
	 * @return void
	 * @throws
	 */
	public void setData(GeneralExamination generalExamination);

	/**
	 * 
	 * @Title: validate
	 * @Description: 验证
	 * @param @return
	 * @return boolean
	 * @throws
	 */
	public boolean validate();
}
