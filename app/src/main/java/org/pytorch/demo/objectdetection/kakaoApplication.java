package org.pytorch.demo.objectdetection;

import android.app.Application;

import com.kakao.sdk.common.KakaoSdk;

public class kakaoApplication extends Application {
    private static kakaoApplication instance;
    @Override
    public void onCreate(){
        super.onCreate();
        instance = this;
        KakaoSdk.init(this, "f3de2d08818769c79ad94cb4f8975e48");
    }
}
