package com.zhaozhuan.mobile.tile.http;

import android.text.TextUtils;
import android.widget.Toast;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.zhaozhuan.mobile.tile.TileApp;
import com.zhaozhuan.mobile.tile.entity.HttpResult;
import com.zhaozhuan.mobile.tile.entity.User;
import com.zhaozhuan.mobile.tile.util.CommonUtil;

import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by ricky.ye on 12/20/16.
 */

public class HttpMethods {
	public static final String BASE_URL = "http://api.zhuanche.com/v1/";

	private static final int DEFAULT_TIMEOUT = 15;

	private Retrofit retrofit;
	private UserService userService;

	private HttpMethods() {
		//添加body日志打印，http，stetho调试的拦截器
		HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
		interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

		//web缓存, 10M
		Cache cache = new Cache(CommonUtil.getDiskCacheDir("response"), 1024 * 1024 * 10);
		OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(interceptor)
			//                .addNetworkInterceptor(new HeaderInterceptors())
			.addNetworkInterceptor(new StethoInterceptor()).connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS).cache(cache).build();

		retrofit = new Retrofit.Builder().client(okHttpClient).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(
			RxJavaCallAdapterFactory.create()).baseUrl(BASE_URL).build();

		userService = retrofit.create(UserService.class);
	}

	private static class SingletonHolder {
		private static final HttpMethods INSTANCE = new HttpMethods();
	}

	public static HttpMethods getInstance() {
		return SingletonHolder.INSTANCE;
	}

	/**
	 * @param subscriber
	 * @param phone
	 */
	public void getSmsCode(Subscriber<Boolean> subscriber, String phone) {
		Observable<Boolean> observable = userService.getSms(phone).map(new HttpResultFunc<Boolean>());
		toSubscribe(observable, subscriber);
	}

	/**
	 * @param subscriber
	 * @param phone
	 */
	public void login(Subscriber<User> subscriber, String phone, String password) {
		Observable<User> observable = userService.login(phone, password).map(new HttpResultFunc<User>());
		toSubscribe(observable, subscriber);
	}

	private <T> void toSubscribe(Observable<T> o, Subscriber<T> s) {
		o.subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(s);
	}

	/**
	 * 用来统一处理Http的resultCode,并将HttpResult的Data部分剥离出来返回给subscriber
	 *
	 * @param <T>   Subscriber真正需要的数据类型，也就是Data部分的数据类型
	 */
	private class HttpResultFunc<T> implements Func1<HttpResult<T>, T> {

		@Override
		public T call(HttpResult<T> httpResult) {
			String message = CommonUtil.getApiExceptionMessage(httpResult.getStatus());
			if (!TextUtils.isEmpty(message)) {
				Toast.makeText(TileApp.getAPP().getApplicationContext(), message, Toast.LENGTH_LONG).show();
				return null;
			}
			return httpResult.getData();
		}
	}
}
