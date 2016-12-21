package com.zhaozhuan.mobile.tile.util;

import android.content.Context;

import com.zhaozhuan.mobile.tile.TileApp;

/**
 * Created by ricky.ye on 12/19/16.
 */

public class TileSharedPreferenceHelper extends SharedPrefHelper {
	public static final String APP_CURRENT_VERSION = "APP_CURRENT_VERSION";
	public static final String OS_VERSION = "OS_VERSION";
	public static final String SDK_INT = "SDK_INT";
	public static final String DEVICE_MODEL = "DEVICE_MODEL";
	public static final String PHONE_NUMBER = "PHONE_NUMBER";
	public static final String NETWORK_OPERATOR_NAME = "NETWORK_OPERATOR_NAME";
	public static final String PREFERENCE_NAME = "TILE_PREF";
	public static final String IS_LOGGED = "IS_LOGGED";
	private static TileSharedPreferenceHelper instance = new TileSharedPreferenceHelper();

	private TileSharedPreferenceHelper() {
		super();
	}

	static {
		instance.prefs = TileApp.getAPP().getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
	}

	public static TileSharedPreferenceHelper getInstance() {
		return instance;
	}

	public boolean isLogged() {
		return getSharedPreferences(IS_LOGGED, false);
	}

	public void setIsLogged(boolean isLogged) {
		setSharedPreferences(IS_LOGGED, isLogged);
	}

}
