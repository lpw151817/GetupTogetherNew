package cm.getuptogether.request;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;

public class ImageRequest extends com.android.volley.toolbox.ImageRequest {

	public ImageRequest(String url, Listener<Bitmap> listener, int maxWidth, int maxHeight, ErrorListener errorListener) {
		super(url, listener, maxWidth, maxHeight, Config.ARGB_8888, errorListener);
	}

}
