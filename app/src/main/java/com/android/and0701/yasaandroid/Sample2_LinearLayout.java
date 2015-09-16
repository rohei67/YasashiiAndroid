package com.android.and0701.yasaandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * Created by 0701AND on 2015/09/03.
 */
public class Sample2_LinearLayout extends AppCompatActivity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LinearLayout ll = new LinearLayout(this);
		ll.setOrientation(LinearLayout.VERTICAL);
		setContentView(ll);

		Button[] bt = new Button[10];

		for (int i = 0; i < bt.length; i++) {
			bt[i] = new Button(this);
			bt[i].setText(Integer.toString(i));
			ll.addView(bt[i]);
		}
	}

}
