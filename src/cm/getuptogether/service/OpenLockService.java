package cm.getuptogether.service;

import org.androidannotations.annotations.EService;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import cm.getuptogether.R;
import cm.getuptogether.receiver.OpenLockReceiver;

//@EService
public class OpenLockService extends Service {
	OpenLockReceiver receiver = null;

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		IntentFilter filter = new IntentFilter();
		filter.addAction(Intent.ACTION_SCREEN_ON);
		filter.addAction(Intent.ACTION_SCREEN_OFF);
		receiver = new OpenLockReceiver();
		registerReceiver(receiver, filter);
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// // 防止service被kill
		// // 方法一：保持service前台运行
		// Notification notification = new Notification(R.drawable.ic_launcher,
		// "test", System.currentTimeMillis());
		// PendingIntent pendingintent = PendingIntent.getActivity(this, 0, new
		// Intent(this, SecendActivity_.class), 0);
		// notification.setLatestEventInfo(this, "uploadservice", "请保持程序在后台运行",
		// pendingintent);
		// startForeground(0x111, notification);
		// // 方法二：设置返回值
		// flags = START_STICKY;
		// // 方法三：在ondestory方法中，自动重启service
		// // 定义写在ondestory方法中。。。。
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy() {
		System.out.println("service onDestroy");

		unregisterReceiver(receiver);
		stopForeground(true);
		// // 防止service被杀。。。。
		// if (startIntent != null) {
		//
		// System.out.println("serviceIntent not null");
		//
		// startService(startIntent);
		//
		// }
	}

}
