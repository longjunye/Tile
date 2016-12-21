package com.zhaozhuan.mobile.tile.util;

import android.content.SharedPreferences;

import java.util.Set;

/**
 * Created by ricky.ye on 12/19/16.
 */

public class SharedPrefHelper {
	public SharedPreferences prefs;

	protected SharedPrefHelper() {
	}

	public boolean getSharedPreferences(String key, boolean defaultVal) {
		return prefs.getBoolean(key, defaultVal);
	}

	public String getSharedPreferences(String key, String defaultVal) {
		return prefs.getString(key, defaultVal);
	}

	public long getSharedPreferences(String key, long defaultVal) {
		return prefs.getLong(key, defaultVal);
	}

	public int getSharedPreferences(String key, int defaultVal) {
		return prefs.getInt(key, defaultVal);
	}

	public float getSharedPreferences(String key, float defaultVal) {
		return prefs.getFloat(key, defaultVal);
	}

	public Set<String> getSharedPreferencesSet(String key, Set<String> defaultVal) {
		return prefs.getStringSet(key, defaultVal);
	}

	public void setSharedPreferences(String key, boolean val) {
		SharedPreferences.Editor edit = prefs.edit();
		edit.putBoolean(key, val);
		edit.commit();
	}

	public void applySharedPreferences(String key, boolean val) {
		SharedPreferences.Editor edit = prefs.edit();
		edit.putBoolean(key, val);
		edit.apply();
	}

	public void setSharedPreferences(String key, int val) {
		SharedPreferences.Editor edit = prefs.edit();
		edit.putInt(key, val);
		edit.commit();
	}

	public void applySharedPreferences(String key, int val) {
		SharedPreferences.Editor edit = prefs.edit();
		edit.putInt(key, val);
		edit.apply();
	}

	public void setSharedPreferences(String key, String val) {
		SharedPreferences.Editor edit = prefs.edit();
		edit.putString(key, val);
		edit.commit();
	}

	public void applySharedPreferences(String key, String val) {
		SharedPreferences.Editor edit = prefs.edit();
		edit.putString(key, val);
		edit.apply();
	}

	public void setSharedPreferences(String key, long val) {
		SharedPreferences.Editor edit = prefs.edit();
		edit.putLong(key, val);
		edit.commit();
	}

	public void applySharedPreferences(String key, long val) {
		SharedPreferences.Editor edit = prefs.edit();
		edit.putLong(key, val);
		edit.apply();
	}

	public void setSharedPreferences(String key, float val) {
		SharedPreferences.Editor edit = prefs.edit();
		edit.putFloat(key, val);
		edit.commit();
	}

	public void applySharedPreferences(String key, float val) {
		SharedPreferences.Editor edit = prefs.edit();
		edit.putFloat(key, val);
		edit.apply();
	}

	public void setSharedPreferencesSet(String key, Set<String> val) {
		SharedPreferences.Editor edit = prefs.edit();
		edit.remove(key);
		edit.putStringSet(key, val);
		edit.commit();
	}

	public void removeSharedPreferences(String key) {
		SharedPreferences.Editor edit = prefs.edit();
		edit.remove(key);
		edit.commit();
	}
}
