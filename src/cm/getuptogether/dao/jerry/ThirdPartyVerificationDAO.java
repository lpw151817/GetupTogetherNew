package cm.getuptogether.dao.jerry;

import java.sql.SQLException;

import com.j256.ormlite.dao.Dao;

import android.content.Context;
import cm.getuptogether.bean.jerry.ThirdPartyVerification;
import cm.getuptogether.dao.BaseDAO;

public class ThirdPartyVerificationDAO extends BaseDAO<ThirdPartyVerification, Integer> {

	public ThirdPartyVerificationDAO(Context context) {
		super(context);
	}

	@Override
	public Dao<ThirdPartyVerification, Integer> getDao() throws SQLException {
		return helper.getDao(ThirdPartyVerification.class);
	}

	public String getRenrenToken() {
		try {
			return queryAll().get(0).getRenrenToken();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public String getQQToken() {
		try {
			return queryAll().get(0).getqQToken();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public String getWeiboToken() {
		try {
			return queryAll().get(0).getWeiboToken();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
