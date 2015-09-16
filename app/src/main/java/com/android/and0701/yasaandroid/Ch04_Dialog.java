package com.android.and0701.yasaandroid;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by 0701AND on 2015/09/07.
 */
public class Ch04_Dialog extends Activity {
	TextView tv;
	Button bt;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LinearLayout ll = new LinearLayout(this);
		ll.setOrientation(LinearLayout.VERTICAL);
		setContentView(ll);

		tv = new TextView(this);
		tv.setText("いらっしゃいあせ！");
		bt = new Button(this);
		bt.setText("買いたい…");

		ll.addView(tv);
		ll.addView(bt);

		bt.setOnClickListener(new SampleClickListener());
	}

	class SampleClickListener implements View.OnClickListener {
		@Override
		public void onClick(View v) {
			new AlertDialog.Builder(Ch04_Dialog.this).setTitle("確認してくれ").setMessage("ぽんとに買う？").
					setPositiveButton("いえす", new SampleDialogClickListener()).setNegativeButton("いや", null).show();
		}
		class SampleDialogClickListener implements DialogInterface.OnClickListener {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				new AlertDialog.Builder(Ch04_Dialog.this).setTitle("買ってくれて")
						.setMessage("ありがと☆").setPositiveButton("あい", null).show();
			}
		}
	}
}
