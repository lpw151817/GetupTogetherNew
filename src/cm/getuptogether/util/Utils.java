package cm.getuptogether.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;

public class Utils {
	public static int getVersionCode(Context context)// ��ȡ�汾��(�ڲ�ʶ���)
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
	 * �ڲ��࣬����������ʽ��֤
	 * 
	 * @author Jerry
	 * 
	 */
	public static class JudgeInput {
		/**
		 * �ж������String�Ƿ����email�淶
		 * 
		 * @param email
		 * @return
		 */
		public static boolean isEmail(String email) {
			if (null == email || "".equals(email))
				return false;
			// Pattern p = Pattern.compile("\\w+@(\\w+.)+[a-z]{2,3}"); //��ƥ��
			Pattern p = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");// ����ƥ��
			Matcher m = p.matcher(email);
			return m.matches();
		}

		/**
		 * �ж�������ֻ������Ƿ���Ϲ淶
		 * 
		 * @param phoneNumber
		 * @return
		 */
		public static boolean isPhoneNumber(String phoneNumber) {
			Matcher m = Pattern.compile("^(1(([35][0-9])|(47)|[8][0126789]))\\d{8}$").matcher(phoneNumber);
			return m.matches();
		}

		/**
		 * ��֤���볤��
		 * 
		 * @param password
		 *            8-16λ
		 * @return
		 */
		public static boolean isPasswordValid(String password) {
			// ������ʽ��(\w|[\.\*\&\^%\\?,/'";:\-_=/+!@#\$\^\(\)<>]){6,18}
			if (password.length() > 7 && password.length() < 17)
				return true;
			else
				return false;
		}

	}
}
