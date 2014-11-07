package cm.getuptogether.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;

public class Utils {
	public static int getVersionCode(Context context)// 获取版本号(内部识别号)
	{
		try {
			PackageInfo pi = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
			return pi.versionCode;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	
	/**
	 * 内部类，利用正则表达式验证
	 * 
	 * @author Jerry
	 * 
	 */
	public static class JudgeInput {
		/**
		 * 判断输入的String是否符合email规范
		 * 
		 * @param email
		 * @return
		 */
		public static boolean isEmail(String email) {
			if (null == email || "".equals(email))
				return false;
			// Pattern p = Pattern.compile("\\w+@(\\w+.)+[a-z]{2,3}"); //简单匹配
			Pattern p = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");// 复杂匹配
			Matcher m = p.matcher(email);
			return m.matches();
		}

		/**
		 * 判断输入的手机号码是否符合规范
		 * 
		 * @param phoneNumber
		 * @return
		 */
		public static boolean isPhoneNumber(String phoneNumber) {
			Matcher m = Pattern.compile("^(1(([35][0-9])|(47)|[8][0126789]))\\d{8}$").matcher(phoneNumber);
			return m.matches();
		}

		/**
		 * 验证密码长度
		 * 
		 * @param password
		 *            8-16位
		 * @return
		 */
		public static boolean isPasswordValid(String password) {
			// 正则表达式：(\w|[\.\*\&\^%\\?,/'";:\-_=/+!@#\$\^\(\)<>]){6,18}
			if (password.length() > 7 && password.length() < 17)
				return true;
			else
				return false;
		}

	}
}
