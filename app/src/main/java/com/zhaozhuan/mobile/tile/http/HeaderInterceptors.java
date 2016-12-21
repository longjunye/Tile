package com.zhaozhuan.mobile.tile.http;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ServiceConfigurationError;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by ricky.ye on 12/20/16.
 */

public class HeaderInterceptors implements Interceptor {
	private static final String TAG = "HeaderInterceptors";

	@Override
	public Response intercept(Chain chain) throws IOException {
		Request request = chain.request();
		Response originalResponse = chain.proceed(request);
		MediaType contentType = originalResponse.body().contentType();
		String originalContent = originalResponse.body().string();

		int code;
		String message;
		String body;

		JSONObject wrapper;
		try {
			wrapper = new JSONObject(originalContent);
			message = wrapper.getString("message");
			code = wrapper.getInt("status");
			body = wrapper.getString("data");
		} catch (JSONException e) {
			throw new ServiceConfigurationError("服务器错误：" + e.getLocalizedMessage());
		}

		String cacheControl = request.cacheControl().toString();
		return originalResponse.newBuilder().code(code).message(message).body(ResponseBody.create(contentType, body)).header("Cache-Control",
			cacheControl).removeHeader("Pragma").build();
	}
}
