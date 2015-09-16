package com.android.and0701.yasaandroid;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by 0701AND on 2015/09/07.
 */
public class Ch04_Button345 extends Activity {
	TextView tv;
	Button bt;
	CheckBox cb1, cb2;
	RadioButton rb1, rb2;
	RadioGroup rg;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LinearLayout ll = new LinearLayout(this);
		ll.setOrientation(LinearLayout.VERTICAL);
		setContentView(ll);

		tv = new TextView(this);
		tv.setText("いらっしゃい！");
		bt = new Button(this);
		bt.setText("購入");

		cb1 = new CheckBox(this);
		cb2 = new CheckBox(this);
		cb1.setText("車");
		cb2.setText("トラック");

		rb1 = new RadioButton(this);
		rb2 = new RadioButton(this);
		rb1.setText("トヨタ");
		rb2.setText("カローラ");
		rg = new RadioGroup(this);
		rg.addView(rb1);
		rg.addView(rb2);
		rb1.setChecked(true);

		ll.addView(tv);
		ll.addView(bt);
		ll.addView(cb1);
		ll.addView(cb2);
		ll.addView(rg);

		bt.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				tv.setText("ありがとうござい！");
				Toast.makeText(Ch04_Button345.this, "ボタン押されたよ！", Toast.LENGTH_LONG).show();
				bt.setEnabled(false);

				Log.v("Yasa", "ボタン押されてるよ");
			}
		});

		cb1.setOnCheckedChangeListener(new SampleCheckedChangeLitener());
		cb2.setOnCheckedChangeListener(new SampleCheckedChangeLitener());

		rb1.setOnClickListener(new SampleClickListener());
		rb2.setOnClickListener(new SampleClickListener());
	}

	class SampleCheckedChangeLitener implements CompoundButton.OnCheckedChangeListener {
		@Override
		public void onCheckedChanged(CompoundButton cb, boolean isChecked) {
			if(isChecked) {
				tv.setText(cb.getText() + "を選んだお");
			} else {
				tv.setText(cb.getText() + "をやめたお");
			}
		}
	}
	class SampleClickListener implements View.OnClickListener {
		@Override
		public void onClick(View v) {
			RadioButton tmp = (RadioButton)v;
			tv.setText(tmp.getText() + "を選んだお");
		}
	}
}
