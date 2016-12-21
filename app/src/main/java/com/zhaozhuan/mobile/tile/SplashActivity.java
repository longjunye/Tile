package com.zhaozhuan.mobile.tile;

import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;

import com.zhaozhuan.mobile.tile.util.TileSharedPreferenceHelper;

public class SplashActivity extends BasicActivity {
    Handler mHandler =new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                doNext();
            }
        }, 2000);
    }

    void doNext() {
        Intent intent;
        if (TileSharedPreferenceHelper.getInstance() != null && TileSharedPreferenceHelper.getInstance().isLogged()) {
            intent = new Intent(this, BasicActivity.class);
        } else {
            intent = new Intent(this, LoginActivity.class);
        }
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}
