package com.shbestwin.followupmanager.view.dialog;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.Html;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.common.util.SystemUtils;

/**
 * 
 * @ClassName: BaseDialogFragment
 * @Description: 公共对话框
 *
 */
public class BaseDialogReportFragment extends DialogFragment {
	private ImageView closeImageView;
	private FrameLayout bodyLayout;

	public static BaseDialogReportFragment newInstance(int messageResId) {
		BaseDialogReportFragment fragment = new BaseDialogReportFragment();
		Bundle args = new Bundle();
		args.putInt("messageResId", messageResId);
		fragment.setArguments(args);
		return fragment;
	}

	public static BaseDialogReportFragment newInstance(String message) {
		BaseDialogReportFragment fragment = new BaseDialogReportFragment();
		Bundle args = new Bundle();
		args.putString("message", message);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setStyle(DialogFragment.STYLE_NO_TITLE, 0);
	}

	protected View getBodyView(LayoutInflater inflater) {
		return null;
	}

	private TextView createDefaultBodyView() {
		TextView textView = new TextView(getActivity());
		textView.setLineSpacing(0.0F, 1.2F);
		textView.setTextColor(getResources().getColor(R.color.black));
		textView.setTextSize(16);
		return textView;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_base_report_dialog, container, false);
		closeImageView = (ImageView) rootView.findViewById(R.id.closeImageView);
		closeImageView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				hide();
			}
		});

		bodyLayout = (FrameLayout) rootView.findViewById(R.id.bodyLayout);

		View bodyView = getBodyView(inflater);
		if (bodyView != null) {
			bodyLayout.addView(bodyView, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		} else {
			if (getArguments() != null) {
				int messageResId = getArguments().getInt("messageResId", 0);
				if (messageResId > 0) {
					TextView textView = createDefaultBodyView();
					textView.setText(Html.fromHtml(getString(messageResId)));
					bodyLayout.addView(textView, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
				} else {
					String message = getArguments().getString("message", null);
					if (!TextUtils.isEmpty(message)) {
						TextView textView = createDefaultBodyView();
						textView.setText(Html.fromHtml(message));
						bodyLayout.addView(textView, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
					}
				}
			}
		}
		return rootView;
	}

	public void hide() {
		SystemUtils.hiddenIME(bodyLayout);
		dismissAllowingStateLoss();
	}

	protected int getWidth() {
		return 0;
	}

	@Override
	public void onResume() {
		super.onResume();

		int width = getWidth();
		int height = 0;
		if (width <= 0) {
			DisplayMetrics dm = new DisplayMetrics();
			getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
			width = (int) (dm.widthPixels * 0.85);
			height=(int)(dm.heightPixels*0.9);
			
		}

		Window window = getDialog().getWindow();
		window.setGravity(Gravity.CENTER);
		window.setBackgroundDrawableResource(R.drawable.dialog_bg);
		window.setLayout(width, height);
		getDialog().setCanceledOnTouchOutside(false);
	}
}
