package com.android.and0701.yasaandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TableLayout;
import android.widget.TextView;

/**
 * Created by 0701AND on 2015/09/07.
 */
public class Ch04_Menu extends AppCompatActivity {
	TextView tv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		TableLayout tl = new TableLayout(this);
		setContentView(tl);

		tv = new TextView(this);
		tv.setText("いらっじゃいまぜぇ！！");

		tl.addView(tv);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(Menu.NONE, 0, 0, "乗用車");
		menu.add(Menu.NONE, 1, 1, "タクシー");
		menu.add(Menu.NONE, 2, 2, "スーパーカー");
		menu.add(Menu.NONE, 3, 3, "オープンカー");
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		String str = null;
		switch (item.getItemId()) {
			case 0:
				str = "乗用車";
				break;
			case 1:
				str = "タクシー";
				break;
			case 2:
				str = "スーパーカー";
				break;
			case 3:
				str = "オープンカー";
				break;
		}
		tv.setText(str + "ですねぇ～");
		return true;
	}
}
