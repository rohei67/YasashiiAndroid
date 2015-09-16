package com.android.and0701.yasaandroid;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by 0701AND on 2015/09/07.
 */
public class Ch04_ViewCustomize extends Activity {
	TextView tv;
	ListView lv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LinearLayout ll = new LinearLayout(this);
		ll.setOrientation(LinearLayout.VERTICAL);
		setContentView(ll);

		tv = new TextView(this);
		tv.setText("えらじゃい！");

		lv = new ListView(this);
		String[] str = {"乗用車", "トラック", "オープンカー", "タクシー", "スポーツカー", "ミニカー", "自転車", "三輪車", "バイク", "飛行機", "ヘリコプター", "ロケット"};
		SampleAdapter ad = new SampleAdapter(this, android.R.layout.simple_list_item_2, str);
		lv.setAdapter(ad);

		ll.addView(tv);
		ll.addView(lv);

		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				TextView tmp = (TextView) view;
				tv.setText(tmp.getText() + "ですね♪");
			}
		});
	}

	class SampleAdapter extends ArrayAdapter<String> {
		public SampleAdapter(Context context, int resource, String[] objects) {
			super(context, resource, objects);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ListView l = (ListView) parent;
			String s = "---------" + l.getItemAtPosition(position);

			TextView t = new TextView(getBaseContext());
			t.setText(position + s);
			t.setBackgroundColor(Color.BLACK);
			t.setTextColor(Color.WHITE);

			if (position % 2 == 0) {
				t.setBackgroundColor(Color.WHITE);
				t.setTextColor(Color.BLACK);
			}
			convertView = t;
			return convertView;
		}
	}
}
