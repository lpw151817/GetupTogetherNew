package cm.getuptogether.activity.jerry;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import cm.getuptogether.R;
import cm.getuptogether.activity.BaseActivity;
import cm.getuptogether.bean.jerry.UserBean;
import cm.getuptogether.dao.jerry.ThirdPartyVerificationDAO;
import cm.getuptogether.inter.HandleUI;
import cm.getuptogether.param.jerry.LoginParam;
import cm.getuptogether.request.StringRequest;
import cm.getuptogether.util.Contants;
import cm.getuptogether.util.NetworkHelper;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.renn.rennsdk.RennClient;
import com.renn.rennsdk.RennExecutor.CallBack;
import com.renn.rennsdk.RennResponse;
import com.renn.rennsdk.exception.RennException;
import com.renn.rennsdk.param.GetUserParam;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WeiboAuth;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.net.RequestListener;
import com.sina.weibo.sdk.openapi.UsersAPI;
import com.sina.weibo.sdk.openapi.models.User;
import com.tencent.connect.UserInfo;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

@EActivity(R.layout.activity_login)
public class LoginActivity extends BaseActivity implements com.renn.rennsdk.RennClient.LoginListener, IUiListener, WeiboAuthListener {

	// =======人人========
	/**
	 * 人人的权限 描述
	 * 
	 * read_user_blog 获取用户日志时需要用户授予的权限。 read_user_checkin 获取用户报到信息时需要用户授予的权限。
	 * read_user_feed 获取用户新鲜事时需要用户授予的权限。 read_user_guestbook 获取用户留言板时需要用户授予的权限。
	 * read_user_invitation 获取用户被邀请的状况时需要用户授予的权限。 read_user_like_history
	 * 获取用户喜欢的历史信息时需要用户授予的权限。 read_user_message 获取用户站内信时需要用户授予的权限。
	 * read_user_notification 获取用户已收到的通知时需要用户授予的权限。 read_user_photo
	 * 获取用户相册相关信息时需要用户授予的权限。 read_user_status 获取用户状态相关信息时需要用户授予的权限。
	 * read_user_album 获取用户相册相关信息时需要用户授予的权限。 read_user_comment
	 * 获取用户评论相关信息时需要用户授予的权限。 read_user_share 获取用户分享相关信息时需要用户授予的权限。
	 * read_user_request 获取用户好友请求、圈人请求等信息时需要用户授予的权限。
	 * 
	 * publish_blog 以用户身份发布日志时需要用户授予的权限。 publish_checkin 以用户身份发布报到时需要用户授予的权限。
	 * publish_feed 以用户身份发送新鲜事时需要用户授予的权限。 publish_share 以用户身份发送分享时需要用户授予的权限。。
	 * write_guestbook 以用户身份进行留言时需要用户授予的权限。 send_invitation 以用户身份发送邀请时需要用户授予的权限。
	 * send_request 以用户身份发送好友申请、圈人请求等时需要用户授予的权限。 send_message
	 * 以用户身份发送站内信时需要用户授予的权限。 send_notification
	 * 以用户身份发送通知（user_to_user）时需要用户授予的权限。 photo_upload 以用户身份上传照片时需要用户授予的权限。
	 * status_update 以用户身份发布状态时需要用户授予的权限。 create_album 以用户身份发布相册时需要用户授予的权限。
	 * publish_comment 以用户身份发布评论时需要用户授予的权限。 operate_like 以用户身份执行喜欢操作时需要用户授予的权限。
	 * 
	 * admin_page 以用户的身份，管理其可以管理的公共主页的权限。
	 */
	private final String RENREN_APP_ID = "272711";
	private final String RENREN_API_KEY = "da5be33beb8b4aa69f7f99f0737fb3eb";
	private final String RENREN_SECRET_KEY = "77dab849bcee456c8246607f4d588d07";
	private final String RENREN_AUTHEN_THINGS = "read_user_feed status_update";
	RennClient rennClient;

	// *************QQ
	private final String QQ_APP_ID = "1103432471";
	private final String QQ_APP_KEY = "JWXwA4tupklTX9U4";
	Tencent mTencent;

	// .............微博
	private final String WEIBO_APP_KEY = "2316724904";
	public static final String WEIBO_REDIRECT_URL = "http://banbore.com";// 应用的回调页
	// 应用申请的高级权限
	public static final String WEIBO_SCOPE = "email,direct_messages_read,direct_messages_write,friendships_groups_read,friendships_groups_write,statuses_to_me_read,follow_app_official_microblog,invitation_write";
	WeiboAuth mWeiboAuth;

	@ViewById(R.id.btn_renren)
	Button btn_renren;
	@ViewById(R.id.btn_qq)
	Button btn_qq;
	@ViewById(R.id.btn_weibo)
	Button btn_weibo;
	@ViewById(R.id.login_register)
	Button btn_register;
	@ViewById(R.id.login_login)
	Button btn_login;

