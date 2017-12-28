package com.zhy.unetutil;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by wanyummy on 2017/12/28.
 */

public class UNetCreator {


    public static final class RetrofitHolder {
        private static final String BASE_URL = "";
        private static final Retrofit RETROFIT_CLIENT = new Retrofit.Builder()
                .client(OKHttpHolder.OK_HTTP_CLIENT)
                .baseUrl(BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
    }

    public static final class OKHttpHolder {
        private static final int TIME_OUT = 60;
        private static final ArrayList<Interceptor> INTERCEPTORS = new ArrayList<>();
        private static final OkHttpClient.Builder BUILDER = new OkHttpClient.Builder();

        private static final OkHttpClient.Builder addInterceptor() {
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


    public static final class UNetServiceHolder {
        private static final UNetService UNET_SERVICE = RetrofitHolder.RETROFIT_CLIENT.create(UNetService.class);
    }


    public static UNetService getRestService() {
        return UNetServiceHolder.UNET_SERVICE;
    }


}
