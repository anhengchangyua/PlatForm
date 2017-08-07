package com.zhy.guolinstudy.hll_core.app;

import com.zhy.guolinstudy.hll_core.util.HLPreference;

/**
 * 用来管理用户信息
 *
 * @author zwy.
 * @time 2017/7/27 12:33
 * @description
 */
public class AcountManager {

    private enum SignTag {
        SIGN_TAG
    }

    //保存用户登录状态
    public static void setSignState(boolean state) {
        HLPreference.setAppFlag(SignTag.SIGN_TAG.name(), state);
    }

    public static boolean isSign() {
        return HLPreference.getAppFlag(SignTag.SIGN_TAG.name());
    }

    //回调方法
    public static void checkAccount(IUserChecker iUserChecker){
        if (isSign()){
            iUserChecker.onSignIn();
        }else {
            iUserChecker.onNotSignIn();
        }

    }

}