	@ViewById(R.id.login_username)
	EditText et_username;
	@ViewById(R.id.login_password)
	EditText et_password;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		dao = new ThirdPartyVerificationDAO(this);

	}

	@Click
	void login_login() {
		if (TextUtils.isEmpty(et_username.getText().toString()))
			showToast("请输入用户名");
		else {
			if (TextUtils.isEmpty(et_password.getText().toString()))
				showToast("请输入密码");
			else {
				// 登录
				new NetworkHelper(this).handleString_Object(Contants.URL_LOGIN_POST, new HandleUI<String>() {

					@Override
					public void onResponse(String response) {
						UserBean user = UserBean.parase(response);
						if (user != null) {
							switch (user.getCode()) {
							case Contants.SERVERCODE_LOGIN_NOACCOUNT:
								showToast("您输入的用户不存在");
								break;
							case Contants.SERVERCODE_LOGIN_SECCESS:
								showToast("登录成功");
								break;
							case Contants.SERVERCODE_LOGIN_WRONGPASSWORD:
								showToast("密码错误");
								break;
							}
						}
					}
				}, new LoginParam(et_username.getText().toString(), et_password.getText().toString()));

			}
		}
	}

	@Click
	void btn_qq() {
		mTencent = Tencent.createInstance(QQ_APP_ID, this);
		mTencent.login(this, "all", this);

	}

	@Click
	void btn_weibo() {
		mWeiboAuth = new WeiboAuth(this, WEIBO_APP_KEY, WEIBO_REDIRECT_URL, WEIBO_SCOPE);
		mWeiboAuth.anthorize(this);
	}

	@Click
	void login_register() {
		Intent intent = new Intent(this, RegisterActivity_.class);
		startActivity(intent);
	}

	@Click
	void btn_renren() {
		// 初始化RennClient
		rennClient = RennClient.getInstance(this);// 获取实例
		rennClient.init(RENREN_APP_ID, RENREN_API_KEY, RENREN_SECRET_KEY);// 设置应用程序信息
		// 设置权限范围
		rennClient.setScope(RENREN_AUTHEN_THINGS);
		// 设置Token类型
		rennClient.setTokenType("bearer"); // 使用bearer token

		// if (rennClient.isLogin()) {
		// getUserInfo();
		// }

		// 通过为rennClient设置监听来处理登陆结果
		rennClient.setLoginListener(this);
		// 登录
		rennClient.login(this);

	}

	// 人人登陆取消的回调
	@Override
	public void onLoginCanceled() {
		System.out.println("cancel...");
	}

	ProgressDialog dialog;

	// 人人登陆成功的回调
	@Override
	public void onLoginSuccess() {
		getUserInfo();
	}

	public void getUserInfo() {
		dialog = new ProgressDialog(this);
		dialog.show();

		GetUserParam userParam = new GetUserParam();
		try {
			rennClient.getRennService().sendAsynRequest(userParam, new CallBack() {

				@Override
				public void onSuccess(RennResponse arg0) {

					Intent intent = new Intent(LoginActivity.this, RegisterActivity_.class);
					intent.putExtra(Contants.INTENT_THRIDPART4REG_STRING, Contants.PREFIX_RENREN + arg0.toString());
					LoginActivity.this.startActivity(intent);
					dialog.dismiss();
				}

				@Override
				public void onFailed(String arg0, String arg1) {
					System.out.println("failed.................");
				}
			});
		} catch (RennException e) {
			e.printStackTrace();
		}
	}

	// QQ Callback
	@Override
	public void onComplete(Object arg0) {
		System.out.println(".............complete");
		UserInfo info = new UserInfo(this, mTencent.getQQToken());
		info.getUserInfo(new IUiListener() {

			@Override
			public void onError(UiError arg0) {
				System.out.println("error:" + arg0.toString());
			}

			@Override
			public void onComplete(Object arg0) {
				Intent intent = new Intent(LoginActivity.this, RegisterActivity_.class);
				intent.putExtra(Contants.INTENT_THRIDPART4REG_STRING, Contants.PREFIX_QQ + arg0.toString());
				LoginActivity.this.startActivity(intent);
			}

			@Override
			public void onCancel() {
				System.out.println("cancel////");
			}
		});

	}

	// QQ Callback
	@Override
	public void onError(UiError arg0) {
		System.out.println("...............UiError");
		System.out.println(arg0.toString());
	}

	// QQ Callback
	// Weibo Callback
	@Override
	public void onCancel() {
		System.out.println("...............cancel");
	}

	// Weibo Callback
	@Override
	public void onComplete(Bundle arg0) {
		dialog = new ProgressDialog(LoginActivity.this);
		dialog.show();
		Oauth2AccessToken mAccessToken = Oauth2AccessToken.parseAccessToken(arg0);
		if (mAccessToken.isSessionValid()) {
			UsersAPI mUsersAPI = new UsersAPI(mAccessToken);
			mUsersAPI.show(Long.parseLong(mAccessToken.getUid()), new RequestListener() {

				@Override
				public void onWeiboException(WeiboException arg0) {
					System.out.println(arg0.toString());
				}

				@Override
				public void onComplete(String arg0) {
					Intent intent = new Intent(LoginActivity.this, RegisterActivity_.class);
					intent.putExtra(Contants.INTENT_THRIDPART4REG_STRING, Contants.PREFIX_WEIBO + arg0.toString());
					LoginActivity.this.startActivity(intent);
					dialog.dismiss();
				}
			});

		} else {
			// 以下几种情况，您会收到 Code：
			// 1. 当您未在平台上注册的应用程序的包名与签名时；
			// 2. 当您注册的应用程序包名与签名不正确时；
			// 3. 当您在平台上注册的包名和签名与您当前测试的应用的包名和签名不匹配时。
			String code = arg0.getString("code", "");
			System.out.println("error code: " + code + "...................");
		}
	}

	// Weibo Callback
	@Override
	public void onWeiboException(WeiboException arg0) {
		System.out.println("...............UiError");
		System.out.println(arg0.toString());
		showToast(arg0.toString());
	}

}
