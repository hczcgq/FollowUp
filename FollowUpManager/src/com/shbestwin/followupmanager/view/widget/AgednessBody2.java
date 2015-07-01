package com.shbestwin.followupmanager.view.widget;

import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.CompoundButton.OnCheckedChangeListener;
import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.common.util.JsonUtil;
import com.shbestwin.followupmanager.common.util.ViewDataUtil;
import com.shbestwin.followupmanager.model.followup.Adedness;
import com.shbestwin.followupmanager.model.followup.FollowUpAged;

public class AgednessBody2 extends LinearLayout  implements IBaseAgednessBody{
	
	private RelativeLayout symptomLayout;
	private CheckBox symptomNone,symptomOther;
	private EditText symptomEdittext;
	
	public AgednessBody2(Context context) {
		this(context, null);
	}

	public AgednessBody2(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public AgednessBody2(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		View rootView = LayoutInflater.from(context).inflate(R.layout.view_agedness_body2, this, true);
		symptomLayout=(RelativeLayout) rootView.findViewById(R.id.symptomLayout);
		symptomNone=(CheckBox) rootView.findViewById(R.id.symptomNone);
		symptomOther=(CheckBox) rootView.findViewById(R.id.symptomOther);
		symptomEdittext=(EditText) rootView.findViewById(R.id.symptomEdittext);
		ViewDataUtil.initOtherCheckboxConstraint(symptomOther,
				symptomEdittext);

		symptomNone
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						setCheckBoxStatus(symptomLayout, isChecked);
					}
				});
		
	}
	@Override
	public void getData(FollowUpAged followUpAged) {
		followUpAged.setZz(getCheckBoxText());
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setData(FollowUpAged followUpAged) {
		if(followUpAged!=null){
			  try {
				setCheckBoxText(symptomLayout, symptomEdittext,  JsonUtil.jsonToObjects(
				          followUpAged.getZz(),
				          Adedness.class));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public boolean validate() {
		return true;
	}

	@Override
	public void setFragment(FragmentManager fragmentManager) {
		
	}
	
	private String getCheckBoxText() {
		List<Adedness> mArrayList = new ArrayList<Adedness>();
        for (int i = 0; i < symptomLayout.getChildCount(); i++) {
            View item = symptomLayout.getChildAt(i);
            if (item instanceof CheckBox) {
                CheckBox checkBox = (CheckBox) item;
                if (checkBox.isChecked()) {
                	Adedness entity = new Adedness();
                    entity.setZz_num(String.valueOf(i));
                    if ("其他".equals(checkBox.getText())
                            && symptomEdittext != null) {
                        entity.setZz_name(symptomEdittext
                                .getText().toString());
                    } else {
                        entity.setZz_name(checkBox.getText().toString());
                    }
                    mArrayList.add(entity);
                }
            }
        }
        try {
            return JsonUtil.objectsToJson(mArrayList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
	
	 private void setCheckBoxText(View layout, EditText textview, List<Adedness> mList) {
	        RelativeLayout myLayout = (RelativeLayout) layout;
	        for (int i = 0; i < mList.size(); i++) {
	            int Num=Integer.parseInt(mList.get(i).getZz_num());
	            String name=mList.get(i).getZz_name();
	           
	            if ((View) (myLayout).getChildAt(Num) instanceof CheckBox) {
	                CheckBox checkBox = (CheckBox) (View) (myLayout)
	                        .getChildAt(Num);
	                checkBox.setChecked(true);
	                if (Num ==  symptomLayout.getChildCount() - 2) {
	                	symptomEdittext.setText(name);
	                }
	            }
	        }
	    }
	
	
	private void setCheckBoxStatus(RelativeLayout familyHistory,
			boolean isChecked) {
		for (int i = 1; i < familyHistory.getChildCount(); i++) {
			View item = familyHistory.getChildAt(i);
			if (item instanceof CheckBox) {
				((CheckBox) item).setEnabled(!isChecked);
				if (isChecked) {
					((CheckBox) item).setChecked(!isChecked);
				}
			}
		}
	}

}
