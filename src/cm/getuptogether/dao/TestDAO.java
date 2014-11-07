package cm.getuptogether.dao;

import java.sql.SQLException;

import com.j256.ormlite.dao.Dao;

import cm.getuptogether.bean.Test;
import android.content.Context;

public class TestDAO extends BaseDAO<Test, Integer> {

	public TestDAO(Context context) {
		super(context);
	}

	@Override
	public Dao<Test, Integer> getDao() throws SQLException {
		return helper.getDao(Test.class);
	}

}
