package cm.getuptogether.bean.jerry;

import cm.getuptogether.Application;

public class BooleanResult {
	private boolean result;

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public static boolean parase(String jo) {
		return Application.getGson().fromJson(jo, BooleanResult.class).isResult();
	}
}
