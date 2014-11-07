package cm.getuptogether.util;

import java.sql.SQLException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import cm.getuptogether.bean.Test;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class OrmDatabaseHelper extends OrmLiteSqliteOpenHelper {

	private final static String DATABASE_NAME = "GETUPDB";
	// 如果想更新数据库表结构，一定先要更新数据库版本
	private static int DATABASE_VERSION = 1;

	public OrmDatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	/**
	 * This is called when the database is first created. Usually you should
	 * call createTable statements here to create the tables that will store
	 * your data.
	 */
	@Override
	public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
		try {
			System.out.println("=====create table....");
			TableUtils.createTableIfNotExists(connectionSource, Test.class);
		} catch (SQLException e) {
			System.out.println("=====create table failed....");
			e.printStackTrace();
		}

	}

	/**
	 * This is called when your application is upgraded and it has a higher
	 * version number. This allows you to adjust the various data to match the
	 * new version number.
	 */
	@Override
	public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
		// 更新到第2版本(newVersion>1)
		if (oldVersion <= 1) {
			// 对database进行操作
			database.execSQL("");
		}
		// 更新到第3版本(newVersion>2)
		if (oldVersion <= 2) {
			// 对database进行操作
			database.execSQL("");
		}
		// ...
	}

}
