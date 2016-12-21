package com.zhaozhuan.mobile.tile.entity;

/**
 * Created by ricky.ye on 12/20/16.
 */

public class HttpResult<T> {
	private int status;
	private String message;
	private T data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
