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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by 0701AND on 2015/09/07.
 */
public class Ch04_DispDate extends Activity {
	TextView tv;
	ListView lv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LinearLayout ll = new LinearLayout(this);
		ll.setOrientation(LinearLayout.VERTICAL);
		setContentView(ll);

		String str[] = new String[50];
		tv = new TextView(this);
		lv = new ListView(this);

		tv.setText("いらっしゃい！");

		SampleAdapter ad = new SampleAdapter(this, android.R.layout.simple_list_item_1, str);
		lv.setAdapter(ad);

		ll.addView(tv);
		ll.addView(lv);

		lv.setOnItemClickListener(new SampleItemClickListener());
	}
	class SampleItemClickListener implements AdapterView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			TextView tmp = (TextView)view;
			tv.setText(tmp.getText()+"ですねっ");
		}
	}
	class SampleAdapter extends ArrayAdapter<String> {
		public SampleAdapter(Context context, int resource, String[] objects) {
			super(context, resource, objects);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			DateFormat df = new SimpleDateFormat("yyyy/MM/dd", Locale.JAPAN);
			Calendar cl = Calendar.getInstance();
			cl.setTime(new Date());
			cl.add(Calendar.DATE, position);
			TextView t = new TextView(getBaseContext());
			t.setText(df.format(cl.getTime()));
			t.setBackgroundColor(Color.GRAY);
			t.setTextColor(Color.WHITE);

			if(cl.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
				t.setBackgroundColor(Color.WHITE);
				t.setTextColor(Color.RED);
			}
			convertView = t;
			return convertView;
		}
	}
}
