package com.zhaozhuan.mobile.tile;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class BasicActivity extends AppCompatActivity {
    protected Context context;
    public enum PendingTransition {
        LEFT,
        RIGHT
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setPendingTransition(PendingTransition.LEFT);
        context = this;
    }

    @Override
    public void finish() {
        super.finish();
        setPendingTransition(PendingTransition.RIGHT);
    }

    public void setPendingTransition(PendingTransition transition) {
        if (transition == PendingTransition.LEFT) {
            overridePendingTransition(R.anim.anim_activity_left_open_action, R.anim.anim_activity_left_close_action);
        } else {
            overridePendingTransition(R.anim.anim_activity_right_open_action, R.anim.anim_activity_right_close_action);
        }
    }
}
