package com.shbestwin.followupmanager.manager;

import java.util.Map;
import java.util.Set;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build;
import android.preference.PreferenceManager;
import android.text.TextUtils;

/**
 * 首选项管理类
 * 
 * @author junbin.he
 * @date 2014-10-11 下午2:05:31
 * @version V1.0
 * 
 */
public class PreferencesManager {
	private Context mContext = null;
	private String mFileName;
	private SharedPreferences mPreferences;
	private Editor mEditor;

	public PreferencesManager(Context context) {
		this(context, null);
	}

	public PreferencesManager(Context context, String fileName) {
		this.mContext = context.getApplicationContext();
		this.mFileName = fileName;
		init();
	}

	private void init() {
		if (TextUtils.isEmpty(mFileName)) {
			mPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
		} else {
			mPreferences = mContext.getSharedPreferences(mFileName, Context.MODE_PRIVATE);
		}

		mEditor = mPreferences.edit();
	}

	public boolean putString(String key, String value) {
		mEditor.putString(key, value);
		return mEditor.commit();
	}

	public String getString(String key, String defValue) {
		return mPreferences.getString(key, defValue);
	}

	public String getString(String key) {
		return mPreferences.getString(key, "");
	}

	public boolean putBoolean(String key, boolean value) {
		mEditor.putBoolean(key, value);
		return mEditor.commit();
	}

	public boolean getBoolean(String key, boolean defValue) {
		return mPreferences.getBoolean(key, defValue);
	}

	public boolean getBoolean(String key) {
		return mPreferences.getBoolean(key, false);
	}

	public boolean putLong(String key, long value) {
		mEditor.putLong(key, value);
		return mEditor.commit();
	}

	public long getLong(String key, long defValue) {
		return mPreferences.getLong(key, defValue);
	}

	public long getLong(String key) {
		return mPreferences.getLong(key, 0);
	}

	public boolean putInt(String key, int value) {
		mEditor.putInt(key, value);
		return mEditor.commit();
	}

	public int getInt(String key, int defValue) {
		return mPreferences.getInt(key, defValue);
	}

	public int getInt(String key) {
		return mPreferences.getInt(key, 0);
	}

	public boolean contains(String key) {
		return mPreferences.contains(key);
	}

	public boolean putFloat(String key, float value) {
		mEditor.putFloat(key, value);
		return mEditor.commit();
	}

	public float getFloat(String key, float defValue) {
		return mPreferences.getFloat(key, defValue);
	}

	public float getFloat(String key) {
		return mPreferences.getFloat(key, 0);
	}

	public boolean putDouble(String key, double value) {
		mEditor.putString(key, String.valueOf(value));
		return mEditor.commit();
	}

	public double getDouble(String key, double defValue) {
		return Double.parseDouble(mPreferences.getString(key, String.valueOf(defValue)));
	}

	public double getDouble(String key) {
		return Double.parseDouble(mPreferences.getString(key, "0"));
	}

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	public Set<String> getStringSet(String key, Set<String> defaultVal) {
		return mPreferences.getStringSet(key, defaultVal);
	}

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	public Set<String> getStringSet(String key) {
		return mPreferences.getStringSet(key, null);
	}

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	public boolean putStringSet(String key, Set<String> value) {
		mEditor.putStringSet(key, value);
		return mEditor.commit();
	}

	public void clear() {
		mEditor.clear().commit();
	}

	public Map<String, ?> getAll() {
		return mPreferences.getAll();
	}

}
