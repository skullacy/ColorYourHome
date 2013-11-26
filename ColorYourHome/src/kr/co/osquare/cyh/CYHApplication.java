package kr.co.osquare.cyh;

import android.app.Application;
import android.content.Context;

public class CYHApplication extends Application {
	public final static String API_URL = "http://www.ggumim.kr";

	private static Context appContext;
	
	@Override
	public void onCreate() {
		super.onCreate();

		appContext = this;
	}
	
	public static Context getContext() {
		return appContext;
	}
}
