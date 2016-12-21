package com.zhaozhuan.mobile.tile;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.zhaozhuan.mobile.tile.entity.User;
import com.zhaozhuan.mobile.tile.http.HttpMethods;
import com.zhaozhuan.mobile.tile.http.ProgressSubscriber;
import com.zhaozhuan.mobile.tile.http.SubscriberOnNextListener;
import com.zhaozhuan.mobile.tile.util.Constant;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class LoginActivity extends BasicActivity {

	@InjectView(R.id.title)
	TextView title;
	@InjectView(R.id.phone)
	EditText phone;
	@InjectView(R.id.password)
	EditText password;
	@InjectView(R.id.confirm)
	Button confirm;
	private SubscriberOnNextListener<Boolean> getSmsSubscribe;
	private SubscriberOnNextListener<User> loginSubscribe;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		ButterKnife.inject(this);
		initSubscribe();
	}

	void initSubscribe() {
		getSmsSubscribe = new SubscriberOnNextListener<Boolean>() {
			@Override
			public void doNext(Boolean aBoolean) {
				if (aBoolean) {
					Toast.makeText(context, R.string.sms_successed, Toast.LENGTH_LONG).show();
				} else {
					Toast.makeText(context, R.string.sms_failed, Toast.LENGTH_LONG).show();
				}
			}
		};

		loginSubscribe = new SubscriberOnNextListener<User>() {
			@Override
			public void doNext(User user) {
				if (user != null) {
					Intent intent;
					if (user.getType() != null) {
						intent = new Intent(context, MainActivity.class);
						intent.putExtra(Constant.USER_TYPE, user.getType());
					} else {
						intent = new Intent(context, RegisterActivity.class);
					}

					intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					startActivity(intent);
					finish();
				} else {
					Toast.makeText(context, R.string.login_failed, Toast.LENGTH_LONG).show();
				}
			}
		};
	}

	@OnClick({ R.id.confirm, R.id.sendSms })
	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.confirm:
				login();
				break;
			case R.id.sendSms:
				getSms();
				break;
		}
	}

	void getSms() {
		String phoneText = phone.getText().toString();
		if (TextUtils.isEmpty(phoneText)) {
			Toast.makeText(context, R.string.login_empty_field, Toast.LENGTH_LONG).show();
			return;
		}
		HttpMethods.getInstance().getSmsCode(
			new ProgressSubscriber<Boolean>(context, getSmsSubscribe), phoneText);
	}

	void login() {
		String phoneText = phone.getText().toString();
		String smsText = password.getText().toString();
		if (TextUtils.isEmpty(phone.getText().toString()) || TextUtils.isEmpty(password.getText().toString())) {
			Toast.makeText(context, R.string.login_empty_field, Toast.LENGTH_LONG).show();
			return;
		}

		if (phone.getText().toString().trim().length() != 11) {
			Toast.makeText(context, R.string.login_wrong_phone_number, Toast.LENGTH_LONG).show();
			return;
		}

		HttpMethods.getInstance().login(
			new ProgressSubscriber<User>(context, loginSubscribe), phoneText, smsText);
	}
}
