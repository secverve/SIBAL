package org.pytorch.demo.objectdetection;

import android.app.Application;

import com.kakao.sdk.common.KakaoSdk;

public class kakaoApplication extends Application {
    private static kakaoApplication instance;
    private static String USERID = "";

    @Override
    public void onCreate(){
        super.onCreate();
        instance = this;
        KakaoSdk.init(this, "f3de2d08818769c79ad94cb4f8975e48");
    }

    public void setUSERID(String userid){
        this.USERID = userid;
    }

    public String getUSERID(){
        return this.USERID;
    }
}
