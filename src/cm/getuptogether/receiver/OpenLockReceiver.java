package cm.getuptogether.receiver;

import org.androidannotations.annotations.EReceiver;

import cm.getuptogether.activity.LockActivity;
import cm.getuptogether.activity.LockActivity_;
import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

@EReceiver
public class OpenLockReceiver extends BroadcastReceiver {
	private KeyguardManager mKeyguardManager = null;
	private KeyguardManager.KeyguardLock mKeyguardLock = null;

	@Override
	public void onReceive(Context context, Intent intent) {
		String action = intent.getAction();

		System.out.println("action is " + action);

		if (action.equals(Intent.ACTION_SCREEN_OFF)) {
			mKeyguardManager = (KeyguardManager) context.getSystemService(Context.KEYGUARD_SERVICE);
			mKeyguardLock = mKeyguardManager.newKeyguardLock("getuptogether");
			mKeyguardLock.disableKeyguard();
			Intent lockIntent = new Intent(context, LockActivity_.class);
			lockIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			context.startActivity(lockIntent);
		}

	}

}
