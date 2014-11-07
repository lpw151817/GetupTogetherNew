package cm.getuptogether.util;

public class Contants {
	// intent命名规则：intent_xx到xx_数据类型
	public static final String INTENT_THRIDPART4REG_STRING = "INTENT_THRIDPART4REG_STRING";

	public static final String PREFIX_RENREN = "PREFIX_RENREN";
	public static final String PREFIX_QQ = "PREFIX_QQ";
	public static final String PREFIX_WEIBO = "PREFIX_WEIBO";

	// 请求url命名规则：URL_功能_post/get
	public static final String URL_LOGIN_POST = "http://114.215.178.134:8080/getup/action/User_login";
	public static final String URL_REGISTER_POST = "http://114.215.178.134:8080/getup/action/User_register";

	// 从服务器返回回来的code值
	public static final int SERVERCODE_LOGIN_NOACCOUNT = 0;
	public static final int SERVERCODE_LOGIN_WRONGPASSWORD = 100;
	public static final int SERVERCODE_LOGIN_SECCESS = 200;
}
