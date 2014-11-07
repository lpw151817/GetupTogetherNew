package cm.getuptogether.request;

import java.util.HashMap;
import java.util.Map;

import cm.getuptogether.Application;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RetryPolicy;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;

public class StringRequest extends com.android.volley.toolbox.StringRequest {
	private Map param;
	private Listener<String> responseListener;
	// 超时时间
	private final int SOCKET_TIMEOUT = 5000;

	public StringRequest(String url, Listener<String> listener, ErrorListener errorListener, Map param) {
		super(Method.POST, url, listener, errorListener);
		this.param = param;
		this.responseListener = listener;
		this.setShouldCache(true);
		this.setTag(responseListener);
	}

	public StringRequest(String url, Listener<String> listener, ErrorListener errorListener) {
		super(Method.GET, url, listener, errorListener);
		this.responseListener = listener;
		this.setShouldCache(true);
		this.setTag(responseListener);
	}

	// 超时设置
	@Override
	public RetryPolicy getRetryPolicy() {
		return new DefaultRetryPolicy(SOCKET_TIMEOUT, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
	}

	@Override
	protected Map getParams() throws AuthFailureError {
		if (this.param != null)
			return this.param;
		else
			return null;
	}

	@Override
	public Map<String, String> getHeaders() throws AuthFailureError {
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Charset", "UTF-8");
		// headers.put("Content-Type", "application/x-javascript");
		// gzip压缩传输
		headers.put("Accept-Encoding", "gzip,deflate");
		return headers;
	}
}
