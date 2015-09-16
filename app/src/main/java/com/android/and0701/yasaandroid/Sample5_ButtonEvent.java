package com.android.and0701.yasaandroid;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by 0701AND on 2015/09/04.
 */
public class Sample5_ButtonEvent extends Activity {
	TextView tv;
	Button bt;

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		tv.setText("どうもです");
		return super.onTouchEvent(event);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LinearLayout ll = new LinearLayout(this);
		ll.setOrientation(LinearLayout.VERTICAL);
		setContentView(ll);

		tv = new TextView(this);
		tv.setText("いらっしゃいぁせ～！");
		bt = new Button(this);
		bt.setText("購入");

		ll.addView(tv);
		ll.addView(bt);
		bt.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					tv.setText("こんちゃ！");
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					tv.setText("ばいちゃ！");
				}
				return true;
			}
		});
	}
}
