package com.shbestwin.followupmanager.common.util;

import java.util.ArrayList;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Spinner;

/**
 * 
 * @ClassName: ViewDataUtil
 * @Description: 视图数据工具类
 * @date 2015年4月27日 上午11:47:27
 *
 */
public class ViewDataUtil {

	/**
	 * 
	 * @Title: initOtherCheckboxConstraint
	 * @Description: 初始化带其他的Checkbox的约束
	 * @param @param otherCheckBox
	 * @param @param otherEditText
	 * @return void
	 * @throws
	 */
	public static void initOtherCheckboxConstraint(CheckBox otherCheckBox, final EditText otherEditText) {
		otherEditText.setEnabled(false);
		otherCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				otherEditText.setEnabled(isChecked);
				if (!isChecked) {
					otherEditText.setText("");
				}
			}
		});
	}

	public static boolean validateOtherCheckbox(CheckBox otherCheckBox,EditText otherEditText) {
		if (otherCheckBox.isChecked() && !TextUtils.isEmpty(otherEditText.getText().toString())) {
			return false;
		}
		return true;
	}
	
	/**
	 * 
	 * @Title: getCheckboxData
	 * @Description: 获取checkbox的数据
	 * @param @param checkboxLayout,checkbox布局
	 * @param @param otherEditText，含有其他的输入框，没有为null
	 * @param @return
	 * @return String
	 * @throws
	 */
	public static String getCheckboxData(ViewGroup checkboxLayout, EditText otherEditText) {
		StringBuilder negativeEvent = new StringBuilder();
		for (int i = 0; i < checkboxLayout.getChildCount(); i++) {
			View item = checkboxLayout.getChildAt(i);
			if (item instanceof CheckBox) {
				CheckBox itemCheckBox = (CheckBox) item;
				if (itemCheckBox.isChecked()) {
					if ("其他".equals(itemCheckBox.getText()) && otherEditText != null) {
						negativeEvent.append("#" + otherEditText.getText().toString() + "#");
					} else {
						negativeEvent.append(itemCheckBox.getText());
					}
					negativeEvent.append(";");
				}
			}
		}
		return negativeEvent.toString();
	}

	public static void setCheckboxData(ViewGroup checkboxLayout, EditText otherEditText, String text) {

		ArrayList<CheckBox> itemCheckBoxs = new ArrayList<CheckBox>();
		for (int i = 0; i < checkboxLayout.getChildCount(); i++) {
			View item = checkboxLayout.getChildAt(i);
			if (item instanceof CheckBox) {
				itemCheckBoxs.add((CheckBox) item);
			}
		}

		String selectedItems[] = null;
		int selectedCount = -1;
		if (!TextUtils.isEmpty(text)) {
			selectedItems = text.split(";");
		}

		String selectedItemText = "";
		int selectedItemIndex = -1;
		if (!ArrayUtils.isEmpty(selectedItems)) {
			selectedItemText = selectedItems[0];
			selectedItemIndex = 0;
			selectedCount = selectedItems.length;
		}

		for (CheckBox itemCheckBox : itemCheckBoxs) {
			String itemText = itemCheckBox.getText().toString();
			if (TextUtils.isEmpty(selectedItemText)) {
				itemCheckBox.setChecked(false);
			} else {
				// 是否是其他选项的值
				if (otherEditText != null && "其他".equals(itemText) && selectedItemText.contains("#")) {
					itemCheckBox.setChecked(true);
					otherEditText.setText(text.substring(text.indexOf("#") + 1, text.indexOf("#;")));
					selectedItemText = "";
				} else if (selectedItemText.equals(itemText)) {
					itemCheckBox.setChecked(true);
					if (++selectedItemIndex < selectedCount) {
						selectedItemText = selectedItems[selectedItemIndex];
					} else {
						selectedItemText = "";
					}
				} else {
					itemCheckBox.setChecked(false);
				}
			}
		}
	}

	public static void resetCheckboxData(ViewGroup checkboxLayout, EditText otherEditText) {
		for (int i = 0; i < checkboxLayout.getChildCount(); i++) {
			View item = checkboxLayout.getChildAt(i);
			if (item instanceof CheckBox) {
				CheckBox itemCheckBox = (CheckBox) item;
				if (itemCheckBox.isChecked()) {
					itemCheckBox.setChecked(false);
				}
			}
		}
		otherEditText.setText("");
	}

	public static void setSpinnerData(Spinner spinner, String text) {
		for (int i = 0; i < spinner.getAdapter().getCount(); i++) {
			String item = spinner.getAdapter().getItem(i).toString();
			if (item.equals(text)) {
				spinner.setSelection(i);
				break;
			}
		}
	}

	public static String getSpinnerData(Spinner spinner) {
		return spinner.getSelectedItem().toString();
	}
}
