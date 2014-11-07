package cm.getuptogether.activity;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import cm.getuptogether.R;
import cm.getuptogether.bean.Test;
import cm.getuptogether.dao.TestDAO;
import cm.getuptogether.request.ImageRequest;
import cm.getuptogether.request.StringRequest;
import cm.getuptogether.service.OpenLockService;
import cm.getuptogether.util.VolleyTools;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.db.DatabaseType;
import com.j256.ormlite.db.SqliteAndroidDatabaseType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * <b>AndroidAnnotation's usage:</b><br/>
 * http://blog.csdn.net/limb99/article/details/9067827<br/>
 * <b>You also can study it by:</b><br/>
 * https://github.com/excilys/androidannotations/wiki<br/>
 * <br/>
 * <b>Volley's usage:</b><br/>
 * You can get it by the example below.<br/>
 * <br/>
 * 
 * @author Jerry
 *
 */

@EActivity(R.layout.activity_main)
public class MainActivity extends BaseActivity {
	String url;
	@ViewById(R.id.test)
	TextView textView;
	@ViewById(R.id.image)
	NetworkImageView imageView;
	@ViewById(R.id.imageview)
	ImageView img;

	VolleyTools tool;

	// AfterViews注释定义的方法会在OnCreate方法的setContentView后执行
	@AfterViews
	void ini() {

		dao = new TestDAO(this);

		// 得到VolleyTools的实例，已定义在Application，从BaseActivity中取出
		tool = getVolleyTools();

		// 数据请求
		// post
		url = "http://114.215.178.134:8080/tyc/app/Product_getProducts";
		Test value = new Test(1, 2);

		Map<String, String> param = new HashMap<String, String>();
		param.put("input", getGson().toJson(value));
		tool.getQueue().add(new StringRequest(url, new Listener<String>() {

			@Override
			public void onResponse(String arg0) {
				textView.setText(arg0);
			}
		}, new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError arg0) {
				System.out.println(arg0.toString());
			}
		}, param));

		// get
		url = "http://114.215.178.134:8080/tyc/app/Product_getProducts?input={%22state%22:1,%22userprofileId%22:2}";
		tool.getQueue().add(new StringRequest(url, new Listener<String>() {

			@Override
			public void onResponse(String arg0) {
				textView.setText(arg0);
			}
		}, new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError arg0) {
				System.out.println(arg0.toString());
			}
		}));

		// 图片请求
		url = "http://news.baidu.com/resource/img/logo_news_137_46.png";
		// 方法一：ImageRequest能够处理单张图片
		tool.getQueue().add(new ImageRequest(url, new Listener<Bitmap>() {

			@Override
			public void onResponse(Bitmap arg0) {
				img.setImageBitmap(arg0);
			}
		}, 300, 200, new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError arg0) {
				// TODO Auto-generated method stub
				System.out.println(arg0.toString());
			}
		}));

		// 方法二，对应imageview绑定对应的网络图片
		tool.getImageLoader().get(url, ImageLoader.getImageListener(img, R.drawable.ic_launcher, 0));

		// 方法三，使用NetworkImageView
		imageView.setImageUrl(url, tool.getImageLoader());
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// // 打开跨锁屏的service
		// startService(new Intent(this, OpenLockService.class));
		// // OpenLockService_.intent(this).start();
	}

}