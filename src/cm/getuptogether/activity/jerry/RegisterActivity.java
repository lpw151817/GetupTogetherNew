package cm.getuptogether.activity.jerry;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.ViewsById;

import com.tencent.utils.Util;

import cm.getuptogether.R;
import cm.getuptogether.R.layout;
import cm.getuptogether.activity.BaseActivity;
import cm.getuptogether.bean.jerry.BooleanResult;
import cm.getuptogether.inter.HandleUI;
import cm.getuptogether.param.jerry.RegisterParam;
import cm.getuptogether.request.StringRequest;
import cm.getuptogether.util.Contants;
import cm.getuptogether.util.NetworkHelper;
import cm.getuptogether.util.ThirdLoginUtils;
import cm.getuptogether.util.Utils;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

@EActivity(R.layout.activity_register)
public class RegisterActivity extends BaseActivity {
	@ViewById
	EditText et_username;
	@ViewById
	EditText et_password;
	@ViewById
	EditText et_phone;
	@ViewById
	Button bt_register;

	@AfterViews
	void ini() {
		// ����������ע�ᰴť���򲻳�ʼ���ؼ��ϵ�ֵ
		if (getIntent().getExtras() == null)
			return;

		String thiredPartResponse = getIntent().getExtras().getString(Contants.INTENT_THRIDPART4REG_STRING, "");

		if (thiredPartResponse != null && thiredPartResponse != "") {
			// ����������˵�½��
			if (thiredPartResponse.startsWith(Contants.PREFIX_RENREN)) {
				et_username.setText(ThirdLoginUtils.renren_getUsername(thiredPartResponse));
			}
			// QQ
			else if (thiredPartResponse.startsWith(Contants.PREFIX_QQ)) {
				et_username.setText(ThirdLoginUtils.qq_getNickName(thiredPartResponse));
			}
			// Weibo
			else if (thiredPartResponse.startsWith(Contants.PREFIX_WEIBO)) {
				et_username.setText(ThirdLoginUtils.weibo_getUserObject(thiredPartResponse).screen_name);
			}
		}
	}

	@Click
	void bt_register() {
		if (et_username.getText().toString().length() == 0)
			showToast("�������˺�");
		else if (et_password.getText().toString().length() == 0)
			showToast("����������");
		else if (!Utils.JudgeInput.isPasswordValid(et_password.getText().toString()))
			showToast("������8-16λ������");
		else if (et_phone.getText().toString().length() == 0)
			showToast("�������ֻ���");
		else if (!Utils.JudgeInput.isPhoneNumber(et_phone.getText().toString()))
			showToast("��������ȷ���ֻ���");
		else {
			// ����ע������.
			RegisterParam param = new RegisterParam();
			param.username = et_username.getText().toString();
			param.password = et_password.getText().toString();
			param.phoneNum = et_phone.getText().toString();
			new NetworkHelper(this).handleString_Object(Contants.URL_REGISTER_POST, new HandleUI<String>() {

				@Override
				public void onResponse(String response) {
					if (BooleanResult.parase(response)) {

					}

				}
			}, param);
		}
	}
}
