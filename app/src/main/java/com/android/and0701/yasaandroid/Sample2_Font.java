package com.android.and0701.yasaandroid;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by 0701AND on 2015/09/07.
 */
public class Sample2_Font extends Activity {
	TextView[] tv = new TextView[3];

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LinearLayout ll = new LinearLayout(this);
		ll.setOrientation(LinearLayout.VERTICAL);
		setContentView(ll);
		for (int i = 0; i < tv.length; i++) {
			tv[i] = new TextView(this);
			tv[i].setText("This is a Car.");
		}
		tv[0].setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));
		tv[1].setTypeface(Typeface.create(Typeface.SERIF, Typeface.ITALIC));
		tv[2].setTypeface(Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD));

		tv[0].setTextSize(18);
		tv[1].setTextSize(25);
		tv[2].setTextSize(50);

		tv[0].setTextColor(Color.BLUE);
		tv[1].setTextColor(Color.YELLOW);
		tv[1].setBackgroundColor(Color.GRAY);
		tv[2].setTextColor(Color.RED);

		for (int i = 0; i < tv.length; i++) {
			ll.addView(tv[i]);
		}
	}
}
