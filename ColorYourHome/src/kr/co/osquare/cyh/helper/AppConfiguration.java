package kr.co.osquare.cyh.helper;

import kr.co.osquare.cyh.CYHApplication;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class AppConfiguration {

	private static AppConfiguration _instance = null;
	public static AppConfiguration getInstance() {
		if (_instance == null) _instance = new AppConfiguration();
		if (_instance.modified) _instance.save();
		return _instance;
	}
	
	private boolean modified;
	private Context context;
	private SharedPreferences pm;
	
	public AppConfiguration() {
		this.context = CYHApplication.getContext();
		this.pm = PreferenceManager.getDefaultSharedPreferences(this.context);
		this.refresh();
	}
	
	
	private String userId;
	private String accessToken;
	private String generatedAt;
	private String machineName;
	
	private long revision;
	
	private boolean usable3G;
	private boolean musicRepeat;
	private boolean musicOneRepeat;
	private boolean musicShuffle;
	
	private boolean offlineMode;
	
	private int listMode;
	public static final int DISPLAY_TITLE_BY_ARTIST_TITLE = 1301;
	public static final int DISPLAY_TITLE_BY_TITLE_ARTIST = 1302;
	public static final int DISPLAY_TITLE_BY_TITLE = 1303;
	public static final int DISPLAY_TITLE_BY_TRACK_TITLE_ARTIST = 1304;
	
	private int orderMode;
	public static final int ORDER_BY_TITLE = 1401;
	public static final int ORDER_BY_ALBUM = 1402;
	
	private void refresh() {
		this.userId = this.pm.getString("userId", "");
		this.accessToken = this.pm.getString("accessToken", "");
		this.generatedAt = this.pm.getString("generatedAt", "");
		this.machineName = this.pm.getString("machineName", "");
		
		this.revision = this.pm.getLong("revision", 0);
		
		this.usable3G = this.pm.getBoolean("usable3G", false);
		this.musicRepeat = this.pm.getBoolean("musicRepeat", false);
		this.musicOneRepeat = this.pm.getBoolean("musicOneRepeat", false);
		this.musicShuffle = this.pm.getBoolean("musicShuffle", false);
		
		this.listMode = this.pm.getInt("listMode", DISPLAY_TITLE_BY_ARTIST_TITLE);
		this.orderMode = this.pm.getInt("orderMode", ORDER_BY_TITLE);
		
		this.offlineMode = this.pm.getBoolean("offlineMode", false);
	}
	
	@SuppressLint("NewApi")
	public void save() {
		SharedPreferences.Editor editor = pm.edit();

		editor.putString("userId", userId);
		editor.putString("accessToken", accessToken);
		editor.putString("generatedAt", generatedAt);
		editor.putString("machineName", machineName);
		
		editor.putLong("revision", revision);
		
		editor.putBoolean("usable3G", usable3G);
		editor.putBoolean("musicRepeat", musicRepeat);
		editor.putBoolean("musicOneRepeat", musicOneRepeat);
		editor.putBoolean("musicShuffle", musicShuffle);
		
		editor.putInt("listMode", listMode);
		editor.putInt("orderMode", orderMode);
		editor.putBoolean("offlineMode", offlineMode);
		
		editor.apply();
		editor.commit();
		this.modified = false;
	}
	
	public void settingRemoveAll() {
		SharedPreferences.Editor editor = pm.edit();
		editor.clear();
		editor.commit();
		
		this.userId = null;
		this.accessToken = null;
		this.generatedAt = null;
		this.machineName = null;
		this.revision = 0;
		this.usable3G = false;
		this.musicRepeat = false;
		this.musicOneRepeat = false;
		this.musicShuffle = false;
		this.listMode = 0;
		this.orderMode = 0;
		this.offlineMode = false;
		
		this.modified = false;
	}

	public void removeString(String key) {
		SharedPreferences.Editor editor = pm.edit();
		editor.remove(key);
		editor.commit();
		this.modified = false;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
		this.modified = true;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
		this.modified = true;
	}

	public String getGeneratedAt() {
		return generatedAt;
	}

	public void setGeneratedAt(String generatedAt) {
		this.generatedAt = generatedAt;
		this.modified = true;
	}

	public String getMachineName() {
		return machineName;
	}

	public void setMachineName(String machineName) {
		this.machineName = machineName;
		this.modified = true;
	}

	public long getRevision() {
		return revision;
	}

	public void setRevision(long revision) {
		this.revision = revision;
		this.modified = true;
	}

	public boolean isUsable3G() {
		return usable3G;
	}

	public void setUsable3G(boolean usable3g) {
		usable3G = usable3g;
		this.modified = true;
	}

	public boolean isMusicRepeat() {
		return musicRepeat;
	}

	public void setMusicRepeat(boolean musicRepeat) {
		this.musicRepeat = musicRepeat;
		this.modified = true;
	}

	public boolean isMusicOneRepeat() {
		return musicOneRepeat;
	}

	public void setMusicOneRepeat(boolean musicOneRepeat) {
		this.musicOneRepeat = musicOneRepeat;
		this.modified = true;
	}

	public boolean isMusicShuffle() {
		return musicShuffle;
	}

	public void setMusicShuffle(boolean musicShuffle) {
		this.musicShuffle = musicShuffle;
		this.modified = true;
	}

	public int getListMode() {
		return listMode;
	}

	public void setListMode(int listMode) {
		this.listMode = listMode;
		this.modified = true;
	}

	public int getOrderMode() {
		return orderMode;
	}

	public void setOrderMode(int orderMode) {
		this.orderMode = orderMode;
		this.modified = true;
	}

	public boolean isOfflineMode() {
		return offlineMode;
	}

	public void setOfflineMode(boolean offlineMode) {
		this.offlineMode = offlineMode;
		this.modified = true;
	}


}