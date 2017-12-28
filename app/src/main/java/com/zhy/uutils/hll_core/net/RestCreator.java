package com.zhy.uutils.hll_core.net;

import com.zhy.uutils.hll_core.app.ConfigType;
import com.zhy.uutils.hll_core.app.Hll;
import com.zhy.uutils.hll_core.net.rx.RxRestService;

import java.util.ArrayList;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by wanyummy on 2017/7/10.
 */

public class RestCreator {


    //惰性加载
    private static final class ParamsHolder {
        //声明全局的请求参数：
        public static final WeakHashMap<String, Object> PARAMS = new WeakHashMap<>();
    }

    public static WeakHashMap<String, Object> getParams() {

        return ParamsHolder.PARAMS;
    }

    private static final class RetrofitHolder {
        private static final String BASE_URL = Hll.getConfiguration(ConfigType.API_HOST);
        private static final Retrofit RETROFIT_CLIENT = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(OKHttpHolder.OK_HTTP_CLIENT)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    //构架okhttp
    private static final class OKHttpHolder {
        private static final int TIME_OUT = 60;
        private static final OkHttpClient.Builder BUILDER = new OkHttpClient.Builder();
        private static final ArrayList<Interceptor> INTERCEPTORS = Hll.getConfiguration(ConfigType.INTERCEPTOR);

        //自定义builder
        private static OkHttpClient.Builder addInterceptor() {
            if (INTERCEPTORS != null && !INTERCEPTORS.isEmpty()) {
                for (Interceptor interceptor : INTERCEPTORS) {
                    BUILDER.addInterceptor(interceptor);
                }
            }
            return BUILDER;
        }

        private static final OkHttpClient OK_HTTP_CLIENT = addInterceptor()
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .build();


    }

    //service 接口
    private static final class RestServiceHolder {
        private static final RestService REST_SERVICE =
                RetrofitHolder.RETROFIT_CLIENT.create(RestService.class);
    }

    public static RestService getRestService() {
        return RestServiceHolder.REST_SERVICE;
    }

    //rxservice 接口
    private static final class RxRestServiceHolder {
        private static final RxRestService REST_SERVICE =
                RetrofitHolder.RETROFIT_CLIENT.create(RxRestService.class);
    }

    public static RxRestService getRxRestService() {
        return RxRestServiceHolder.REST_SERVICE;
    }

}
