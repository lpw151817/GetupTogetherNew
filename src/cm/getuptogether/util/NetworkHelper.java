package cm.getuptogether.util;

import java.util.HashMap;
import java.util.Map;

import com.android.volley.Request;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;

import cm.getuptogether.Application;
import cm.getuptogether.activity.BaseActivity;
import cm.getuptogether.inter.HandleUI;
import cm.getuptogether.request.StringRequest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;

public class NetworkHelper implements OnCancelListener {
	private Activity activity;
	ProgressDialog dialog;
	Request request;

	public NetworkHelper(Activity activity) {
		this.activity = activity;
		dialog = new ProgressDialog(activity);
		dialog.setOnCancelListener(this);
	}

	/**
	 * 将object传给param作为参数，post到服务器
	 * 
	 * @param url
	 * @param listener
	 * @param param
	 */
	public void handleString_Object(String url, final HandleUI<String> listener, Object param) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("param", Application.getGson().toJson(param));
		handleString_Map(url, listener, map);
	}

	public void handleString_Map(String url, final HandleUI<String> listener, Map param) {
		if (this.activity instanceof BaseActivity) {
			dialog.show();
			this.request = new StringRequest(url, new Listener<String>() {

				@Override
				public void onResponse(String arg0) {
					listener.onResponse(arg0);
					dialog.dismiss();
				}
			}, new ErrorListener() {

				@Override
				public void onErrorResponse(VolleyError arg0) {
					System.out.println(arg0.toString());
					dialog.dismiss();
				}
			}, param);
			Application.getTools().getQueue().add(request);
		} else {
			new Exception("");
		}
	}

	@Override
	public void onCancel(DialogInterface dialog) {
		if (request != null && !request.isCanceled())
			request.cancel();
	}
}
