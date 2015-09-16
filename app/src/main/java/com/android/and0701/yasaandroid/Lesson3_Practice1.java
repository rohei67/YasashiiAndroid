package com.android.and0701.yasaandroid;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by 0701AND on 2015/09/04.
 */
public class Lesson3_Practice1 extends Activity {
	TextView tv;
	Button bt;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_pre_layout);


		/*LinearLayout fl = new LinearLayout(this);
		fl.setOrientation(LinearLayout.VERTICAL);
		setContentView(fl);*/

//		tv = new TextView(this);
		tv = (TextView)findViewById(R.id.textview1);

//		bt = new Button(this);
		bt = (Button)findViewById(R.id.button1);

//		fl.addView(tv);
//		fl.addView(bt);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				tv.setTextSize(50);
				tv.setTextColor(Color.BLUE);
				tv.setBackgroundColor(Color.LTGRAY);
				bt.setTextSize(50);
				bt.setTextColor(Color.GREEN);
				bt.setBackgroundColor(Color.YELLOW);
				bt.setText("押されてるぅ！");
				break;
			case MotionEvent.ACTION_UP:
				tv.setTextSize(80);
				tv.setTextColor(Color.RED);
				tv.setBackgroundColor(Color.WHITE);
				bt.setTextSize(20);
				bt.setTextColor(Color.YELLOW);
				bt.setBackgroundColor(Color.BLACK);
				bt.setText("離されてるぅ！");
				break;
		}

		return super.onTouchEvent(event);
	}
}
