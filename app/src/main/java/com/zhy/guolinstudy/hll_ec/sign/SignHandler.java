package com.zhy.guolinstudy.hll_ec.sign;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zhy.guolinstudy.hll_core.app.AcountManager;
import com.zhy.guolinstudy.hll_ec.database.DatabaseManager;
import com.zhy.guolinstudy.hll_ec.database.UserProfile;

/**
 * Created by wanyummy on 2017/7/27.
 */

public class SignHandler {

    public static void onSignUp(String response,ISignListener iSignListener) {
        final JSONObject jsonObject = JSON.parseObject(response).getJSONObject("data");
        final long userId = jsonObject.getInteger("userId");
        final String name = jsonObject.getString("name");
        final String avatar = jsonObject.getString("avatar");
        final String gender = jsonObject.getString("gender");
        final String address = jsonObject.getString("address");

        //写入
        final UserProfile profile = new UserProfile(userId, name, avatar, gender, address);
        DatabaseManager.getInstance().getmDao().insert(profile);

        //已经注册成功了
        AcountManager.setSignState(true);
        iSignListener.onSignUpSuccess();
    }

    public static void onSignIn(String response,ISignListener iSignListener) {
        final JSONObject jsonObject = JSON.parseObject(response).getJSONObject("data");
        final long userId = jsonObject.getInteger("userId");
        final String name = jsonObject.getString("name");
        final String avatar = jsonObject.getString("avatar");
        final String gender = jsonObject.getString("gender");
        final String address = jsonObject.getString("address");

        //写入
        final UserProfile profile = new UserProfile(userId, name, avatar, gender, address);
        DatabaseManager.getInstance().getmDao().insert(profile);

        //已经注册成功了
        AcountManager.setSignState(true);
        iSignListener.onSignInSuccess();
    }
}
