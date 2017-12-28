package com.zhy.uutils.hll_core.net;

import android.content.Context;

import com.zhy.uutils.hll_core.loadingui.HLLoader;
import com.zhy.uutils.hll_core.loadingui.LoaderStyle;
import com.zhy.uutils.hll_core.net.callback.IError;
import com.zhy.uutils.hll_core.net.callback.IFailure;
import com.zhy.uutils.hll_core.net.callback.IRequest;
import com.zhy.uutils.hll_core.net.callback.ISuccess;
import com.zhy.uutils.hll_core.net.callback.RequestCallBacks;
import com.zhy.uutils.hll_core.net.download.DownloadHandler;

import java.io.File;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by wanyummy on 2017/7/10.
 */

public class RestClient {

    private final String URL;
    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    private final IRequest REQUEST;
    //文件名
    private final String NAME;
    //后缀
    private final String EXTENSION;
    //存放目录
    private final String DOWNLOAD_DIR;

    private final IError ERROR;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final RequestBody BODY;
    //加入loadstyle
    private final LoaderStyle LOADER_STYLE;
    private final Context CONTEXT;
    //加入文件参数
    private final File FILE;

    public RestClient(String url, WeakHashMap<String, Object> params,
                      IRequest request,
                      String name,
                      String extension,
                      String download_dir,
                      IError error,
                      ISuccess success,
                      IFailure failure,
                      RequestBody body,
                      LoaderStyle loaderStyle,
                      Context context,
                      File file) {
        this.URL = url;
        PARAMS.putAll(params);
        this.REQUEST = request;
        this.NAME = name;
        this.EXTENSION = extension;
        this.DOWNLOAD_DIR = download_dir;
        this.ERROR = error;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.BODY = body;
        this.CONTEXT = context;
        this.LOADER_STYLE = loaderStyle;
        this.FILE = file;
    }

    public static RestClientBuilder builder() {

        return new RestClientBuilder();
    }

    private void request(HttpMethod method) {
        final RestService service = RestCreator.getRestService();
        Call<String> call = null;

        if (REQUEST != null) {
            REQUEST.onRequestStart();
        }
        //SHOW LOADING
        if (LOADER_STYLE != null) {
            HLLoader.showLoading(CONTEXT, LOADER_STYLE);
        }

        //1 添加switch
        switch (method) {

            case GET:
                call = service.get(URL, PARAMS);
                break;
            case POST:
                call = service.post(URL, PARAMS);
                break;
            case POST_ARW:
                call = service.postRaw(URL, BODY);
                break;
            case PUT:
                call = service.put(URL, PARAMS);
                break;
            case PUT_ARM:
                call = service.putRaw(URL, BODY);
                break;
            case DELETE:
                call = service.delete(URL, PARAMS);
                break;
            case UPLOAD:
                final RequestBody requestBody = RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()), FILE);
                //from 表单的形式提交
                final MultipartBody.Part body = MultipartBody.Part.createFormData("file", FILE.getName(), requestBody);
                call = RestCreator.getRestService().upload(URL, body);

                break;
            default:
                break;
        }
        //2 添加call回调
        if (call != null) {
            call.enqueue(getRequestCallback());
        }
    }

    private Callback<String> getRequestCallback() {
        return new RequestCallBacks(
                REQUEST,
                ERROR,
                SUCCESS,
                FAILURE,
                LOADER_STYLE
        );
    }


    //使用方法
    public final void get() {
        request(HttpMethod.GET);
    }

    public final void post() {

        if (BODY == null) {
            request(HttpMethod.POST);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null");
            }
            request(HttpMethod.POST_ARW);
        }

    }

    public final void put() {

        if (BODY == null) {
            request(HttpMethod.PUT);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null");
            }
            request(HttpMethod.PUT_ARM);
        }
    }

    //上传
    public final void upload() {
        request(HttpMethod.UPLOAD);
    }
    //下载文件
    public final void download(){
       new DownloadHandler(URL,REQUEST,NAME,EXTENSION,DOWNLOAD_DIR,SUCCESS,FAILURE,ERROR).handleDownload();
    }

    public final void delete() {
        request(HttpMethod.DELETE);
    }

}
