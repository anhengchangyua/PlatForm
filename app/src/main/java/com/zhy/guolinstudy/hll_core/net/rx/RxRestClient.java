package com.zhy.guolinstudy.hll_core.net.rx;

import android.content.Context;

import com.zhy.guolinstudy.hll_core.loadingui.HLLoader;
import com.zhy.guolinstudy.hll_core.loadingui.LoaderStyle;
import com.zhy.guolinstudy.hll_core.net.HttpMethod;
import com.zhy.guolinstudy.hll_core.net.RestCreator;

import java.io.File;
import java.util.WeakHashMap;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * Created by wanyummy on 2017/7/10.
 */

public class RxRestClient {

    private final String URL;
    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();

    private final RequestBody BODY;
    //加入loadstyle
    private final LoaderStyle LOADER_STYLE;
    private final Context CONTEXT;
    //加入文件参数
    private final File FILE;

    public RxRestClient(String url, WeakHashMap<String, Object> params,
                        RequestBody body,
                        LoaderStyle loaderStyle,
                        Context context,
                        File file) {
        this.URL = url;
        PARAMS.putAll(params);
        this.BODY = body;
        this.CONTEXT = context;
        this.LOADER_STYLE = loaderStyle;
        this.FILE = file;
    }

    public static RxRestClientBuilder builder() {

        return new RxRestClientBuilder();
    }

    private Observable<String> request(HttpMethod method) {
        final RxRestService service = RestCreator.getRxRestService();

        Observable<String> observable = null;

        //SHOW LOADING
        if (LOADER_STYLE != null) {
            HLLoader.showLoading(CONTEXT, LOADER_STYLE);
        }

        //1 添加switch
        switch (method) {

            case GET:
                observable = service.get(URL, PARAMS);
                break;
            case POST:
                observable = service.post(URL, PARAMS);
                break;
            case POST_ARW:
                observable = service.postRaw(URL, BODY);
                break;
            case PUT:
                observable = service.put(URL, PARAMS);
                break;
            case PUT_ARM:
                observable = service.putRaw(URL, BODY);
                break;
            case DELETE:
                observable = service.delete(URL, PARAMS);
                break;
            case UPLOAD:
                final RequestBody requestBody = RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()), FILE);
                //from 表单的形式提交
                final MultipartBody.Part body = MultipartBody.Part.createFormData("file", FILE.getName(), requestBody);
                observable = RestCreator.getRxRestService().upload(URL, body);

                break;
            default:
                break;
        }
        //2 添加call回调
        return observable;
    }


    //使用方法
    public final Observable<String> get() {
        return request(HttpMethod.GET);
    }

    public final Observable<String> post() {

        if (BODY == null) {
            return request(HttpMethod.POST);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null");
            }
            return request(HttpMethod.POST_ARW);
        }

    }

    public final Observable<String> put() {

        if (BODY == null) {
            return request(HttpMethod.PUT);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null");
            }
            return request(HttpMethod.PUT_ARM);
        }
    }

    //上传
    public final Observable<String> upload() {
        return request(HttpMethod.UPLOAD);
    }

    //下载文件
    public final Observable<ResponseBody> download() {

        final Observable<ResponseBody> responseBodyObservable = RestCreator.getRxRestService().download(URL, PARAMS);
        return responseBodyObservable;

    }

    public final Observable<String> delete() {
        return request(HttpMethod.DELETE);
    }

}
