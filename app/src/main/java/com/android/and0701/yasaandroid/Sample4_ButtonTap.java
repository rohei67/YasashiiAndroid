package com.android.and0701.yasaandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Sample4_ButtonTap extends AppCompatActivity {
	TextView tv;
	Button bt;
	boolean blSwitch = true;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LinearLayout ll = new LinearLayout(this);
		ll.setOrientation(LinearLayout.VERTICAL);
		setContentView(ll);

		tv = new TextView(this);
		tv.setText("いらっしゃいませ");
		bt = new Button(this);
		bt.setText("購入");

		ll.addView(tv);
		ll.addView(bt);

		bt.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (blSwitch)
					tv.setText("ありがとうございます。");
				else
					tv.setText("またのご来店を、お待ちしてます！");
				blSwitch = !blSwitch;
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_sample4__button_tap, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if (id == R.id.action_settings) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}
}
