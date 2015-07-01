package com.shbestwin.followupmanager.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.shbestwin.followupmanager.MyApplication;
import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.common.util.DateUtils;
import com.shbestwin.followupmanager.model.ArchiveInfo;

public class BaseIDCardInfoFragment extends BaseFragment {
	private ImageView avatarImageView;
	private TextView nameTextView, gendarTextView, ageTextView;
	private TextView idcardTextView, archiveNoTextView, cardNoTextView;

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		avatarImageView = (ImageView) view.findViewById(R.id.avatarImageView);
		nameTextView = (TextView) view.findViewById(R.id.nameTextView);
		gendarTextView = (TextView) view.findViewById(R.id.gendarTextView);
		ageTextView = (TextView) view.findViewById(R.id.ageTextView);
		idcardTextView = (TextView) view.findViewById(R.id.idcardTextView);
		archiveNoTextView = (TextView) view.findViewById(R.id.archiveNoTextView);
		cardNoTextView = (TextView) view.findViewById(R.id.cardNoTextView);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		onRenderPage();
	}

	@Override
	public void onRenderPage() {
		super.onRenderPage();
		ArchiveInfo archiveInfo = MyApplication.getInstance().getArchiveInfo();
		System.out.println(archiveInfo.getName()+"-----");
		if (archiveInfo != null) {
			nameTextView.setText(archiveInfo.getName());
			idcardTextView.setText(archiveInfo.getIdcard());
			if ("男".equals(archiveInfo.getGender())) {
				gendarTextView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.icon_male, 0, 0, 0);
			} else if ("女".equals(archiveInfo.getGender())) {
				gendarTextView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.icon_female, 0, 0, 0);
			}
			gendarTextView.setText(archiveInfo.getGender());
			ageTextView.setText(DateUtils.getAgeByBirthday(archiveInfo.getBirthday()) + "岁");
			avatarImageView.setImageBitmap(archiveInfo.getPicture());

			cardNoTextView.setText(archiveInfo.getCardNo());
			archiveNoTextView.setText(archiveInfo.getArchiveNo());
		}
	}
}
