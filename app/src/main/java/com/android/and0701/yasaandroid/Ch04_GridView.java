package com.android.and0701.yasaandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by 0701AND on 2015/09/08.
 */
public class Ch04_GridView extends AppCompatActivity {
	TextView tv;
	GridView gv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LinearLayout ll = new LinearLayout(this);
		ll.setOrientation(LinearLayout.VERTICAL);
		setContentView(ll);

		tv = new TextView(this);
		tv.setText("えらっしゃいやせ！");

		gv = new GridView(this);
		String[] str={
			"乗用車", "トラック", "オープンカー", "タクシー", "スポーツカー", "ミニカー", "自転車", "三輪車", "バイク", "飛行機", "ヘリコプター", "ロケット"
		};

		ArrayAdapter<String> ad = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, str);
		gv.setAdapter(ad);
		gv.setNumColumns(3);

		ll.addView(tv);
		ll.addView(gv);

		gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				TextView tmp = (TextView) view;
				tv.setText(tmp.getText() + "ですねぇ？");
			}
		});
	}
}
