package cm.getuptogether.bean.jerry;

import java.io.Serializable;
import java.util.List;

import cm.getuptogether.Application;

public class UserBean implements Serializable {
	private int userId;
	private String username;
	private String nickname;
	private String gender;
	private String signature;
	private String iamgeUrl;
	private String audioIntroductionUrl;
	private List<String> alarmTimes;
	private int code;
	private int cookie;
	private String token;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public int getCookie() {
		return cookie;
	}

	public void setCookie(int cookie) {
		this.cookie = cookie;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getIamgeUrl() {
		return iamgeUrl;
	}

	public void setIamgeUrl(String iamgeUrl) {
		this.iamgeUrl = iamgeUrl;
	}

	public String getAudioIntroductionUrl() {
		return audioIntroductionUrl;
	}

	public void setAudioIntroductionUrl(String audioIntroductionUrl) {
		this.audioIntroductionUrl = audioIntroductionUrl;
	}

	public List<String> getAlarmTimes() {
		return alarmTimes;
	}

	public void setAlarmTimes(List<String> alarmTimes) {
		this.alarmTimes = alarmTimes;
	}

	public UserBean(int userId, String nickname, String gender, String signature, String iamgeUrl, String audioIntroductionUrl) {
		this.userId = userId;
		this.nickname = nickname;
		this.gender = gender;
		this.signature = signature;
		this.iamgeUrl = iamgeUrl;
		this.audioIntroductionUrl = audioIntroductionUrl;
	}

	public UserBean(int userId, String nickname, String gender, String signature, String iamgeUrl, String audioIntroductionUrl, List<String> alarmTimes) {
		this.userId = userId;
		this.nickname = nickname;
		this.gender = gender;
		this.signature = signature;
		this.iamgeUrl = iamgeUrl;
		this.audioIntroductionUrl = audioIntroductionUrl;
		this.alarmTimes = alarmTimes;
	}

	public UserBean() {
	}

	public static UserBean parase(String jo) {
		return Application.getGson().fromJson(jo, UserBean.class);
	}
}
