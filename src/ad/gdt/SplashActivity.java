package ad.gdt;

import com.chengsweather.app.R;
import com.qq.e.ads.splash.SplashAD;
import com.qq.e.ads.splash.SplashADListener;


import activity.ChooseAreaActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.view.Window;

public class SplashActivity extends Activity implements SplashADListener
{

	@SuppressWarnings("unused")
	private SplashAD splashAD;
	private ViewGroup container;

//	public Intent i = new Intent(this,ChooseAreaActivity.class);
	public boolean canJump = false;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_splash);
		
		container = (ViewGroup) this.findViewById(R.id.splash_container);
		new SplashAD(this, container, "1105224545","4000514032808881",this);
		
	}

	

	@Override
	public void onADPresent()
	{
		Log.i("AD_DEMO", "SplashADPresent");
	}

	@Override
	public void onADClicked()
	{
		Log.i("AD_DEMO", "SplashADClicked");
	}

	@Override
	public void onADDismissed()
	{
		Log.i("AD_DEMO", "SplashADDismissed");
		next();
	}

	@Override
	public void onNoAD(int errorCode)
	{
		Log.i("AD_DEMO", "LoadSplashADFail, eCode=" + errorCode);
		/** 如果加载广告失败，则直接跳转 */
		this.startActivity(new Intent(this, ChooseAreaActivity.class));
		this.finish();
	}

	/**
	 * 设置一个变量来控制当前开屏页面是否可以跳转，当开屏广告为普链类广告时，点击会打开一个广告落地页，此时开发者还不能打开自己的App主页。
	 * 当从广告落地页返回以后， 才可以跳转到开发者自己的App主页；当开屏广告是App类广告时只会下载App。
	 */
	private void next()
	{
		if (canJump)
		{
			this.startActivity(new Intent(this, ChooseAreaActivity.class));
			this.finish();
		}
		else
		{
			canJump = true;
		}
	}

	@Override
	protected void onPause()
	{
		super.onPause();
		canJump = false;
	}

	@Override
	protected void onResume()
	{
		super.onResume();
		if (canJump)
		{
			next();
		}
		canJump = true;
	}

	/** 开屏页最好禁止用户对返回按钮的控制 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		if (keyCode == KeyEvent.KEYCODE_BACK || keyCode == KeyEvent.KEYCODE_HOME)
		{
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

}
