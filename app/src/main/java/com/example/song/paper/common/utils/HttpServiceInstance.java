package com.example.song.paper.common.utils;

import android.content.Context;
import android.webkit.WebSettings;

import com.example.song.paper.AppConstant;
import com.example.song.paper.MyApplication;
import com.example.song.paper.common.base.BaseResponse;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.functions.Function;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

public class HttpServiceInstance {
    private static final int DEFAULT_TIMEOUT = 20;
    private static HttpService instance = null;
    private static File httpCacheDirectory;
    private static OkHttpClient okHttpClient;
    public static HttpService getInstance(){
        if (instance==null){
            synchronized (HttpService.class){
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(AppConstant.Base_Url)
                        // .addConverterFactory(GsonConverterFactory.create())
                        .addConverterFactory(ResponseConverterFactory.create())
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .build();
                instance = retrofit.create(HttpService.class);
            }
        }
        return instance;
    }
    public static HttpService getInstance(Context context){
        if (instance==null){
            synchronized (HttpService.class){
                if (httpCacheDirectory == null) httpCacheDirectory = new File(context.getCacheDir(),"yuanyu_cache");
                Cache cache = new Cache(httpCacheDirectory,10*1024*1024);
//user agent is Mozilla/5.0 (Linux; Android 8.0.0; BND-AL10 Build/HONORBND-AL10; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/66.0.3359.126 Mobile Safari/537.36
                okHttpClient = new OkHttpClient.Builder()
                        .addNetworkInterceptor(
                                new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                        .cache(cache)
                        .addInterceptor(new Interceptor() {
                            @Override
                            public Response intercept(Chain chain) throws IOException {
                                Request request = chain.request().newBuilder()
                                        .removeHeader("User-Agent")
                                        .addHeader("User-Agent",WebSettings.getDefaultUserAgent(MyApplication.getContext()))
                                        .build();
                                return chain.proceed(request);
                            }
                        })
                        .addInterceptor(new CaheInterceptor(context))
                        .addNetworkInterceptor(new CaheInterceptor(context))
                        .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                        .writeTimeout(DEFAULT_TIMEOUT,TimeUnit.SECONDS)
                        .build();






                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(AppConstant.Base_Url)
                        //.addConverterFactory(GsonConverterFactory.create())
                        .addConverterFactory(ResponseConverterFactory.create())
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .client(okHttpClient)
                        .build();
                instance = retrofit.create(HttpService.class);
            }
        }

        return instance;
    }


    //处理错误的变换
    public static class ErrorTransformer<T> implements ObservableTransformer {

        @Override
        public ObservableSource apply(Observable upstream) {
            //onErrorResumeNext当发生错误的时候，由另外一个Observable来代替当前的Observable并继续发射数据
            return (Observable<T>) upstream.map(new HandleFuc<T>()).onErrorResumeNext(new HttpResponseFunc<T>());
        }
    }


    public static class HttpResponseFunc<T> implements Function<Throwable, Observable<T>> {
        @Override
        public Observable<T> apply(Throwable throwable) throws Exception {
            return Observable.error(throwable);
            //return Observable.error(new ExceptionHandler.ResponeThrowable(throwable,10));
        }
    }

    public static class HandleFuc<T> implements Function<BaseResponse<T>, T> {
        @Override
        public T apply(BaseResponse<T> response) throws Exception {
            if (!response.isOk()) {
                // throw new RuntimeException(response.getStatus() + "" + response.getMsg() != null ? response.getMsg() : "");
                throw new ExceptionHandler.ServerException(response.getStatus(),response.getMsg());
            }

            return response.getData();
        }
    }

}

