package com.zhaozhuan.mobile.tile.entity;

/**
 * Created by ricky.ye on 12/21/16.
 */

public class Sms {
    private boolean sendStatus;
    private String smsCode;

    public boolean isSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(boolean sendStatus) {
        this.sendStatus = sendStatus;
    }

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }
}
