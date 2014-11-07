package cm.getuptogether.inter;

import com.android.volley.Response.Listener;

public interface HandleUI<T> extends Listener<T> {
	public void onResponse(T response);
}
