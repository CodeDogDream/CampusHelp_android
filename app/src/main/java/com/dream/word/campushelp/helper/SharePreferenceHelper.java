package com.dream.word.campushelp.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SharePreferenceHelper {


	public static void saveSharePreferenceFromString(Context context, String name, String key, String value) {
		SharedPreferences sharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);
		Editor editor = sharedPreferences.edit();
		editor.putString(key, value);
		editor.commit();
	}

	public static void saveSharePreferenceFromBoolean(Context context, String name, String key, boolean value) {
		SharedPreferences sharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);
		Editor editor = sharedPreferences.edit();
		editor.putBoolean(key, value);
		editor.commit();
	}

	public static void saveSharePreferenceFromInteger(Context context, String name, String key, int value) {
		SharedPreferences sharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);
		Editor editor = sharedPreferences.edit();
		editor.putInt(key, value);
		editor.commit();
	}

	public static boolean getSharePreferenceFromBoolean(Context context, String name, String key) {
		SharedPreferences sharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);
		return sharedPreferences.getBoolean(key, false);
	}

	public static String getSharePreferenceFromString(Context context, String name, String key) {
		SharedPreferences sharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);
		return sharedPreferences.getString(key, "");

	}

	public static int getSharePreferenceFromInteger(Context context, String name, String key) {
		SharedPreferences sharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);
		try {
			return sharedPreferences.getInt(key, 0);
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}

	}

	public static int getSharePreferenceFromNegativeInteger(Context context, String name, String key) {
		SharedPreferences sharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);
		try {
			return sharedPreferences.getInt(key, -1);
		} catch (Exception e) {
			// TODO: handle exception
			return -1;
		}

	}


}
