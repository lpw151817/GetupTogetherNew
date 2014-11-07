package cm.getuptogether.bean.jerry;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class ThirdPartyVerification {
	@DatabaseField(id = true)
	int id;
	@DatabaseField(useGetSet = true)
	String renrenToken;
	@DatabaseField(useGetSet = true)
	String qQToken;
	@DatabaseField(useGetSet = true)
	String weiboToken;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRenrenToken() {
		return renrenToken;
	}

	public void setRenrenToken(String renrenToken) {
		this.renrenToken = renrenToken;
	}

	public String getqQToken() {
		return qQToken;
	}

	public void setqQToken(String qQToken) {
		this.qQToken = qQToken;
	}

	public String getWeiboToken() {
		return weiboToken;
	}

	public void setWeiboToken(String weiboToken) {
		this.weiboToken = weiboToken;
	}

	public ThirdPartyVerification(int id, String renrenToken, String qQToken, String weiboToken) {
		this.id = id;
		this.renrenToken = renrenToken;
		this.qQToken = qQToken;
		this.weiboToken = weiboToken;
	}

	public ThirdPartyVerification() {
	}

}
