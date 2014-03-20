package cn.eoe.app.ui.base;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Window;
import android.widget.Toast;
import cn.eoe.app.R;

import com.umeng.analytics.MobclickAgent;

public class BaseFragmentActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE); 
		MobclickAgent.onError(this);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		MobclickAgent.onPause(this);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		MobclickAgent.onResume(this);
	}
	
	public void finish()
	{
		super.finish();
		overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
	}
	
	public void defaultFinish()
	{
		super.finish();
	}
	
	protected long mExitTime=0;
	/**
	 * 按返回键后根据时间 来判断双击退出。
	 * 相比开一个timerTask 要更加简洁和节省资源
	 */
	public void doubleClickExit(){
		if ((System.currentTimeMillis() - mExitTime) > 3000) {
			Toast.makeText(this,
                    getResources().getString(R.string.press_again_exit),
                    Toast.LENGTH_SHORT).show();
			mExitTime = System.currentTimeMillis();
		}else{
			defaultFinish();
		}
	}
}
