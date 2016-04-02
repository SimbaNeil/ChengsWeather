package ad.baidu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.RelativeLayout;

import com.baidu.mobads.SplashAd;
import com.baidu.mobads.SplashAdListener;
import com.chengsweather.app.R;

import activity.ChooseAreaActivity;

/**
 * 实时�??屏，广告实时请求并且立即展现
 */
public class RSplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        // adUnitContainer
        RelativeLayout adsParent = (RelativeLayout) this.findViewById(R.id.adsRl);

        // the observer of AD
        SplashAdListener listener = new SplashAdListener() {
            @Override
            public void onAdDismissed() {
                Log.i("RSplashActivity", "onAdDismissed");
                jumpWhenCanClick(); // 跳转至您的应用主界面
            }

            @Override
            public void onAdFailed(String arg0) {
                Log.i("RSplashActivity", "onAdFailed");
                jump();
            }

            @Override
            public void onAdPresent() {
                Log.i("RSplashActivity", "onAdPresent");
            }

            @Override
            public void onAdClick() {
                Log.i("RSplashActivity", "onAdClick");
                // 设置�??屏可接受点击时，该回调可�??
            }
        };
        String adPlaceId = "2457533"; // 重要：请填上您的广告位ID，代码位错误会导致无法请求到广告
        new SplashAd(this, adsParent, listener, adPlaceId, true);
    }

    /**
     * 当设置开屏可点击时，�??要等待跳转页面关闭后，再切换至您的主窗口。故此时�??要增加canJumpImmediately判断�?? 另外，点击开屏还�??要在onResume中调用jumpWhenCanClick接口�??
     */
    public boolean canJumpImmediately = false;

    private void jumpWhenCanClick() {
        Log.d("test", "this.hasWindowFocus():" + this.hasWindowFocus());
        if (canJumpImmediately) {
            this.startActivity(new Intent(RSplashActivity.this, ChooseAreaActivity.class));
            this.finish();
        } else {
            canJumpImmediately = true;
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        canJumpImmediately = false;
    }

    /**
     * 不可点击的开屏，使用该jump方法，�?�不是用jumpWhenCanClick
     */
    private void jump() {
        this.startActivity(new Intent(RSplashActivity.this, ChooseAreaActivity.class));
        this.finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (canJumpImmediately) {
            jumpWhenCanClick();
        }
        canJumpImmediately = true;
    }

}
