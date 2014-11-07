package cm.getuptogether;

import org.androidannotations.annotations.EApplication;

import cm.getuptogether.util.VolleyTools;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Application extends android.app.Application {
	private static VolleyTools tools;
	private static Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

	@Override
	public void onCreate() {
		super.onCreate();
		tools = new VolleyTools(this);
	}

	public static VolleyTools getTools() {
		return tools;
	}

	@Override
	public void onTerminate() {
		super.onTerminate();
		tools.release();
	}

	public static Gson getGson() {
		return gson;
	}
	
	

}
