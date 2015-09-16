package com.android.and0701.yasaandroid;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by 0701AND on 2015/09/14.
 */
public class Ch08_BroadcastReceiver extends AppCompatActivity {
	TextView tv;
	ImageView iv;
	SampleBroadcastReceiver sb;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LinearLayout ll = new LinearLayout(this);
		ll.setOrientation(LinearLayout.VERTICAL);
		setContentView(ll);

		tv = new TextView(this);
		iv = new ImageView(this);

		Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.light);
		iv.setImageBitmap(bmp);

		ll.addView(tv);
		ll.addView(iv);
		sb = new SampleBroadcastReceiver();
	}

	@Override
	protected void onPostResume() {
		super.onPostResume();
		IntentFilter itf = new IntentFilter();
		itf.addAction(Intent.ACTION_BATTERY_CHANGED);
		registerReceiver(sb, itf);
	}

	@Override
	protected void onPause() {
		super.onPause();
		unregisterReceiver(sb);
	}

	private class SampleBroadcastReceiver extends BroadcastReceiver{
		@Override
		public void onReceive(Context context, Intent intent) {
			String str = intent.getAction();
			if(str.equals(Intent.ACTION_BATTERY_CHANGED)) {
				float level = intent.getIntExtra("level", 0);
				float scale = intent.getIntExtra("scale", 1);
				float b = level / scale;
				tv.setText("バッテリ" + b * 100 + "%");
				AlphaAnimation anim = new AlphaAnimation(0, b);
				anim.setDuration((long)(1000/b));
				anim.setRepeatMode(Animation.REVERSE);
				anim.setRepeatCount(Animation.INFINITE);
				iv.startAnimation(anim);
			}
		}
	}
}
